package de.htwg.se.starrealms.model.GameCore.structure

import de.htwg.se.starrealms.model.GameCore.{CardInterface, DeckInterface, DrawStrategy}

class DefaultDrawStrategy extends DrawStrategy {
  override def draw(deck: DeckInterface, count: Int): List[CardInterface] = {
    (1 to count).flatMap(_ => deck.drawCard()).toList
  }
}

class StartTurnStrategy extends DrawStrategy {
  override def draw(deck: DeckInterface, count: Int): List[CardInterface] = {
    (1 to count).flatMap(_ => deck.drawCard()).toList
  }
}

class TradeRowReplenishStrategy extends DrawStrategy {
  override def draw(deck: DeckInterface, count: Int): List[CardInterface] = {
    val cards = (1 to count).flatMap(_ => deck.drawCard()).toList
    println(s"Trade Row: ${cards.map(_.render()).mkString(", ")}")
    cards
  }
}
