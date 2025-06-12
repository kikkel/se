package de.htwg.se.starrealms.model.GameCore

import de.htwg.se.starrealms.model.GameCore.Card
import scala.util.Try

trait DeckInterface {        //The DeckInterface is not for Deck.scala — it’s for everyone else.
  def getName: String
  def getCards: Map[Card, Int]
  def getCardStack: List[Card]

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

trait DeckDirectorInterface {
    def constructEmptyDeck(name: String, builderFactory: => Builder): DeckInterface
    def constructCustomDeck(name: String, builderFactory: => Builder, cards: List[Card]): DeckInterface
    def constructDecks(builderFactory: => Builder, groupedCards: Map[String, List[Card]]): Map[String, DeckInterface]
}

trait Builder {
    def reset: Unit
    def setName(name: String): Unit
    def setCards(cards: Map[Card, Int]): Unit
    def addCard(card: Card): Unit
    def addCards(cards: List[Card]): Unit
    def getProduct: DeckInterface
}
