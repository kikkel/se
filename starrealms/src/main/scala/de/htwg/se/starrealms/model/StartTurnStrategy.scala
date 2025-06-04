package de.htwg.se.starrealms.model

class StartTurnStrategy extends DrawStrategy {
  override def draw(deck: Deck, count: Int): List[Card] = {
    (1 to count).flatMap(_ => deck.drawCard()).toList
  }
}