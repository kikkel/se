package de.htwg.se.starrealms.model

import com.github.tototoshi.csv._


object LoadCards{
  def loadFromResource(filename: String, setName: String): List[Card] = {
    val resource = Option(getClass.getResource(s"/$filename"))
      .getOrElse(throw new RuntimeException(s"Resource not found: $filename"))
    val loader = new CardCSVLoader(resource.getPath)
    loader.loadCardsFromFile()
    loader.getCardsForSet(setName) 
  }
}

class CardCSVLoader(filePath: String) {
    private var cardsBySet: Map[String, List[Card]] = Map()

    def loadCardsFromFile(): Unit = {
        val reader = CSVReader.open(new java.io.File(filePath))
        val rows = reader.allWithHeaders()
        val validCards = filterValidCards(rows)
        val cardInstances = validCards.map(createCardInstance)
        cardsBySet = cardInstances.collect { case card: FactionCard => card }.groupBy(_.set.nameOfSet)
        reader.close()
    }

    def getCardsForSet(setName: String): List[Card] = {
        if (cardsBySet.isEmpty) { loadCardsFromFile() }
        cardsBySet.getOrElse(setName, List())
    }

    private def filterValidCards(rows: List[Map[String, String]]): List[Map[String, String]] = {
        rows.filter(row => 
            row.get("Name").exists(_.nonEmpty) &&
            row.get("CardType").exists(_.nonEmpty) &&
            row.get("Set").exists(_.nonEmpty)
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

        new FactionCard(
            set = Set(card("Set")),
            cardName = card("Name"),
            cost = card.get("Cost").map(_.toInt).getOrElse(0),
            primaryAbility = primaryAbility,
            allyAbility = allyAbility,
            scrapAbility = scrapAbility,
            faction = faction,
            cardType = cardType
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


