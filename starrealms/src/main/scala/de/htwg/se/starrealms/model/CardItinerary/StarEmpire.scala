package de.htwg.se.starrealms.model.CardItinerary

import de.htwg.se.starrealms.model._

case class Corvette() extends FactionCard {
  override def cardName = "Corvette"
  override def cardType = new Ship()
  override def faction = Faction("star empire")
  override def cost = Some(2)
  override def primaryAbility = Some(PrimaryAbility(List("Gain 1 Combat", "Draw a card")))
  override def allyAbility = Some(AllyAbility(List("Gain 2 Combat")))
  override def scrapAbility = None
  override def render(): String = {
    s"$cardName, Cost: ${cost.getOrElse("N/A")}, " +
      s"Primary Ability: ${primaryAbility.map(_.render()).getOrElse("N/A")}, " +
      s"Ally Ability: ${allyAbility.map(_.render()).getOrElse("N/A")}} #StarEmpire.scala"
  }

}


