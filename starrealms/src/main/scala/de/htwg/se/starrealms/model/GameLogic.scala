package de.htwg.se.starrealms.model

import scala.collection.mutable.ListBuffer
import de.htwg.util.Observable
import de.htwg.se.starrealms.model._

class GameLogic(var deck: Deck) extends Observable {
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