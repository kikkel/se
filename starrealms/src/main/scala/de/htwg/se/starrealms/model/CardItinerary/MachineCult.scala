package de.htwg.se.starrealms.model.CardItinerary

import de.htwg.se.starrealms.model._

case class BattleStation() extends FactionCard(
  cardName = "Battle Station",
  cost = Some(4),
  defense = Some("5"),
  primaryAbility = Some(PrimaryAbility(List("2 damage"))),
  allyAbility = Some(AllyAbility(List("2 damage"))),
  scrapAbility = Some(ScrapAbility(List("Draw a card"))),
  faction = Faction("Machine Cult"),
  cardType = new Base(isOutpost = true)
) {
  override def cardName = "Battle Station"
  override def render(): String = {
    s"FactionCard($cardName, $cost, $defense, $primaryAbility, " +
      s"$allyAbility, $scrapAbility, ${faction.factionName}, ${cardType.cardType}) #MachineCult.scala: BattleStation"
  }
}


