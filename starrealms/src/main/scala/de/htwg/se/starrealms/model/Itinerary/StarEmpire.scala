/* package de.htwg.se.starrealms.model.Itinerary

import de.htwg.se.starrealms.model._

case class Corvette() extends FactionCard(
  set = Set("CoreSet"),
  cardName = "Corvette",
  cost = 2,
  primaryAbility = Some(PrimaryAbility(List("Gain 1 Combat", "Draw a card"))),
  allyAbility = Some(AllyAbility(List("Gain 2 Combat"))),
  scrapAbility = None,
  faction = Faction("star empire"),
  colour = "blue",
  cardType = new Ship()
) {
  override val cardName: String = "Corvette"
  override def render(): String = {
    s"FactionCard($cardName, $cost, $primaryAbility, " +
      s"$allyAbility, $scrapAbility, ${faction.factionName}, ${cardType.cardType}) #StarEmpire.scala: Corvette"
  }
}


 */