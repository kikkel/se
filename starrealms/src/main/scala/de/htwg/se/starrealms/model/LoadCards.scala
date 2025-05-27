package de.htwg.se.starrealms.model


import scala.io.Source
import scala.util.{Failure, Try, Success}
import scala.util.matching.Regex


object LoadCards {
    def loadFromResource(getCsvPath: String, setName: String): Map[String, Deck] = {
        val loader = new CardCSVLoader(getCsvPath)
        loader.loadCardsFromFile()
        val cards = loader.getAllCards.filter(_.edition.nameOfEdition.trim.equalsIgnoreCase(setName.trim))
        val groupedCards = cards.groupBy(_.role.trim)
        groupedCards.map { case (role, cards) =>
            val deck = new Deck()
            deck.setName(role)
            val cardMap = cards.groupBy(identity).view.mapValues(_.size).toMap
            deck.setCards(cardMap)
            role -> deck
        }
    }

    val ki_filePath: String = "/Users/kianimoon/se/se/starrealms/src/main/resources/PlayableSets.csv"
    //val ki_filePath: String = "/Users/koeseazra/SE-uebungen/se/starrealms/src/main/resources/PlayableSets.csv"


    def getCsvPath: String =
        sys.env.getOrElse("CARDS_CSV_PATH", s"$ki_filePath")

}

class CardCSVLoader(filePath: String) {
    private var cardsByEdition: Map[String, List[Card]] = Map()
    private val validRoles = Set("Trade Deck", "Personal Deck", "Explorer Pile")


    def loadCardsFromFile(): Unit = {
        Try(Source.fromFile(filePath).getLines().toList) match {
            case Success(lines) if lines.nonEmpty =>
                val headers = parseCSVLine(lines.head)
                val rows = lines.tail.map { line =>
                    val values = parseCSVLine(line)
                    val paddedValues = values.padTo(headers.length, "")
                    headers.zip(paddedValues).toMap
                }
                val validRows = filterValidCards(rows)
                val cards = validRows.flatMap { row =>
                    Try(createParsedCard(row)) match {
                        case Success(parsedCard) =>
                            transformToSpecificCard(parsedCard) match {
                                case Some(card) => Some(card)
                                case None =>
                                    println(s"Unknown role or error in card: $row\n")
                                    None
                            }
                        case Failure(exception) =>
                            println(s"Failed to create card for row: $row. Error: ${exception.getMessage}\n #loadCardsFromFile")
                            None
                    }
                }
                cardsByEdition = cards.groupBy(_.edition.nameOfEdition)
                println(s"\nLoaded Editions: \n${cardsByEdition.keys.mkString(",\n")}")
            case Success(_) =>
                println("\nThe file is empty. No cards loaded.\n")
            case Failure(exception) =>
                println(s"\nFailed to load cards from file: ${exception.getMessage}\n")
        }
    }

