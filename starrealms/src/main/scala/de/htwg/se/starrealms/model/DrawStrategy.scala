package de.htwg.se.starrealms.model

trait DrawStrategy { 
  def draw(deck: Deck, count: Int): List[Card] 
} //Service Interface!!
