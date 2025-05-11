package de.htwg.se.starrealms.model.CardItinerary

import de.htwg.se.starrealms.model._


case class BattleStation() extends FactionCard {
  override def cardName = "Battle Station"
  override def cardType = new Base()
  override def faction = Faction("machine cult")
  override def cost = Some(4)
  override def defense = Some("4")
  override def isOutPost = true
  override def scrapAbility = Some(ScrapAbility(List("Gain 5 Combat")))
  override def render(): String = {
    s"$cardName, Cost: ${cost.getOrElse("N/A")}, Defense: ${defense.getOrElse("N/A")}, " +
      s"Scrap Ability: ${scrapAbility.map(_.render()).getOrElse("N/A")}} #MachineCult.scala"
  }
}


