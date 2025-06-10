package de.htwg.se.starrealms.model.GameCore.impl

import de.htwg.se.starrealms.model.GameCore.{Card, CardType, Faction, AbilityInterface}
import de.htwg.se.starrealms.model.EditionComponent.Edition
import scala.util.{Try, Success, Failure}

import de.htwg.se.starrealms.model.GameCore._

case class FactionCard(
    override val edition: Edition,
    override val cardName: String,
    val cost: Int,
    override val primaryAbility: Option[AbilityInterface],
    val allyAbility: Option[AbilityInterface],
    val scrapAbility: Option[AbilityInterface],
    override val faction: Faction,
    override val cardType: Try[CardType],
    override val qty: Int,
    override val role: String,
    val notes: Option[String]
    ) extends Card {
        override def render(): String = {
            val cardTypeStr = cardType match {
                case Success(value) => value.toString
                case Failure(exception) => s"Error: ${exception.getMessage} #FactionCard"
            }
            s"FactionCard(${edition.nameOfEdition}, $cardName, $cost, ${primaryAbility.map(_.render).getOrElse("None")}, " +
                s"${allyAbility.map(_.render).getOrElse("None")}, ${scrapAbility.map(_.render).getOrElse("None")}, " +
                s"${faction.factionName}, $cardTypeStr), ${notes.getOrElse("No notes")}) #BRIDGE: FactionCard"
        }
}