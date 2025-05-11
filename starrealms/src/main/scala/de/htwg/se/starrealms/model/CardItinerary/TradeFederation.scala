package de.htwg.se.starrealms.model.CardItinerary

import de.htwg.se.starrealms.model._


case class BarterWorld() extends FactionCard {
  override def cardName = "Barter World"
  override def cardType = new Base()
  override def faction = Faction("trade federation")
  override def cost = Some(4)
  override def defense = Some("4")
  override def isOutpost = false
  override def allyAbility = Some(AllyAbility(List("Gain 2 Authority", "Gain 2 Trade")))
  override def scrapAbility = Some(ScrapAbility(List("Gain 5 Combat")))
  override def render(): String = {
    s"$cardName, Cost: ${cost.getOrElse("N/A")}, Defense: ${defense.getOrElse("N/A")}, " +
      s"Primary Ability: ${primaryAbility.map(_.render()).getOrElse("N/A")}, " +
      s"Ally Ability: ${allyAbility.map(_.render()).getOrElse("N/A")}, " +
      s"Scrap Ability: ${scrapAbility.map(_.render()).getOrElse("N/A")} #TradeFederation.scala"
  }

}