    private def parseCSVLine(line: String): List[String] = {
        val fields = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)")
        fields.map(_.trim.replaceAll("^\"|\"$", "")).toList

    }

    private def filterValidCards(rows: List[Map[String, String]]): List[Map[String, String]] = {
        rows.filter(row =>
            row.get("Name").exists(_.nonEmpty) &&
            row.get("CardType").exists(_.nonEmpty) &&
            row.get("Edition").exists(_.nonEmpty) &&
            row.get("Role").exists(_.nonEmpty)
        )
    }
    private def createParsedCard(card: Map[String, String]): ParsedCard = {
        val abilities = card.get("Text").map(_.split("<hr>").map(_.trim).toList).getOrElse(List())
        val primaryAbility = abilities.headOption.filter(_.nonEmpty).map(a => new Ability(parseActions(a)))
        val allyAbility = abilities.find(_.contains("Ally")).map(a => new Ability(parseActions(a)))
        val scrapAbility = abilities.find(_.startsWith("{Scrap}")).map(a => new Ability(parseActions(a.stripPrefix("{Scrap}").trim)) )

        ParsedCard(
            edition = Edition(card("Edition")),
            cardName = card("Name"),
            cost = card.get("Cost").flatMap(c => Try(c.toInt).toOption).orElse(None),
            primaryAbility = primaryAbility,
            allyAbility = allyAbility,
            scrapAbility = scrapAbility,
            faction = Faction(card("Faction")),
            cardType = Try {
                card("CardType") match {
                    case "Ship" => new Ship()
                    case "Base" =>
                        val defense = card.getOrElse("Defense", "0")
                        val isOutPost = card.get("Outpost").exists(_.toBoolean)
                        new Base(defense, isOutPost)
                    case _ => throw new IllegalArgumentException(s"Unknown card type: ${card("CardType")}")
                }
            },
            qty = card.get("Qty").map(_.toInt).getOrElse(0),
            role = card.get("Role").getOrElse(""),
            notes = card.get("Notes").filter(_.nonEmpty)
        )
    }
    def transformToSpecificCard(parsedCard: ParsedCard): Option[Card] = {
        if (!validRoles.contains(parsedCard.role)) {
            println(s"Unknown role: ${parsedCard.role}. Ignoring card.\n")
            return None
        }

        parsedCard.role match {
            case "Trade Deck" =>
                Some(FactionCard(
                    edition = parsedCard.edition,
                    cardName = parsedCard.cardName,
                    cost = parsedCard.cost.getOrElse(0),
                    primaryAbility = parsedCard.primaryAbility,
                    allyAbility = parsedCard.allyAbility,
                    scrapAbility = parsedCard.scrapAbility,
                    faction = parsedCard.faction,
                    cardType = parsedCard.cardType,
                    qty = parsedCard.qty,
                    role = parsedCard.role,
                    notes = parsedCard.notes
                ))
            case "Personal Deck" =>
                Some(DefaultCard(
                    edition = parsedCard.edition,
                    cardName = parsedCard.cardName,
                    primaryAbility = parsedCard.primaryAbility,
                    faction = parsedCard.faction,
                    cardType = parsedCard.cardType,
                    qty = parsedCard.qty,
                    role = parsedCard.role
                ))
            case "Explorer Pile" =>
                Some(ExplorerCard(
                    edition = parsedCard.edition,
                    cardName = parsedCard.cardName,
                    cost = parsedCard.cost.getOrElse(0),
                    primaryAbility = parsedCard.primaryAbility,
                    scrapAbility = parsedCard.scrapAbility,
                    faction = parsedCard.faction,
                    cardType = parsedCard.cardType,
                    qty = parsedCard.qty,
                    role = parsedCard.role
                ))
        }
    }

    private def parseActions(text: String): List[Action] = {
        text.split("<hr>").toList.map { action =>
            if (action.contains("OR")) {
                val conditions = action.split("OR").map(_.trim)
                ConditionalAction(parseSingleAction(conditions(0)), parseSingleAction(conditions(1)))
            } else if (action.contains("Whenever")) {
                val parts = action.split(", gain").map(_.trim)
                TriggeredAction(parts(0), parseSingleAction(parts(1)))
            } else if (action.contains("then")) {
                val steps = action.split("then").map(_.trim).toList
                CompositeAction(steps.map(parseSingleAction))
            } else {
                parseSingleAction(action)
            }
        }
    }
    private def parseSingleAction(text: String): Action = {
        val actionMap: Map[String, String => Action] = Map(
            "Trade" -> (desc => SimpleAction(s"Gain ${desc.filter(_.isDigit)} Trade")),
            "Combat" -> (desc => SimpleAction(s"Gain ${desc.filter(_.isDigit)} Combat")),
            "Authority" -> (desc => SimpleAction(s"Gain ${desc.filter(_.isDigit)} Authority")),
            "Draw a card" -> (_ => SimpleAction("Draw a card")),
            "Target opponent discards a card" -> (_ => SimpleAction("Target opponent discards a card"))
        )

        actionMap
        .find { case (keyword, _) => text.contains(keyword) }
        .map { case (_, constructor) => constructor(text) }
        .getOrElse(SimpleAction(text))
    }
    def getCardsForEdition(nameOfEdition: String): List[Card] = {

        cardsByEdition.filter { case (key, _) => key.trim.toLowerCase.contains(nameOfEdition.trim.toLowerCase) }
            .values.flatten.toList
    }

    def getAllCards: List[Card] = cardsByEdition.values.flatten.toList;

}


