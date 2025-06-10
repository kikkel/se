package de.htwg.se.starrealms.model.GameCore

trait LoadCardsInterface {
  def loadFromResource(getCsvPath: String, setName: String): Map[String, DeckInterface]
  def getCsvPath: String
}
