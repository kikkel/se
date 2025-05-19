package de.htwg.se.starrealms.model

import scala.collection.mutable.ListBuffer
import de.htwg.util.Observable
import de.htwg.se.starrealms.model._

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
class GameLogic(var deck: Deck) extends Observable {
  private val tradeRowDeck = new Deck()
  private val replenishStrategy = new TradeRowReplenishStrategy()
  private val startTurnStrategy = new StartTurnStrategy()
  
  def drawCards(count: Int): List[Card] = {
    val cards = startTurnStrategy.draw(deck, count)
    notifyObservers()
    cards
  }
  def replenishTradeRow(): Unit = {
    replenishStrategy.draw(tradeRowDeck, 5)
    notifyObservers()
  }

  def drawCard(): Option[Card] = {
    val card = deck.drawCard()
    notifyObservers()
    card
  }

  def purchaseCard(card: Card): Unit = {
    deck.removeCard(card)
    notifyObservers()
  }
  
  def playCard(card: Card): Unit = {
    deck.removeCard(card)
    notifyObservers()
  }


  def resetGame(): Unit = {
    deck.resetDeck()
    notifyObservers()
  }

  def getDeckState: String = deck.render()
}