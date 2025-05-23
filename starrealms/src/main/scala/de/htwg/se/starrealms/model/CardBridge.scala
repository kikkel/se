package de.htwg.se.starrealms.model

import scala.util.{Try, Success, Failure}

//bridge

trait Card {
    val set: Set
    val cardName: String
    val primaryAbility: Option[Ability]
    val faction: Faction
    def cardType: Try[CardType]
    val qty: Int
    val role: String
    def render(): String
}
trait CardType {
    def cardType: String
    //def render(): String
}

class Ship extends CardType {
    override def cardType: String = "Ship"
    //override def render(): String = "Ship #cardType"
}
class Base(val defense: String, val isOutpost: Boolean) extends CardType {
    override def cardType: String = "Base"
   // override def render(): String = "Base #cardType"

}

class FactionCard(
    override val set: Set,
    override val cardName: String,
    val cost: Int,
    override val primaryAbility: Option[Ability],
    val allyAbility: Option[Ability],
    val scrapAbility: Option[Ability],
    override val faction: Faction,
    override val cardType: Try[CardType],
    override val qty: Int,
    override val role: String
    ) extends Card {
        override def render(): String = {
            val cardTypeStr = cardType match {
                case scala.util.Success(value) => value.toString
                case scala.util.Failure(exception) => s"Error: ${exception.getMessage}"
            }
            s"FactionCard(${set.nameOfSet}, $cardName, $cost, ${primaryAbility.map(_.render()).getOrElse("None")}, " +
                s"${allyAbility.map(_.render()).getOrElse("None")}, ${scrapAbility.map(_.render()).getOrElse("None")}, " +
                s"${faction.factionName}, $cardTypeStr) #BRIDGE: FactionCard"
        }
}

case class DefaultCard(
    override val set: Set,
    override val cardName: String,
    override val primaryAbility: Option[Ability],
    override val faction: Faction,
    override val cardType: Try[CardType],
    override val qty: Int,
    override val role: String

) extends Card {
    override def render(): String = {
        val cardTypeStr = cardType match {
            case scala.util.Success(value) => value.toString
            case scala.util.Failure(exception) => s"Error: ${exception.getMessage}"
        }
        s"DefaultCard(${set.nameOfSet}, $cardName, " +
        s"${primaryAbility.map(_.render()).getOrElse("None")}, ${faction.factionName} $cardTypeStr) #BRIDGE: DefaultCard"
    }
}

class ExplorerCard(
    override val set: Set,
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
            case scala.util.Success(value) => value.toString
            case scala.util.Failure(exception) => s"Error: ${exception.getMessage}"
        }
        s"ExplorerCard(${set.nameOfSet}, $cardName, $cost, ${primaryAbility.map(_.render()).getOrElse("None")}, " +
        s"${scrapAbility.map(_.render()).getOrElse("None")}, ${faction.factionName}, $cardTypeStr) #BRIDGE: ExplorerCard"
    }
}