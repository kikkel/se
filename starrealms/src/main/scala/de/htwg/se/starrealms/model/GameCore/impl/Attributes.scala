package de.htwg.se.starrealms.model.GameCore.impl

import de.htwg.se.starrealms.model.GameCore.{AbilityInterface, ActionInterface}
import de.htwg.se.starrealms.di.StarRealmsModule
import com.google.inject.{Guice, Inject}


class Ability @Inject() (val actions: List[ActionInterface]) extends AbilityInterface {
  override def getActions: List[ActionInterface] = actions
  override def hasActions: Boolean = actions.nonEmpty
  override def executeActions(): Unit = actions.foreach(_.doMove)

  override def render: String = {
    if (actions.isEmpty) "None"
    else actions.map(_.description).mkString(", ")
  }
}

case class PrimaryAbility(override val actions: List[ActionInterface]) extends Ability(actions) with AbilityInterface {
  override def render: String = {
    if (actions.isEmpty) "No primary actions available"
    else actions.map(a => s"${a.getClass.getSimpleName}(${a.description})").mkString(", ")
  }
}

case class AllyAbility(override val actions: List[ActionInterface]) extends Ability(actions) with AbilityInterface {
  override def render: String = {
    if (actions.isEmpty) "No ally actions available"
    else actions.map(a => s"${a.getClass.getSimpleName}(${a.description})").mkString(", ")
  }
}

case class ScrapAbility(override val actions: List[ActionInterface]) extends Ability(actions) with AbilityInterface {
  override def render: String = {
    if (actions.isEmpty) "No scrap actions available"
    else actions.map(a => s"${a.getClass.getSimpleName}(${a.description})").mkString(", ")
  }
}

/* case class PrimaryAbility(override val actions: List[ActionInterface]) extends Ability(actions) with AbilityInterface {
  override def render: String = {
    if (actions.isEmpty) "No primary actions available"
    else actions.map(_.description).mkString(", ")
  }
}

case class AllyAbility(override val actions: List[ActionInterface]) extends Ability(actions) with AbilityInterface {
  override def render: String = {
    if (actions.isEmpty) "No ally actions available"
    else actions.map(_.description).mkString(", ")
  }
}
case class ScrapAbility(override val actions: List[ActionInterface]) extends Ability(actions) with AbilityInterface {
  override def render: String = {
    if (actions.isEmpty) "No scrap actions available"
    else actions.map(_.description).mkString(", ")
  }
} */

abstract class Action @Inject() extends ActionInterface {
  def doMove: Unit
  def description: String = this.getClass.getSimpleName
}
case class SimpleAction(description: String) extends ActionInterface { override def doMove: Unit = println(description) }
case class ConditionalAction(condition1: ActionInterface, condition2: ActionInterface) extends ActionInterface {
  override def doMove: Unit = {
    println("Condition met:")
    condition1.doMove
    condition2.doMove
  }
  override def description: String = s"Condition: $condition1, $condition2"
}
case class TriggeredAction(trigger: String, action: ActionInterface) extends ActionInterface {
  override def doMove: Unit = {
    println(s"Triggered by $trigger:")
    action.doMove
  }
  override def description: String = s"Triggered by $trigger: $action"
}
case class CompositeAction(actions: List[ActionInterface]) extends ActionInterface {
  override def doMove: Unit = {
    println("Composite action:")
    actions.foreach(_.doMove)
  }
  override def description: String = s"Composite action: ${actions.map(_.description).mkString(", ")}"
}

/* case class CoinAction(amount: Int) extends ActionInterface with Action { override def doMove: Unit = println(s"$amount coins") }
case class CombatAction(amount: Int) extends ActionInterface with Action { override def doMove: Unit = println(s"$amount damage") }
case class HealingAction(amount: Int) extends ActionInterface with Action { override def doMove: Unit = println(s"heal $amount") }
case class ComplexAction(description: String) extends ActionInterface with Action { override def doMove: Unit = println(description) }
 */