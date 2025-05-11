package de.htwg.se.starrealms.model.CardItinerary

import de.htwg.se.starrealms.model._

case class TradePod() extends FactionCard {
  override def cardName = "Trade Pod"
  override def cardType = new Base()
  override def faction = Faction("blob")
  override def cost = Some(2)
  override def primaryAbility = Some(PrimaryAbility(List("3 coins")))
  override def allyAbility = Some(AllyAbility(List("2 coins")))
  override def render(): String = {
      s"$cardName, Cost: ${cost.getOrElse("N/A")}, " +
          s"Primary Ability: ${primaryAbility.map(_.render()).getOrElse("N/A")}, " +
          s"Ally Ability: ${allyAbility.map(_.render()).getOrElse("N/A")}} #Blob.scala"
  }
}


