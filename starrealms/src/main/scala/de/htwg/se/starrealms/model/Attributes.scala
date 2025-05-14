package de.htwg.se.starrealms.model

import de.htwg.se.starrealms.model._

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

