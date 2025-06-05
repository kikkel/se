package de.htwg.se.starrealms.model.DeckComponent.interface

import de.htwg.se.starrealms.model.CardComponent.interface.Card


trait DeckInterface {        //The DeckInterface is not for Deck.scala — it’s for everyone else.
  def getName: String
  def getCards: Map[Card, Int]
  def getExpandedCards: List[Card]

  def setName(name: String): Unit
  def setCards(cards: Map[Card, Int]): Unit
  def setCardStack(stack: List[Card]): Unit

  def addCard(card: Card): Unit
  def addCards(cards: List[Card]): Unit
  def removeCard(card: Card): Unit

  def shuffle(): Unit
  def drawCard(): Option[Card]
  def resetDeck(): Unit
  def render(): String
}