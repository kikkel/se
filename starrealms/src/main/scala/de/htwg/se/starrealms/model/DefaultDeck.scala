package de.htwg.se.starrealms.model

import scala.util.Random


class DefaultDeck(name: String, cardType: CardType) extends AbstractDeck(name, cardType) {
  private var cards: List[DefaultCard] = List.fill(8)(new ScoutCard) ++ List.fill(2)(new ViperCard)


  override def size: Int = cards.size
  override def isEmpty: Boolean = cards.isEmpty
  override def getName: String = name
  override def getCardType: CardType = cardType
  override def getCards: List[DefaultCard] = cards


  override def addCard(card: AbstractCard): Unit = ???
  override def removeCard(card: AbstractCard): Unit = ???
  override def shuffle(): Unit = cards = Random.shuffle(cards)


  // Draw a card of the specified type (e.g., "Scout" or "Viper")
  override def drawCard(): Option[DefaultCard] = {
    val index = Random.nextInt(cards.size) // Randomly select an index
    if (index != -1) {
      val card = cards(index)
      cards = cards.patch(index, Nil, 1) // Remove the card from the deck
      Some(card)
    } else {
      None // No card of the specified type is left
    }
  }

  // Get the current state of the deck as a string
  def getDeckState: String = if (cards.nonEmpty) cards.mkString(", ") else "Empty"

  // Reset the deck to its default state
  def resetDeck(): Unit = {
    cards = List.fill(8)(new ScoutCard) ++ List.fill(2)(new ViperCard)
  }

  def getScoutCards: List[ScoutCard] = ???
  def getViperCards: List[ViperCard] = ???
  def getAllCards: List[DefaultCard] = ???
}