package de.htwg.se.starrealms.model

import com.github.tototoshi.csv._

class CardCSVLoader(filePath: String) {
    private var cardsBySet: Map[String, List[Card]] = Map()

    def loadCardsFromFile(): Unit = {
        val rows = loadCsv(filePath)
        val validCards = filterValidCards(rows)
        val cardInstances = validCards.map(createCardInstance)
        cardsBySet = cardInstances.groupBy(_.set.nameOfSet)
    }

    def getCardsForSet(setName: String): List[Card] = {
        if (cardsBySet.isEmpty) loadCardsFromFile()
        cardsBySet.getOrElse(setName, List())
    }
    private def loadCsv(filePath: String): List[Map[String, String]] = {
    val reader = CSVReader.open(new java.io.File(filePath))
    val rows = reader.allWithHeaders()
    reader.close()
    rows
    }
    private def filterValidCards(rows: List[Map[String, String]]): List[Map[String, String]] = {
        rows.filter(row =>
            row.get("Name").exists(_.nonEmpty) &&
            row.get("CardType").exists(_.nonEmpty) &&
            row.get("Set").exists(_ == "Core Set")
        )
    }
    private def createCardInstance(card: Map[String, String]): Card = {
        val faction = Faction(card("Faction"))
        val cardType = card("CardType").toLowerCase match {
            case "ship" => new Ship()
            case "base" =>
                val defense = card.get("Defense").getOrElse("0")
                val isOutpost = 
                    card.get("Outpost").exists(_.toLowerCase.contains("outpost"))
                new Base(defense, isOutpost)
            case _ => throw new IllegalArgumentException(s"Unknown card type: ${card("CardType")}")
        }
        val abilities = card.get("Text").map(_.split("<hr>").map(_.trim).toList).getOrElse(List()) 
        val primaryAbility = abilities.headOption.map(a => new Ability(List(a)))
        val allyAbility = abilities.find(_.contains("Ally")).map(a => new Ability(List(a)))
        val scrapAbility = abilities.find(_.startsWith("{Scrap}")).map(a => new Ability(List(a.stripPrefix("{Scrap}: ").trim)))

        new FactionCard(
            set = Set(card("Set")),
            cardName = card("Name"),
            cost = card.get("Cost").flatMap(s => scala.util.Try(s.toInt).toOption),
            primaryAbility = primaryAbility,
            allyAbility = allyAbility,
            scrapAbility = scrapAbility,
            faction = faction,
            colour = card.get("Colour").getOrElse(""),
            cardType = cardType
        )
    }
    def getAllCards: List[Card] = cardsBySet.values.flatten.toList
}


