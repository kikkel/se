package de.htwg.se.starrealms.model.DeckComponent.structure

import de.htwg.se.starrealms.model.DeckComponent._
import de.htwg.se.starrealms.model.CardComponent.Card

class StartTurnStrategy extends DrawStrategy {
  override def draw(deck: DeckInterface, count: Int): List[Card] = {
    (1 to count).flatMap(_ => deck.drawCard()).toList
  }
}