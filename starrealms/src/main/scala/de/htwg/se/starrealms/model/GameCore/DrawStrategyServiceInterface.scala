package de.htwg.se.starrealms.model.GameCore

import de.htwg.se.starrealms.model.GameCore.Card

trait DrawStrategy { 
  def draw(deck: DeckInterface, count: Int): List[Card] 
} //Service Interface!!
