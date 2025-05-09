package de.htwg.se.starrealms.model.CardItinerary

import de.htwg.se.starrealms.model._
import de.htwg.se.starrealms.model.CardBridge._
import de.htwg.se.starrealms.model.FactionFactory._

case class TradePod() extends Ship(
    name = "Trade Pod",
    cardType = "Ship",
    faction = Some(new Blob),
    cost = Some(2),
    primaryAbility = Some(PrimaryAbility(List("3 coins"))),
    allyAbility = Some(AllyAbility(List("2 coins")))
  ) {
    override def render(): String = {
      s"Trade Pod Card"
    }
}

