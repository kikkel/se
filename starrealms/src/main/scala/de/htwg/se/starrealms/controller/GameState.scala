/* package de.htwg.se.starrealms.controller

import de.htwg.se.starrealms.model._
import de.htwg.util._

class GameState extends Observable {
  private var deck: DefaultDeck = new DefaultDeck("DefaultDeck", "Default", List())
  private var discardPile: List[Card] = List()
  private var field: List[Card] = List()

  def drawCard(cardType: String): Option[Card] = {
    val card = deck.drawCard()
    card.foreach(discardPile :+= _)
    notifyObservers()
    card
  }

  def reset(): Unit = {
    deck.resetDeck()
    discardPile = List()
    field = List()
    notifyObservers()
  }
} */