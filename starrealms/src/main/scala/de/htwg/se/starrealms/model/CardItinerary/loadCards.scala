package de.htwg.se.starrealms.model

import com.github.tototoshi.csv._

object CardItineraryApp {
    def main(args: Array[String]): Unit = {
        val filePath = "src/main/resources/CoreSet.csv"
        val rows = loadCsv(filePath)

        val validCards = filterValidCards(rows)

        val cardInstances = validCards.map(createCardInstance)

        val outputFile = "src/main/scala/de/htwg/se/starrealms/model/CardItinerary/CardItinerary.scala"
        writeClassesToFile(cardInstances.map(_.render()), outputFile)

        println(s"Generated ${cardInstances.size} card classes.")
    }

    // Load CSV
    def loadCsv(filePath: String): List[Map[String, String]] = {
        val reader = CSVReader.open(new java.io.File(filePath))
        val rows = reader.allWithHeaders()
        reader.close()
        rows
    }

    // Filter valid cards
    def filterValidCards(rows: List[Map[String, String]]): List[Map[String, String]] = {
        rows.filter(row =>
            row.get("Name").exists(_.nonEmpty) &&
            row.get("Type").exists(_.nonEmpty) &&
            row.get("Set").exists(_ == "Core Set")

        )
    }

    // Create card instance using the bridge and factory
    def createCardInstance(card: Map[String, String]): Card = {
        val faction = Faction(card("Faction"))
        val cardType = card("CardType").toLowerCase match {
            case "ship" => new Ship()
            case "base" =>
                val isOutpost = card.get("Outpost").exists(_.toLowerCase == "true")
                new Base(isOutpost)
            case _ => throw new IllegalArgumentException(s"Unknown card type: ${card("CardType")} #createCardInstance")
        }

        // Parse abilities from the "Text" field
        val abilities = card.get("Text").map(_.split("<hr>").map(_.trim).toList).getOrElse(List())

        val primaryAbility = abilities.headOption.map(a => new Ability(List(a)))
        val allyAbilities = abilities.filter(_.matches("\\{.*? Ally\\}:.*"))
        val allyAbility = if (allyAbilities.nonEmpty) {
            Some(new Ability(allyAbilities.map(_.replaceAll("\\{.*? Ally\\}: ", "").trim)))
        } else None
        val scrapAbility = abilities.find(_.startsWith("{Scrap}")).map(a => new Ability(List(a.stripPrefix("{Scrap}: ").trim)))

        new FactionCard(
            cardName = card("Name"),
            cost = card.get("Cost").map(_.toInt),
            defense = card.get("Defense"),
            primaryAbility = primaryAbility,
            allyAbility = allyAbility,
            scrapAbility = scrapAbility,
            faction = faction,
            cardType = cardType
        )
    }

    // Write classes to file
    def writeClassesToFile(classes: List[String], outputFile: String): Unit = {
        val writer = new java.io.PrintWriter(new java.io.File(outputFile))
        try {
            classes.foreach(writer.println)
        } finally {
            writer.close()
        }
    }
}


