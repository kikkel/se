package de.htwg.se.starrealms.model.CardItinerary

import de.htwg.se.starrealms.model._

case class Corvette() extends FactionCard(
  cardName = "Corvette",
  cost = Some(2),
  defense = None,
  primaryAbility = Some(PrimaryAbility(List("Gain 1 Combat", "Draw a card"))),
  allyAbility = Some(AllyAbility(List("Gain 2 Combat"))),
  scrapAbility = None,
  faction = Faction("star empire"),
  cardType = new Ship()
) {
  override def cardName = "Corvette"
  override def render(): String = {
    s"FactionCard($cardName, $cost, $defense, $primaryAbility, " +
      s"$allyAbility, $scrapAbility, ${faction.factionName}, ${cardType.cardType}) #StarEmpire.scala: Corvette"
  }
}


