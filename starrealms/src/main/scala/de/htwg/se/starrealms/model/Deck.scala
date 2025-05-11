/* package de.htwg.se.starrealms.model

import de.htwg.se.starrealms.model._
import scala.util.Random

// Abstract Deck class
trait Deck {
  def getName: String
  def getCards: List[Card]

  // Functionality
  def addCard(card: Card): Unit
  def removeCard(card: Card): Unit
  def shuffle(): Unit
  def drawCard(): Option[Card]
  def resetDeck(): Unit
}

// DefaultDeck implementation
class DefaultDeck(name: String, cardType: String, initialCards: List[Card]) extends Deck {
  val scoutCard = new Scout()
  val viperCard = new Viper()

  // Initialize localCards using DefaultCard
  private var localCards: List[Card] = List.fill(8)(scoutCard) ++ List.fill(2)(viperCard)

  override def getName: String = name
  override def getCards: List[Card] = localCards

  override def addCard(card: Card): Unit = {
    localCards = localCards :+ card
  }

  override def removeCard(card: Card): Unit = {
    localCards = localCards.filterNot(_ == card)
  }

  override def shuffle(): Unit = {
    localCards = scala.util.Random.shuffle(localCards)
  }

  override def drawCard(): Option[Card] = {
    localCards match {
      case Nil => None
      case head :: tail =>
        localCards = tail
        Some(head)
    }
  }

  override def resetDeck(): Unit = {
    localCards = List.fill(8)(scoutCard) ++ List.fill(2)(viperCard)
  }
}


/* 
//-------------------------------------------------------------------------------------

class DefaultDeck(name: String, cardType: String, initialCards: List[Card]) extends Deck(name, initialCards) {
  val scoutCard = Card("Scout")
  val viperCard = CardFactory.createCard("Viper")
  /*private*/ var localCards: List[Card] = List.fill(8)(scoutCard) ++ List.fill(2)(viperCard)

  override def isEmpty: Boolean = localCards.isEmpty
  override def getName: String = name
  override def getCards: List[Card] = localCards

  override def addCard(card: Card): Unit = {
    localCards = localCards :+ card
  }

  override def removeCard(card: Card): Unit = {
    localCards = localCards.filterNot(_ == card)
  }

  override def shuffle(): Unit = {
    localCards = scala.util.Random.shuffle(localCards)
  }

  override def drawCard(): Option[Card] = {
    if (localCards.nonEmpty) {
      val card = localCards.head
      localCards = localCards.tail
      Some(card)
    } else {
      None
    }
  }

  override def resetDeck(): Unit = {
    localCards = List.fill(8)(CardFactory.createCard("Scout")) ++ List.fill(2)(CardFactory.createCard("Viper"))
  }

  def getDeckState: String = if (localCards.nonEmpty) localCards.mkString("\n ") else "Empty"
} */ */