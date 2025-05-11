package de.htwg.se.starrealms.model.CardItinerary

import de.htwg.se.starrealms.model._


case class BarterWorld() extends Base {
  override def name = "Barter World"
  override def faction = Faction("trade federation")
  override def cost = Some(4)
  override def defense = Some("4")
  override def isOutPost = false
  override def allyAbility = Some(AllyAbility(List("Gain 2 Authority", "Gain 2 Trade")))
  override def scrapAbility = Some(ScrapAbility(List("Gain 5 Combat")))
  override def render(): String = s"Barter World Card"
}

