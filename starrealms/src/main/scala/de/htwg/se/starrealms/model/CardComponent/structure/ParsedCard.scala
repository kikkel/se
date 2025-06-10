package de.htwg.se.starrealms.model.CardComponent.structure

import de.htwg.se.starrealms.model.CardComponent._
import de.htwg.se.starrealms.model.EditionComponent.Edition
import de.htwg.se.starrealms.model.AbilityComponent._
import scala.util.{Try, Success, Failure}



case class ParsedCard(
    edition: Edition,
    cardName: String,
    cost: Option[Int],
    primaryAbility: Option[AbilityInterface],
    allyAbility: Option[AbilityInterface],
    scrapAbility: Option[AbilityInterface],
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
        s"ParsedCard(${edition.nameOfEdition}, $cardName, $cost, ${primaryAbility.map(_.render).getOrElse("None")}, " +
        s"${allyAbility.map(_.render).getOrElse("None")}, ${scrapAbility.map(_.render).getOrElse("None")}, " +
        s"${faction.factionName}, $cardTypeStr), ${notes.getOrElse("No notes")}) #BRIDGE: ParsedCard"
    }
}