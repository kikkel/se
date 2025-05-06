package de.htwg.se.starrealms.model.CardItinerary

import de.htwg.se.starrealms.model._

case class TradeProd(
    name: String = "Trade Pod",
    cardType: String = "Ship",
    faction: Faction = new Blob,
    cost: Int = 2,
    primaryAbility: PrimaryAbility = PrimaryAbility(List("3 coins")),
    allyAbility: AllyAbility = AllyAbility(List("2 coins"))
) extends Ship(name, cardType, Some(faction), Some(cost), None, Some(primaryAbility), Some(allyAbility)) {
  override def render(): String = {
    s"Trade Pod Card: $name, Type: $cardType, Faction: ${faction.render()}, Cost: $cost, Primary Ability: ${primaryAbility.render()}, Ally Ability: ${allyAbility.render()}"
  }

}


 