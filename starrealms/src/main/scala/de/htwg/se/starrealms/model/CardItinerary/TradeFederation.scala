package de.htwg.se.starrealms.model.CardItinerary

import de.htwg.se.starrealms.model._

case class BarterWorld() extends FactionCard(
  cardName = "Barter World",
  cost = Some(4),
  defense = Some("5"),
  primaryAbility = Some(PrimaryAbility(List("2 damage"))),
  allyAbility = Some(AllyAbility(List("2 damage"))),
  scrapAbility = Some(ScrapAbility(List("Draw a card"))),
  faction = Faction("Trade Federation"),
  cardType = new Base(isOutpost = true)
) {
  override def cardName = "Barter World"
  override def render(): String = {
    s"FactionCard($cardName, $cost, $defense, $primaryAbility, " +
      s"$allyAbility, $scrapAbility, ${faction.factionName}, ${cardType.cardType}) #tradefederation.scala: BarterWorld"
  }
}
