package de.htwg.se.starrealms.model

import scala.io.Source
import javax.management.relation.Role


object LoadCards {
  def loadFromResource(filename: String, setName: String): List[Card] = {
    //val loader = new CardCSVLoader(s"/Users/kianimoon/se/se/starrealms/src/main/resources/$filename")
    //val loader = new CardCSVLoader(s"/Users/kianimoon/se/se/starrealms/src/main/resources/$filename")
    //val loader = new CardCSVLoader(s"starrealms/src/main/resources/$filename")
    loader.loadCardsFromFile()
    loader.getCardsForSet(setName) 
  }
}

class CardCSVLoader(filePath: String) {
    private var cardsBySet: Map[String, List[Card]] = Map()

    def loadCardsFromFile(): Unit = {
        val lines = Source.fromFile(filePath).getLines().toList
        if (lines.isEmpty) return
        val headers = lines.head.split(",").map(_.trim)
        val rows = lines.tail.map { line =>
            val values = line.split(",", -1).map(_.trim)
            headers.zipAll(values, "", "").toMap
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

    private def createCardInstance(card: Map[String, String]): Card = {
        val faction = Faction(card("Faction"))
        val cardType = card("CardType") match {
            case "Ship" => new Ship()
            case "Base" => 
                val defense = card.getOrElse("Defense", "0")
                val isOutPost = card.get("Outpost").exists(_.toBoolean)
                new Base(defense, isOutPost)
            case _ => throw new IllegalArgumentException(s"Unknown card type: ${card("CardType")}")
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
            case _ => throw new IllegalArgumentException(s"Unknown role: ${card("Role")}")
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


