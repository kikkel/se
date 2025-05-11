package de.htwg.se.starrealms.model.CardItinerary

import de.htwg.se.starrealms.model._

case class Corvette() extends Ship {
  override def name = "Corvette"
  override def faction = Faction("star empire")
  override def cost = Some(2)
  override def primaryAbility = Some(PrimaryAbility(List("Gain 1 Combat", "Draw a card")))
  override def allyAbility = Some(AllyAbility(List("Gain 2 Combat")))
  override def render(): String = s"Corvette Card"
}


