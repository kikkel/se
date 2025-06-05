package de.htwg.se.starrealms.model.DeckComponent.str

import de.htwg.se.starrealms.model.DeckComponent.interface._
import de.htwg.se.starrealms.model.CardComponent.interface.Card

class StartTurnStrategy extends DrawStrategy {
  override def draw(deck: DeckInterface, count: Int): List[Card] = {
    (1 to count).flatMap(_ => deck.drawCard()).toList
  }
}