package de.htwg.se.starrealms.model

import scala.util.{Try, Success, Failure}

//bridge

trait Card {
    val edition: Edition
    val cardName: String
    def combat: Int = 0 // musste ich rein machen sonst errorrrrrr
    val primaryAbility: Option[Ability]
    val faction: Faction
    def cardType: Try[CardType]
    val qty: Int
    val role: String
    def render(): String
}
trait CardType {
    def cardType: String
}

class Ship extends CardType {
    override def cardType: String = "Ship"
}
class Base(val defense: String, val isOutpost: Boolean) extends CardType {
    override def cardType: String = "Base"

}

case class FactionCard(
    override val edition: Edition,
    override val cardName: String,
    val cost: Int,
    override val primaryAbility: Option[Ability],
    val allyAbility: Option[Ability],
    val scrapAbility: Option[Ability],
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
            s"FactionCard(${edition.nameOfEdition}, $cardName, $cost, ${primaryAbility.map(_.render()).getOrElse("None")}, " +
                s"${allyAbility.map(_.render()).getOrElse("None")}, ${scrapAbility.map(_.render()).getOrElse("None")}, " +
                s"${faction.factionName}, $cardTypeStr), ${notes.getOrElse("No notes")}) #BRIDGE: FactionCard"
        }
}

case class DefaultCard(
    override val edition: Edition,
    override val cardName: String,
    override val primaryAbility: Option[Ability],
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
        s"${primaryAbility.map(_.render()).getOrElse("None")}, ${faction.factionName} $cardTypeStr) #BRIDGE: DefaultCard"
    }
}

case class ExplorerCard(
    override val edition: Edition,
    override val cardName: String,
    val cost: Int,
    override val primaryAbility: Option[Ability],
    val scrapAbility: Option[Ability],
    override val faction: Faction,
    override val cardType: Try[CardType],
    override val qty: Int,
    override val role: String
) extends Card {
    override def render(): String = {
        val cardTypeStr = cardType match {
            case Success(value) => value.toString
            case Failure(exception) => s"Error: ${exception.getMessage} #ExplorerCard"
        }
        s"ExplorerCard(${edition.nameOfEdition}, $cardName, $cost, ${primaryAbility.map(_.render()).getOrElse("None")}, " +
        s"${scrapAbility.map(_.render()).getOrElse("None")}, ${faction.factionName}, $cardTypeStr) #BRIDGE: ExplorerCard"
    }
}

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