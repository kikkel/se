package de.htwg.se.starrealms.model

import de.htwg.se.starrealms.model._

class Ability(val actions: List[String]) {
  def getActions: List[String] = actions
  def hasActions: Boolean = actions.nonEmpty

  def render(): String = {
    val result = actions
        .filter(_.nonEmpty)
        .map(_.mkString(", "))
        .headOption
        .getOrElse("No actions available")
    result
  }
}

case class PrimaryAbility(override val actions: List[String]) extends Ability(actions) {
  override def render(): String = {
    val result = actions
        .filter(_.nonEmpty)
        .map(_.mkString(", "))
        .headOption
        .getOrElse("No primary actions available")
    result
  } 
}

case class AllyAbility(override val actions: List[String]) extends Ability(actions) {
  override def render(): String = {
    val result = actions
        .filter(_.nonEmpty)
        .map(_.mkString(", "))
        .headOption
        .getOrElse("No ally actions available")
    result
  } 
}
case class ScrapAbility(override val actions: List[String]) extends Ability(actions) {
  override def render(): String = {
    val result = actions
        .filter(_.nonEmpty)
        .map(_.mkString(", "))
        .headOption
        .getOrElse("No scrap actions available")
    result
  } 
}

//----------------------------------------------------------------------------------------
class CardCost(val cost: Int) {
  def getCost: Int = cost 
  def isFree: Boolean = cost == 0 
}
//----------------------------------------------------------------------------------------
class CardDamage(val damage: Int) {
  def getDamage: Int = damage 
  def isNoDamage: Boolean = damage == 0 
}
//----------------------------------------------------------------------------------------
class CardDefense(val defense: Int) {
  def getDefense: Int = defense 
  def isNoDefense: Boolean = defense == 0 
}
//----------------------------------------------------------------------------------------

class Healing(val healing: Int) {
  def getHealing: Int = healing 
  def isNoHealing: Boolean = healing == 0 
}
