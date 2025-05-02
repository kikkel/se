package de.htwg.se.starrealms.controller

import de.htwg.se.starrealms.model._
import de.htwg.util._

class GameState extends Observable {
  private var deck: DefaultDeck = new DefaultDeck("DefaultDeck", new CardType("Default"), List())
  private var discardPile: List[AbstractCard] = List()
  private var field: List[AbstractCard] = List()

  def drawCard(cardType: String): Option[AbstractCard] = {
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
}