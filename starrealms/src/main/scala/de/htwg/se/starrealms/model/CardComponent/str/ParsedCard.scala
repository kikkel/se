package de.htwg.se.starrealms.model.CardComponent.str

import de.htwg.se.starrealms.model.CardComponent.interface._
import de.htwg.se.starrealms.model.EditionComponent.interface.Edition
import de.htwg.se.starrealms.model.AbilityComponent.interface._
import de.htwg.se.starrealms.model.AbilityComponent.impl._
import scala.util.{Try, Success, Failure}

case class ParsedCard(
    edition: Edition,
    cardName: String,
    cost: Option[Int],
    primaryAbility: Option[Ability],
    allyAbility: Option[Ability],
    scrapAbility: Option[Ability],
    faction: Faction,
    cardType: Try[CardType],
    qty: Int,
    role: String,
    notes: Option[String]
) extends Card {
    override def render(): String = {
        val cardTypeStr = cardType match {
            case Success(value) => value.toString
            case Failure(exception) => s"Error: ${exception.getMessage} #ParsedCard"
        }
        s"ParsedCard(${edition.nameOfEdition}, $cardName, $cost, ${primaryAbility.map(_.render()).getOrElse("None")}, " +
        s"${allyAbility.map(_.render()).getOrElse("None")}, ${scrapAbility.map(_.render()).getOrElse("None")}, " +
        s"${faction.factionName}, $cardTypeStr), ${notes.getOrElse("No notes")}) #BRIDGE: ParsedCard"
    }
}