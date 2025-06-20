package de.htwg.se.starrealms.model.GameCore

import de.htwg.se.starrealms.model.GameCore.CardInterface
import scala.util.Try

trait DeckInterface {        //The DeckInterface is not for Deck.scala — it’s for everyone else.
  def getName: String
  def getCards: Map[CardInterface, Int]
  def getCardStack: List[CardInterface]

  def setName(name: String): Unit
  def setCards(cards: Map[CardInterface, Int]): Unit
  def setCardStack(stack: List[CardInterface]): Unit

  def addCard(card: CardInterface): Unit
  def addCards(cards: List[CardInterface]): Unit
  def removeCard(card: CardInterface): Unit

  def shuffle(): Unit
  def drawCard(): Option[CardInterface]
  def resetDeck(): Unit
  def render(): String
}

trait DeckDirectorInterface {
    def constructEmptyDeck(name: String, builderFactory: => Builder): DeckInterface
    def constructCustomDeck(name: String, builderFactory: => Builder, cards: List[CardInterface]): DeckInterface
    def constructDecks(builderFactory: => Builder, groupedCards: Map[String, List[CardInterface]]): Map[String, DeckInterface]
}

trait Builder {
    def reset: Unit
    def setName(name: String): Unit
    def setCards(cards: Map[CardInterface, Int]): Unit
    def addCard(card: CardInterface): Unit
    def addCards(cards: List[CardInterface]): Unit
    def getProduct: DeckInterface
}
