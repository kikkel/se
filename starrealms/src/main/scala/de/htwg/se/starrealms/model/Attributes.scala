package de.htwg.se.starrealms.model

import de.htwg.se.starrealms.model._

trait Action { def doMove: Unit }

class CoinAction(amount: Int) extends Action { override def doMove: Unit = println(s"$amount coins") }
class CombatAction(amount: Int) extends Action { override def doMove: Unit = println(s"$amount damage") }
class HealingAction(amount: Int) extends Action { override def doMove: Unit = println(s"heal $amount") }
class ComplexAction(description: String) extends Action { override def doMove: Unit = println(description) }

class Ability(val actions: List[Action]) {
  def getActions: List[String] = actions
  def hasActions: Boolean = actions.nonEmpty
  def executeActions(): Unit = actions.foreach(_.doMove)

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


