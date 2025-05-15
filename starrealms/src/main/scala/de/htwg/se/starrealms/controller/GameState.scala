package de.htwg.se.starrealms.controller

import de.htwg.se.starrealms.model._
import de.htwg.util._

class GameState extends Observable {
  val deck: Deck = new Deck()

  def drawCard(cardType: String): Option[Card] = {
    val card = deck.drawCard()
    //card.foreach(discardPile :+= _)
    notifyObservers()
    card
  }

  def reset(): Unit = {
    deck.resetDeck()
    //discardPile = List()
    notifyObservers()
  }
}