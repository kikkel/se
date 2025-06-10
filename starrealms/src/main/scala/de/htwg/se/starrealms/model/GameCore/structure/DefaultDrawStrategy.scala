package de.htwg.se.starrealms.model.GameCore.structure

import de.htwg.se.starrealms.model.GameCore.{Card, DeckInterface, DrawStrategy}

class DefaultDrawStrategy extends DrawStrategy {
  override def draw(deck: DeckInterface, count: Int): List[Card] = {
    (1 to count).flatMap(_ => deck.drawCard()).toList
  }
}