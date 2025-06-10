package de.htwg.se.starrealms.model.DeckComponent

import de.htwg.se.starrealms.model.CardComponent.Card

trait DrawStrategy { 
  def draw(deck: DeckInterface, count: Int): List[Card] 
} //Service Interface!!
