package de.htwg.se.starrealms.model

import de.htwg.se.starrealms.model._


/* class Base(
  name: String,
  cardType: String = "Base",
  faction: Option[Faction] = None,
  cost: Int,
  defense: String,
  isOutPost: Boolean = false, // Hier der neue Parameter
  primaryAbility: Option[Ability] = None,
  allyAbility: Option[Ability] = None,
  scrapAbility: Option[Ability] = None
) extends Card(name, cardType, faction, Some(cost), Some(defense), None, primaryAbility, allyAbility, scrapAbility) {

  val outpost: Boolean = isOutPost
  def isOutpost: Boolean = outpost
  override def toString: String = {
  s"Base: $name, Defense: $defense, Outpost: $isOutpost, Abilities: " +
    s"Primary: ${primaryAbility.map(_.render()).getOrElse("None")}, " +
    s"Ally: ${allyAbility.map(_.render()).getOrElse("None")}, " +
    s"Scrap: ${scrapAbility.map(_.render()).getOrElse("None")}"
}
} */

/* class Ship(
  name: String,
  cardType: String = "Ship",
  faction: Option[Faction] = None,
  cost: Int,
  defense: String = "0",
  isOutPost: Boolean = false,
  primaryAbility: Option[Ability] = None,
  allyAbility: Option[Ability] = None,
  scrapAbility: Option[Ability] = None
) extends Card(name, cardType, faction, Some(cost), Some(defense), None, primaryAbility, allyAbility, scrapAbility) {
  override def toString: String = {
    s"Ship: $name, Abilities: " +
      s"Primary: ${primaryAbility.map(_.render()).getOrElse("None")}, " +
      s"Ally: ${allyAbility.map(_.render()).getOrElse("None")}, " +
      s"Scrap: ${scrapAbility.map(_.render()).getOrElse("None")}"
  }
} */

//----------------------------------------------------------------------------------------




//----------------------------------------------------------------------------------------
/* abstract class Faction(val name: String) {
  def getName: String  // Diese Methode bleibt abstrakt
  def render(): String = getName
}

class TradeFederation extends Faction("Trade Federation") {
  override def getName: String = "Trade Federation"
}

class StarEmpire extends Faction("Star Empire") {
  override def getName: String = "Star Empire"
}

class Blob extends Faction("Blob") {
  override def getName: String = "Blob"
}

class MachineCult extends Faction("Machine Cult") {
  override def getName: String = "Machine Cult"
} */


//----------------------------------------------------------------------------------------

class Ability(val actions: List[String]) {
  def getActions: List[String] = actions // Return the list of actions
  def hasActions: Boolean = actions.nonEmpty // Check if the list is not empty

  def render(): String = {
    if (actions.isEmpty) {
      "No actions available"
    } else {
      actions.mkString(", ")
    }
  } // Return a string representation of the actions


}

case class PrimaryAbility(override val actions: List[String]) extends Ability(actions) {
  override def render(): String = {
    if (actions.isEmpty) {
      "No primary actions available"
    } else {
      actions.mkString(", ")
    }
  } // Return a string representation of the primary actions
}

case class AllyAbility(override val actions: List[String]) extends Ability(actions) {
  override def render(): String = {
    if (actions.isEmpty) {
      "No ally actions available"
    } else {
      actions.mkString(", ")
    }
  } // Return a string representation of the ally actions
}
case class ScrapAbility(override val actions: List[String]) extends Ability(actions) {
  override def render(): String = {
    if (actions.isEmpty) {
      "No scrap actions available"
    } else {
      actions.mkString(", ")
    }
  } // Return a string representation of the scrap actions
}

//----------------------------------------------------------------------------------------
// Kosten einer Karte
class CardCost(val cost: Int) {
  def getCost: Int = cost // Return the cost of the card
  def isFree: Boolean = cost == 0 // Check if the card is free
}
//----------------------------------------------------------------------------------------
//Schaden einer Karte festlegen
class CardDamage(val damage: Int) {
  def getDamage: Int = damage // Return the damage of the card
  def isNoDamage: Boolean = damage == 0 // Check if the card has no damage
}
//----------------------------------------------------------------------------------------
//defense einer Karte festlegen
class CardDefense(val defense: Int) {
  def getDefense: Int = defense // Return the defense of the card
  def isNoDefense: Boolean = defense == 0 // Check if the card has no defense
}
//----------------------------------------------------------------------------------------

