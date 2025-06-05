package de.htwg.se.starrealms.model.SetUpComponent.interface

import de.htwg.se.starrealms.model.DeckComponent.interface._

trait LoadCardsInterface {
  def loadFromResource(getCsvPath: String, setName: String): Map[String, DeckInterface]
  def getCsvPath: String
}
