package de.htwg.se.starrealms.model.DeckComponent.structure

import de.htwg.se.starrealms.model.DeckComponent._
import de.htwg.se.starrealms.model.CardComponent.Card

class TradeRowReplenishStrategy extends DrawStrategy {
  override def draw(deck: DeckInterface, count: Int): List[Card] = {
    val cards = (1 to count).flatMap(_ => deck.drawCard()).toList
    println(s"Trade Row: ${cards.map(_.render()).mkString(", ")}")
    cards
  }
}