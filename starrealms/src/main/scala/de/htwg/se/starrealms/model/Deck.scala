package de.htwg.se.starrealms.model

import scala.util.Random

abstract class Deck(val name: String, val cards: List[Card]) {
    
  //informative
  def isEmpty: Boolean
  def getName: String
  def getCards: List[Card]

  //functionality
  def addCard(card: Card): Unit 
  def removeCard(card: Card): Unit
  def shuffle(): Unit
  def drawCard(): Option[Card]
    
}
    


//-------------------------------------------------------------------------------------

class DefaultDeck(name: String, cardType: CardType, initialCards: List[Card]) extends Deck(name, initialCards) {
  val scoutCard = CardFactory.createCard("Scout")
  val viperCard = CardFactory.createCard("Viper")
  private var localCards: List[Card] = List.fill(8)(scoutCard) ++ List.fill(2)(viperCard)
  //private var discardPile: List[Card] = List()

  override def isEmpty: Boolean = localCards.isEmpty
  override def getName: String = name
  override def getCards: List[Card] = localCards


  override def addCard(card: Card): Unit = ???
  override def removeCard(card: Card): Unit = ???
  override def shuffle(): Unit = ??? //localCards = Random.shuffle(localCards)


  // Draw a card of the specified type (e.g., "Scout" or "Viper")
  override def drawCard(): Option[Card] = {
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
    localCards = List.fill(8)(scoutCard) ++ List.fill(2)(viperCard)
    //discardPile = List()
  }


}