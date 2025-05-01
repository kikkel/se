package de.htwg.se.starrealms.model

import de.htwg.se.starrealms.view
import de.htwg.util.Observable

class DefaultDeck {
  private var cards: List[String] = List.fill(8)("Scout") ++ List.fill(2)("Viper")

  // Draw a card of the specified type (e.g., "Scout" or "Viper")
  def drawCard(cardType: String): Option[String] = {
    val index = cards.indexWhere(_.toLowerCase.contains(cardType.toLowerCase))
    if (index != -1) {
      val card = cards(index)
      cards = cards.patch(index, Nil, 1) // Remove the card from the deck
      Some(card)
    } else {
      None // No card of the specified type is left
    }
  }

  // Check if the deck is empty
  def isEmpty: Boolean = cards.isEmpty

  // Get the current state of the deck as a string
  def getDeckState: String = if (cards.nonEmpty) cards.mkString(", ") else "Empty"

  // Reset the deck to its default state
  def resetDeck(): Unit = {
    cards = List.fill(8)("Scout") ++ List.fill(2)("Viper")
  }

  def getScoutCards: List[ScoutCard] = ???
  def getViperCards: List[ViperCard] = ???
  def getAllCards: List[DefaultCard] = ???
}