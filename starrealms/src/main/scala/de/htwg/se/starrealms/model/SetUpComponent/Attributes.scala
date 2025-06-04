package de.htwg.se.starrealms.model.SetUpComponent

import de.htwg.se.starrealms.model._

sealed trait Action { def description: String; def doMove: Unit }

case class SimpleAction(description: String) extends Action { override def doMove: Unit = println(description) }
case class ConditionalAction(condition1: Action, condition2: Action) extends Action {
  override def doMove: Unit = {
    println("Condition met:")
    condition1.doMove
    condition2.doMove
  }
  override def description: String = s"Condition: $condition1, $condition2"
}
case class TriggeredAction(trigger: String, action: Action) extends Action {
  override def doMove: Unit = {
    println(s"Triggered by $trigger:")
    action.doMove
  }
  override def description: String = s"Triggered by $trigger: $action"
}
case class CompositeAction(actions: List[Action]) extends Action {
  override def doMove: Unit = {
    println("Composite action:")
    actions.foreach(_.doMove)
  }
  override def description: String = s"Composite action: ${actions.map(_.description).mkString(", ")}"
}
/* case class CoinAction(amount: Int) extends Action { override def doMove: Unit = println(s"$amount coins") }
case class CombatAction(amount: Int) extends Action { override def doMove: Unit = println(s"$amount damage") }
case class HealingAction(amount: Int) extends Action { override def doMove: Unit = println(s"heal $amount") }
case class ComplexAction(description: String) extends Action { override def doMove: Unit = println(description) }
 */
class Ability(val actions: List[Action]) {
  def getActions: List[Action] = actions
  def hasActions: Boolean = actions.nonEmpty
  def executeActions(): Unit = actions.foreach(_.doMove)

  def render(): String = {
    if (actions.isEmpty) "No actions available"
    else actions.map(a => s"${a.getClass.getSimpleName}(${a.description})").mkString(", ")
  }
}

case class PrimaryAbility(override val actions: List[Action]) extends Ability(actions) {
  override def render(): String = {
    if (actions.isEmpty) "No primary actions available"
    else actions.map(a => s"${a.getClass.getSimpleName}(${a.description})").mkString(", ")
  }
}

case class AllyAbility(override val actions: List[Action]) extends Ability(actions) {
  override def render(): String = {
    if (actions.isEmpty) "No ally actions available"
    else actions.map(a => s"${a.getClass.getSimpleName}(${a.description})").mkString(", ")
  }
}

case class ScrapAbility(override val actions: List[Action]) extends Ability(actions) {
  override def render(): String = {
    if (actions.isEmpty) "No scrap actions available"
    else actions.map(a => s"${a.getClass.getSimpleName}(${a.description})").mkString(", ")
  }
}

/* case class PrimaryAbility(override val actions: List[Action]) extends Ability(actions) {
  override def render(): String = {
    if (actions.isEmpty) "No primary actions available"
    else actions.map(_.description).mkString(", ")
  }
}

case class AllyAbility(override val actions: List[Action]) extends Ability(actions) {
  override def render(): String = {
    if (actions.isEmpty) "No ally actions available"
    else actions.map(_.description).mkString(", ")
  }
}
case class ScrapAbility(override val actions: List[Action]) extends Ability(actions) {
  override def render(): String = {
    if (actions.isEmpty) "No scrap actions available"
    else actions.map(_.description).mkString(", ")
  }
} */


