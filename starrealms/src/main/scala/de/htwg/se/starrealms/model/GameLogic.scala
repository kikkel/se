package de.htwg.se.starrealms.model

import scala.collection.mutable.ListBuffer
import de.htwg.util.Observable
import de.htwg.se.starrealms.model._


//--------------------------------------------------------------------Strategy
trait DrawStrategy { def draw(deck: Deck, count: Int): List[Card] }

class StartTurnStrategy extends DrawStrategy {
  override def draw(deck: Deck, count: Int): List[Card] = {
    (1 to count).flatMap(_ => deck.drawCard()).toList
  }
}
class TradeRowReplenishStrategy extends DrawStrategy {
  override def draw(deck: Deck, count: Int): List[Card] = {
    val cards = (1 to count).flatMap(_ => deck.drawCard()).toList
    println(s"Trade Row: ${cards.map(_.render()).mkString(", ")}")
    cards
  }
}

//--------------------------------------------------------------------GameLogic
class GameLogic(var decks: Map[String, Deck]) extends Observable {
  private val replenishStrategy = new TradeRowReplenishStrategy()
  private val startTurnStrategy = new StartTurnStrategy()

  def drawCards(deckName: String, count: Int): List[Card] = {
    val deck = decks.getOrElse(deckName, throw new NoSuchElementException(s"Deck $deckName not found #GL.scala: drawCards"))
    val cards = startTurnStrategy.draw(deck, count)
    notifyObservers()
    cards
  }
  def replenishTradeRow(): Unit = {
    val tradeDeck = decks.getOrElse("Trade Deck", throw new NoSuchElementException("Trade Deck not found #GL.scala: replenishTradeRow"))
    replenishStrategy.draw(tradeDeck, 5)
    notifyObservers()
  }

  def drawCard(deckName: String): Option[Card] = {
    val deck = decks.getOrElse(deckName, throw new NoSuchElementException(s"Deck $deckName not found #GL.scala: drawCard"))
    val card = deck.drawCard()
    notifyObservers()
    card
  }

  def purchaseCard(deckName: String, card: Card): Unit = {
    val deck = decks.getOrElse(deckName, throw new NoSuchElementException(s"Deck $deckName not found #GL.scala: purchaseCard"))
    deck.removeCard(card)
    notifyObservers()
  }

  def playCard(card: Card): Unit = {
    val deck = decks.getOrElse("Personal Deck", throw new NoSuchElementException("Personal Deck not found #GL.scala: playCard"))
    deck.removeCard(card)
    notifyObservers()
  }


  def resetGame(): Unit = {
    decks.values.foreach(_.resetDeck())
    notifyObservers()
  }

  def getDeckState: String = { decks.map { case (name, deck) => 
    s"$name:\n${deck.getExpandedCards.map(_.render()).mkString("\n")}"
    }.mkString("\n") 
  }
}
