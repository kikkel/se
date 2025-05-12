package de.htwg.se.starrealms.model.Itinerary

import de.htwg.se.starrealms.model._

case class TradePod() extends FactionCard(
  set = Set("CoreSet"),
  cardName = "Trade Pod",
  cost = Some(2),
  primaryAbility = Some(PrimaryAbility(List("3 coins"))),
  allyAbility = Some(AllyAbility(List("2 coins"))),
  scrapAbility = None,
  faction = Faction("blob"),
  cardType = new Base("", isOutpost = false)
) {
  override val cardName: String = "Trade Pod"
  override def render(): String = {
    s"FactionCard($cardName, $cost, $primaryAbility, " +
      s"$allyAbility, $scrapAbility, ${faction.factionName}, ${cardType.cardType}) #blob.scala: TradePod"
  }
}


