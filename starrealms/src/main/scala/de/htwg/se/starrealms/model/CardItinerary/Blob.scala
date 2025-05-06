package de.htwg.se.starrealms.model.CardItinerary

import de.htwg.se.starrealms.model._

case class TradeProd(
    name: String = "Trade Pod",
    cardType: String = "Ship",
    faction: Faction = new Blob,
    cost: Int = 2,
    primaryAbility: PrimaryAbility = PrimaryAbility(List("3 coins")),
    allyAbility: AllyAbility = AllyAbility(List("2 coins")),
    scrapAbility: Option[Ability] = None
) extends Ship(name, cardType, faction, cost, primaryAbility, allyAbility, scrapAbility) {
  override def render(): String = {
    s"Trade Pod Card: $name, Type: $cardType, Faction: ${faction.render()}, Cost: $cost, Primary Ability: ${primaryAbility.map(_.render()).getOrElse("None")}, Ally Ability: ${allyAbility.render()}, Scrap Ability: ${scrapAbility.map(_.render()).getOrElse("None")}"
  }

}


 