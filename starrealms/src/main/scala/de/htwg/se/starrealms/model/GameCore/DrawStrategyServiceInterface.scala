package de.htwg.se.starrealms.model.GameCore

import de.htwg.se.starrealms.model.GameCore.CardInterface

trait DrawStrategy { 
  def draw(deck: DeckInterface, count: Int): List[CardInterface] 
} //Service Interface!!
