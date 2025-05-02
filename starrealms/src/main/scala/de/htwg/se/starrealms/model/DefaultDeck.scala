package de.htwg.se.starrealms.model

import scala.util.Random


class DefaultDeck(name: String, cardType: CardType, initialCards: List[DefaultCard]) extends AbstractDeck(name, cardType, initialCards) {
  private var localCards: List[DefaultCard] = List.fill(8)(new ScoutCard) ++ List.fill(2)(new ViperCard)
  //private var discardPile: List[AbstractCard] = List()


  override def size: Int = cards.size
  override def isEmpty: Boolean = cards.isEmpty
  override def getName: String = name
  override def getCardType: CardType = cardType
  override def getCards: List[DefaultCard] = localCards


  override def addCard(card: AbstractCard): Unit = ???
  override def removeCard(card: AbstractCard): Unit = ???
  override def shuffle(): Unit = localCards = Random.shuffle(localCards)


  // Draw a card of the specified type (e.g., "Scout" or "Viper")
  override def drawCard(): Option[DefaultCard] = {
    val index = Random.nextInt(localCards.size) // Randomly select an index
    if (index != -1) {
      val card = localCards(index)
      localCards = localCards.patch(index, Nil, 1) // Remove the card from the deck
      Some(card)
      //discardPile = discardPile :+ card // Add the drawn card to the discard pile
    } else {
      None // No card of the specified type is left
    }
  }

  // Get the current state of the deck as a string
  def getDeckState: String = if (localCards.nonEmpty) localCards.mkString("\n ") else "Empty"
  def viewDiscardPile: String = if (localCards.nonEmpty) localCards.mkString("\n ") else "Empty"

  // Reset the deck to its default state
  def resetDeck(): Unit = {
    localCards = List.fill(8)(new ScoutCard) ++ List.fill(2)(new ViperCard)
    //discardPile = List()
  }

  def getScoutCards: List[ScoutCard] = ???
  def getViperCards: List[ViperCard] = ???
  def getAllCards: List[DefaultCard] = ???
}