package de.htwg.se.starrealms.model

import scala.io.Source
import scala.util.{Failure, Try, Success}
import java.io.File


object LoadCards {
    def loadFromResource(getCsvPath: String, setName: String): List[Card] = {
        val loader = new CardCSVLoader(getCsvPath)
        loader.loadCardsFromFile()
        val cards = loader.getCardsForSet(setName)

        val groupedCards = cards.groupBy(_.role)
        groupedCards.map { case (role, cards) =>
            val deck = new Deck()
            deck.setName(role)
            deck.setCards(cards)
            role -> deck
            }
    }

    val ki_filePath: String = "/Users/kianimoon/se/se/starrealms/src/main/resources/FullCardItinerary.csv"
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
            case Success(_) => // Handle empty file case if needed
            case Failure(exception) =>
                println(s"Failed to load cards from file: ${exception.getMessage}")
        }
    }

    def getCardsForSet(setName: String): List[Card] = {
            if (cardsBySet.isEmpty) { loadCardsFromFile() }
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
        text.split("<hr>").map {
            case action if action.contains("Gain") => 
                case action if action.contains("Combat") => new CombatAction(action.filter(_.isDigit).toInt)
                case action if action.contains("Trade") => new CoinAction(action.filter(_.isDigit).toInt)
                case action if action.contains("Authority") => new HealingAction(action.filter(_.isDigit).toInt)
            case _ => new ComplexAction(action)
        }
    }

    private def createCardInstance(card: Map[String, String]): Card = {
        val faction = Faction(card("Faction"))
        val cardType: Try[CardType] = Try {
            card("CardType") match {
                case "Ship" => new Ship()
                case "Base" =>
                    val defense = card.getOrElse("Defense", "0")
                    val isOutPost = card.get("Outpost").exists(_.toBoolean)
                    new Base(defense, isOutPost)
                case _ => throw new IllegalArgumentException(s"Unknown card type: ${card("CardType")}")
            }
        } 
        val abilities = card.get("Text").map(_.split("<hr>").map(_.trim).toList).getOrElse(List())
        val primaryAbility = abilities.headOption.map(a => new Ability(List(a)))
        val allyAbility = abilities.find(_.contains("Ally")).map(a => new Ability(List(a)))
        val scrapAbility = abilities.find(_.startsWith("{Scrap}")).map(a => new Ability(List(a.stripPrefix("{Scrap}").trim)))
        val qty = card("Qty").map(_.toInt)
        val role = card("Role") match {
            case "Trade Deck" => "Trade Deck"
            case "Explorer Pile" => "Explorer Deck"
            case "Personal Deck" => "Personal Deck"
            case _ => Failure(new IllegalArgumentException(s"Unknown role: ${card("Role")}"))
        }

        new FactionCard(
            set = Set(card("Set")),
            cardName = card("Name"),
            cost = card.get("Cost").map(_.toInt).getOrElse(0),
            primaryAbility = primaryAbility,
            allyAbility = allyAbility,
            scrapAbility = scrapAbility,
            faction = faction,
            cardType = cardType,
            qty = card("Qty").toInt,
            role = card("Role")
        )

/*         new DefaultCard(
            set = Set(card("Set")),
            cardName = card("Scout") || card("Viper"),
            primaryAbility = primaryAbility,
            faction = faction,
            cardType = cardType
        )

        new ExplorerCard(
            set = Set(card("Set")),
            cardName = card("Explorer"),
            primaryAbility = primaryAbility,
            scrapAbility = scrapAbility,
            faction = faction,
            cardType = cardType
        ) */
    }
    def getAllCards: List[Card] = cardsBySet.values.flatten.toList
}


