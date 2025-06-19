package de.htwg.se.starrealms.model.GameCore.impl

import de.htwg.se.starrealms.model.GameCore.{AbilityInterface, ActionInterface}
import scala.util.matching.Regex

// ===== Ability-Implementierungen =====

class Ability(val actions: List[ActionInterface]) extends AbilityInterface {
  override def getActions: List[ActionInterface] = actions
  override def hasActions: Boolean = actions.nonEmpty
  override def executeActions(): Unit = actions.foreach(_.doMove)
  override def render: String = if (actions.isEmpty) "None" else actions.map(_.description).mkString(", ")
}

case class PrimaryAbility(override val actions: List[ActionInterface]) extends Ability(actions) {
  override def render: String =
    if (actions.isEmpty) "No primary actions available"
    else actions.map(a => s"${a.getClass.getSimpleName}(${a.description})").mkString(", ")
}

case class AllyAbility(override val actions: List[ActionInterface]) extends Ability(actions) {
  override def render: String =
    if (actions.isEmpty) "No ally actions available"
    else actions.map(a => s"${a.getClass.getSimpleName}(${a.description})").mkString(", ")
}

case class ScrapAbility(override val actions: List[ActionInterface]) extends Ability(actions) {
  override def render: String =
    if (actions.isEmpty) "No scrap actions available"
    else actions.map(a => s"${a.getClass.getSimpleName}(${a.description})").mkString(", ")
}

// ===== Action-Implementierungen =====

trait ActionBase extends ActionInterface {
  override def description: String = this.getClass.getSimpleName
}

case class SimpleAction(override val description: String) extends ActionInterface {
  override def doMove: Unit = println(description)
}

case class ConditionalAction(condition1: ActionInterface, condition2: ActionInterface) extends ActionInterface {
  override def doMove: Unit = {
    println("Condition met:")
    condition1.doMove
    condition2.doMove
  }
  override def description: String = s"Condition: ${condition1.description}, ${condition2.description}"
}

case class TriggeredAction(trigger: String, action: ActionInterface) extends ActionInterface {
  override def doMove: Unit = {
    println(s"Triggered by $trigger:")
    action.doMove
  }
  override def description: String = s"Triggered by $trigger: ${action.description}"
}

case class CompositeAction(actions: List[ActionInterface]) extends ActionInterface {
  override def doMove: Unit = {
    println("Composite action:")
    actions.foreach(_.doMove)
  }
  override def description: String = s"Composite action: ${actions.map(_.description).mkString(", ")}"
}

// ===== Beispiel-Action für Star Realms =====

case class GainCombatAction(amount: Int) extends ActionInterface {
  override def description: String = s"Gain $amount Combat"
  override def doMove: Unit = println(s"Player gains $amount Combat!")
}

case class GainTradeAction(amount: Int) extends ActionInterface {
  override def description: String = s"Gain $amount Trade"
  override def doMove: Unit = println(s"Player gains $amount Trade!")
}

case object DrawCardAction extends ActionInterface {
  override def description: String = "Draw a card"
  override def doMove: Unit = println("Player draws a card!")
}

// ===== AbilityFactory =====

object AbilityFactory {
  def fromText(text: String): AbilityInterface = {
    val actions = parseActions(text)
    new Ability(actions)
  }

  private def parseActions(text: String): List[ActionInterface] = {
    val gainCombat: Regex = """\{Gain (\d+) Combat\}""".r
    val gainTrade: Regex = """\{Gain (\d+) Trade\}""".r
    val drawCard: Regex = """Draw a card""".r

    val actions = scala.collection.mutable.ListBuffer.empty[ActionInterface]

    // Combat
    gainCombat.findAllMatchIn(text).foreach(m =>
      actions += GainCombatAction(m.group(1).toInt)
    )
    // Trade
    gainTrade.findAllMatchIn(text).foreach(m =>
      actions += GainTradeAction(m.group(1).toInt)
    )
    // Draw Card
    if (drawCard.findFirstIn(text).isDefined)
      actions += DrawCardAction

    actions.toList
  }
}