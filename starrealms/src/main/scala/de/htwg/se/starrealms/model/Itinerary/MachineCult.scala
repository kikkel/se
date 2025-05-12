package de.htwg.se.starrealms.model.Itinerary

import de.htwg.se.starrealms.model._

case class BattleStation() extends FactionCard(
  set = Set("CoreSet"),
  cardName = "Battle Station",
  cost = Some(4),
  primaryAbility = Some(PrimaryAbility(List("2 damage"))),
  allyAbility = Some(AllyAbility(List("2 damage"))),
  scrapAbility = Some(ScrapAbility(List("Draw a card"))),
  faction = Faction("Machine Cult"),
  colour = "red",
  cardType = new Base("5", isOutpost = true)
) {
  override val cardName: String = "Battle Station"
  override def render(): String = {
    s"FactionCard($cardName, $cost, $primaryAbility, " +
      s"$allyAbility, $scrapAbility, ${faction.factionName}, ${cardType.cardType}) #MachineCult.scala: BattleStation"
  }
}


