/* package de.htwg.se.starrealms.model.Itinerary

import de.htwg.se.starrealms.model._

case class BarterWorld() extends FactionCard(
  set = Set("CoreSet"),
  cardName = "Barter World",
  cost = 4,
  primaryAbility = Some(PrimaryAbility(List("2 damage"))),
  allyAbility = Some(AllyAbility(List("2 damage"))),
  scrapAbility = Some(ScrapAbility(List("Draw a card"))),
  faction = Faction("Trade Federation"),
  colour = "yellow",
  cardType = new Base("5", isOutpost = true)
) {
  override val cardName: String = "Barter World"
  override def render(): String = {
    s"FactionCard($cardName, $cost, $primaryAbility, " +
      s"$allyAbility, $scrapAbility, ${faction.factionName}, ${cardType.cardType}) #tradefederation.scala: BarterWorld"
  }
}
 */