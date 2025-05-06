package de.htwg.se.starrealms.model

import com.github.tototoshi.csv._

object CardItineraryApp {
  def main(args: Array[String]): Unit = {
    val filePath = "src/main/resources/CoreSet.csv"
    val rows = loadCsv(filePath)

    val validCards = filterValidCards(rows)

    val classDefinitions = validCards.map(generateCardClass)

    val outputFile = "src/main/scala/de/htwg/se/starrealms/model/CardItinerary/CardItinerary.scala"
    writeClassesToFile(classDefinitions, outputFile)

    println(s"Generated ${validCards.size} card classes.")
  }
  //load
  def loadCsv(filePath: String): List[Map[String, String]] = {
    val reader = CSVReader.open(new java.io.File(filePath))
    val rows = reader.allWithHeaders()
    reader.close()
    rows
  }



  //filter
  def filterValidCards(rows: List[Map[String, String]]): List[Map[String, String]] = {
    rows.filter(row =>
      row.get("Name").exists(_.nonEmpty) &&
      row.get("Type").exists(_.nonEmpty) &&
      row.get("Set").exists(_ == "Core Set")

    )
  }

def generateCardClass(card: Map[String, String]): String = {
  val name = card("Name").replace(" ", "")
  val cardType = card("CardType")

  // Parse abilities from the "Text" field
  val abilities = card.get("Text").map(_.split("<hr>").map(_.trim).toList).getOrElse(List())

  // Allocate abilities
  val primaryAbility = abilities.headOption.map(a => s"""Some(new Ability(List("$a")))""").getOrElse("None")

  // Generalized ally ability detection for all factions
  val allyAbilities = abilities.filter(_.matches("\\{.*? Ally\\}:.*"))
  val allyAbility = if (allyAbilities.nonEmpty) {
    val parsedAbilities = allyAbilities.map(_.replaceAll("\\{.*? Ally\\}: ", "").trim)
    s"""Some(new Ability(List(${parsedAbilities.map(a => s""""$a"""").mkString(", ")})))"""
  } else {
    "None"
  }

  // Scrap ability detection
  val scrapAbility = abilities.find(_.startsWith("{Scrap}")).map(a => s"""Some(new Ability(List("${a.stripPrefix("{Scrap}: ").trim}")))""").getOrElse("None")

  s"""
  class $name extends Card(
    name = "${card("Name")}",
    cardType = new CardType("$cardType"),
    primaryAbility = $primaryAbility,
    allyAbility = $allyAbility,
    scrapAbility = $scrapAbility
  ) {
    override def render(): String = s"${card("RenderTemplate")}"
  }
  """
}

  //write classes to file
  def writeClassesToFile(classes: List[String], outputFile: String): Unit = {
  val writer = new java.io.PrintWriter(new java.io.File(outputFile))
  try {
    classes.foreach(writer.println)
  } finally {
    writer.close()
  }
  }

}


