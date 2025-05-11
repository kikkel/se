package de.htwg.se.starrealms.model.CardItinerary

import de.htwg.se.starrealms.model._
/* import de.htwg.se.starrealms.model.CardBridge._
import de.htwg.se.starrealms.model.FactionFactory._ */

case class TradePod() extends Ship {
    override def name = "Trade Pod"
    override def faction = Faction("blob")
    override def cost = Some(2)
    override def primaryAbility = Some(PrimaryAbility(List("3 coins")))
    override def allyAbility = Some(AllyAbility(List("2 coins")))
    override def render(): String = s"Trade Pod"
}

