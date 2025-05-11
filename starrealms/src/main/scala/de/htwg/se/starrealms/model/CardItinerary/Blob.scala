package de.htwg.se.starrealms.model.CardItinerary

import de.htwg.se.starrealms.model._

case class TradePod() extends FactionCard(
  cardName = "Trade Pod",
  cost = Some(2),
  defense = None,
  primaryAbility = Some(PrimaryAbility(List("3 coins"))),
  allyAbility = Some(AllyAbility(List("2 coins"))),
  scrapAbility = None,
  faction = Faction("blob"),
  cardType = new Base(isOutpost = false)
) {
  override val cardName: String = "Trade Pod"
  override def render(): String = {
    s"FactionCard($cardName, $cost, $defense, $primaryAbility, " +
      s"$allyAbility, $scrapAbility, ${faction.factionName}, ${cardType.cardType}) #blob.scala: TradePod"
  }
}


