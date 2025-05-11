package de.htwg.se.starrealms.model.CardItinerary

import de.htwg.se.starrealms.model._


case class BattleStation() extends Base {
  override def name = "Battle Station"
  override def faction = Faction("machine cult")
  override def cost = Some(4)
  override def defense = Some("4")
  override def isOutPost = true
  override def scrapAbility = Some(ScrapAbility(List("Gain 5 Combat")))
  override def render(): String = s"Battle Station Card"
}
