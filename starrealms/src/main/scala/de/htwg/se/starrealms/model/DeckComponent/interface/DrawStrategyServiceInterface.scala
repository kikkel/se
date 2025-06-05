package de.htwg.se.starrealms.model.DeckComponent.interface

import de.htwg.se.starrealms.model.CardComponent.interface.Card

trait DrawStrategy { 
  def draw(deck: DeckInterface, count: Int): List[Card] 
} //Service Interface!!
