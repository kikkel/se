package de.htwg.se.starrealms.model.CardComponent.impl

import de.htwg.se.starrealms.model.CardComponent.interface._
import de.htwg.se.starrealms.model.EditionComponent.interface.Edition
import de.htwg.se.starrealms.model.AbilityComponent.interface._
import scala.util.{Try, Success, Failure}

import de.htwg.se.starrealms.model.AbilityComponent.interface._

case class DefaultCard(
    override val edition: Edition,
    override val cardName: String,
    override val primaryAbility: Option[AbilityInterface],
    override val faction: Faction,
    override val cardType: Try[CardType],
    override val qty: Int,
    override val role: String


) extends Card {
    override def render(): String = {
        val cardTypeStr = cardType match {
            case Success(value) => value.toString
            case Failure(exception) => s"Error: ${exception.getMessage} #DefaultCard"
        }
        s"DefaultCard(${edition.nameOfEdition}, $cardName, " +
        s"${primaryAbility.map(_.render).getOrElse("None")}, ${faction.factionName} $cardTypeStr) #BRIDGE: DefaultCard"
    }
}