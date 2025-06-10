package de.htwg.se.starrealms.model.DeckComponent

trait LoadCardsInterface {
  def loadFromResource(getCsvPath: String, setName: String): Map[String, DeckInterface]
  def getCsvPath: String
}
