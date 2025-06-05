package de.htwg.se.starrealms.model.SetUpComponent

trait DrawStrategy { 
  def draw(deck: Deck, count: Int): List[Card] 
} //Service Interface!!
