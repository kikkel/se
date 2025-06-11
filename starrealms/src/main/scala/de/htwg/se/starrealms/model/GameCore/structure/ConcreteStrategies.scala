package de.htwg.se.starrealms.model.GameCore.structure

import de.htwg.se.starrealms.model.GameCore.{Card, DeckInterface, DrawStrategy}

class DefaultDrawStrategy extends DrawStrategy {
  override def draw(deck: DeckInterface, count: Int): List[Card] = {
    (1 to count).flatMap(_ => deck.drawCard()).toList
  }
}

class StartTurnStrategy extends DrawStrategy {
  override def draw(deck: DeckInterface, count: Int): List[Card] = {
    (1 to count).flatMap(_ => deck.drawCard()).toList
  }
}

class TradeRowReplenishStrategy extends DrawStrategy {
  override def draw(deck: DeckInterface, count: Int): List[Card] = {
    val cards = (1 to count).flatMap(_ => deck.drawCard()).toList
    println(s"Trade Row: ${cards.map(_.render()).mkString(", ")}")
    cards
  }
}