package de.htwg.se.starrealms.model

class DefaultDeck {
  private val scoutCards: List[ScoutCard] = List.fill(8)(new ScoutCard())
  private val viperCards: List[ViperCard] = List.fill(2)(new ViperCard())

  def getScoutCards: List[ScoutCard] = scoutCards
  def getViperCards: List[ViperCard] = viperCards
  def getAllCards: List[DefaultCard] = scoutCards ++ viperCards
}