package de.htwg.se.starrealms.model

import scala.io.Source
import scala.util.{Failure, Try, Success}


object LoadCards {
    def loadFromResource(getCsvPath: String, setName: String): Map[String, Deck] = {
        val loader = new CardCSVLoader(getCsvPath)
        loader.loadCardsFromFile()
        loader.testCardParsing()

        val cards = loader.getCardsForSet(setName)

        val groupedCards = cards.groupBy(_.role)
        groupedCards.map { case (role, cards) =>
            val deck = new Deck()
            deck.setName(role)
            deck.setCards(cards)
            role -> deck
            }
    }

    val ki_filePath: String = "/Users/kianimoon/se/se/starrealms/src/main/resources/CoreSet.csv"
    //val ki_filePath: String = "/Users/kianimoon/se/se/starrealms/src/main/resources/FullCardItinerary.csv"
    //val ki_filePath: String = "/Users/koeseazra/SE-uebungen/se/starrealms/src/main/resources/FullCardItinerary.csv"

    def getCsvPath: String =
        sys.env.getOrElse("CARDS_CSV_PATH", s"$ki_filePath")

}

class CardCSVLoader(filePath: String) {
    private var cardsBySet: Map[String, List[Card]] = Map()

    def loadCardsFromFile(): Unit = {
        Try(Source.fromFile(filePath).getLines().toList) match {
            case Success(lines) if lines.nonEmpty =>
                val headers = lines.head.split(",").map(_.trim)
                val rows = lines.tail.map { line =>
                    val values = line.split(",", -1).map(_.trim)
                    headers.zipAll(values, "", "").toMap
                }
                val validRows = filterValidCards(rows)                 
                val cards = validRows.flatMap { row =>
                    Try(createParsedCard(row)) match {
                        case Success(card) => Some(card)
                        case Failure(exception) =>
                            println(s"Failed to create card for row: $row. Error: ${exception.getMessage}")
                            None
                    }
                }
                cardsBySet = cards.groupBy(_.set.nameOfSet)
                println(s"Loaded Sets: ${cardsBySet.keys.mkString(", ")}")
            case Success(_) =>
                println("The file is empty. No cards loaded.")
            case Failure(exception) =>
                println(s"Failed to load cards from file: ${exception.getMessage}")
        }
    }

    def getCardsForSet(setName: String): List[Card] = {
            if (cardsBySet.isEmpty) { loadCardsFromFile() }
            println(s"Requested set: $setName. Available sets: ${cardsBySet.keys.mkString(", ")}")
            cardsBySet.getOrElse(setName, List())
            
    }

    private def filterValidCards(rows: List[Map[String, String]]): List[Map[String, String]] = {
        rows.filter(row =>
            row.get("Name").exists(_.nonEmpty) &&
            row.get("CardType").exists(_.nonEmpty) &&
            row.get("Set").exists(_.nonEmpty) &&
            row.get("Role").exists(_.nonEmpty)
        )
    }
    private def parseActions(text: String): List[Action] = {
        val actionMap: Map[String, String => Action] = Map(
            "Trade" -> (desc => SimpleAction(s"Gain ${desc.filter(_.isDigit)} Trade")),
            "Combat" -> (desc => SimpleAction(s"Gain ${desc.filter(_.isDigit)} Combat")),
            "Authority" -> (desc => SimpleAction(s"Gain ${desc.filter(_.isDigit)} Authority")),
            "Draw a card" -> (_ => SimpleAction("Draw a card")),
            "Target opponent discards a card" -> (_ => SimpleAction("Target opponent discards a card"))
        )

        text.split("<hr>").toList.map { actions =>
            if (text.contains("OR")) {
                val conditions = text.split("OR").map(_.trim)
                ConditionalAction(parseSingleAction(conditions(0)), parseSingleAction(conditions(1)))
            } else if (text.contains("Whenever")) {
                val parts = text.split(", gain").map(_.trim)
                TriggeredAction(parts(0), parseSingleAction(parts(1)))
            } else if (text.contains("then")) {
                val steps = text.split("then").map(_.trim).toList
                CompositeAction(steps.map(parseSingleAction))
            } else {
                parseSingleAction(text)
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

    private def createParsedCard(card: Map[String, String]): ParsedCard = {
        val abilities = card.get("Text").map(_.split("<hr>").map(_.trim).toList).getOrElse(List())
        val primaryAbility = abilities.headOption.filter(_.nonEmpty).map(a => new Ability(parseActions(a)))
        val allyAbility = abilities.find(_.contains("Ally")).map(a => new Ability(parseActions(a)))
        val scrapAbility = abilities.find(_.startsWith("{Scrap}")).map(a => new Ability(parseActions(a.stripPrefix("{Scrap}").trim)) )

        ParsedCard(
            set = Set(card("Set")),
            cardName = card("Name"),
            cost = card.get("Cost").map(_.toInt),
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
            role = card("Role"),
            notes = card.get("Notes").getOrElse("")
        )
    }
    def transformToSpecificCard(parsedCard: ParsedCard): Option[Card] = {
        parsedCard.role match {
            case "Trade Deck" =>
                Some(FactionCard(
                    set = parsedCard.set,
                    cardName = parsedCard.cardName,
                    cost = parsedCard.cost.getOrElse(0),
                    primaryAbility = parsedCard.primaryAbility,
                    allyAbility = parsedCard.allyAbility,
                    scrapAbility = parsedCard.scrapAbility,
                    faction = parsedCard.faction,
                    cardType = parsedCard.cardType,
                    qty = parsedCard.qty,
                    role = parsedCard.role
                ))
            case "Personal Deck" =>
                Some(DefaultCard(
                    set = parsedCard.set,
                    cardName = parsedCard.cardName,
                    primaryAbility = parsedCard.primaryAbility,
                    faction = parsedCard.faction,
                    cardType = parsedCard.cardType,
                    qty = parsedCard.qty,
                    role = parsedCard.role
                ))
            case "Explorer Pile" =>
                Some(ExplorerCard(
                    set = parsedCard.set,
                    cardName = parsedCard.cardName,
                    cost = parsedCard.cost.getOrElse(0),
                    primaryAbility = parsedCard.primaryAbility,
                    scrapAbility = parsedCard.scrapAbility,
                    faction = parsedCard.faction,
                    cardType = parsedCard.cardType,
                    qty = parsedCard.qty,
                    role = parsedCard.role
                ))
            case _ =>
                println(s"Unknown role: ${parsedCard.role}. Ignoring card.")
                None
        }
    }

    def getAllCards: List[Card] = cardsBySet.values.flatten.toList; 

    def testCardParsing(): Unit = {
        if (cardsBySet.isEmpty) {
            println("Card data is empty. Attempting to load cards...")
            loadCardsFromFile()
        }

        val allCards = getAllCards
        println(s"Total cards loaded: ${allCards.length}")

        val invalidCards = allCards.filter(card =>
            card.cardName.isEmpty || 
            card.role.isEmpty || 
            card.cardType.isFailure
        )

        if (invalidCards.nonEmpty) {
            println(s"Warning: Found ${invalidCards.length} cards with potential issues.")
            invalidCards.foreach(card =>
                println(s"Issue in card: ${card.cardName}, Role: ${card.role}, CardType: ${card.cardType}")
            )
        } else {
            println("All cards parsed successfully.")
        }
    }
}


