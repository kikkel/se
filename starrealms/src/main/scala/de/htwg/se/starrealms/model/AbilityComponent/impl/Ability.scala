package de.htwg.se.starrealms.model.AbilityComponent.impl

import de.htwg.se.starrealms.model.AbilityComponent.{AbilityInterface, Action}


class Ability(val actions: List[Action]) extends AbilityInterface {
  override def getActions: List[Action] = actions
  override def hasActions: Boolean = actions.nonEmpty
  override def executeActions(): Unit = actions.foreach(_.doMove)

  override def render: String = {
    if (actions.isEmpty) "No actions available"
    else actions.map(a => s"${a.getClass.getSimpleName}(${a.description})").mkString(", ")
  }
}

case class PrimaryAbility(override val actions: List[Action]) extends Ability(actions) with AbilityInterface {
  override def render: String = {
    if (actions.isEmpty) "No primary actions available"
    else actions.map(a => s"${a.getClass.getSimpleName}(${a.description})").mkString(", ")
  }
}

case class AllyAbility(override val actions: List[Action]) extends Ability(actions) with AbilityInterface {
  override def render: String = {
    if (actions.isEmpty) "No ally actions available"
    else actions.map(a => s"${a.getClass.getSimpleName}(${a.description})").mkString(", ")
  }
}

case class ScrapAbility(override val actions: List[Action]) extends Ability(actions) with AbilityInterface {
  override def render: String = {
    if (actions.isEmpty) "No scrap actions available"
    else actions.map(a => s"${a.getClass.getSimpleName}(${a.description})").mkString(", ")
  }
}

/* case class PrimaryAbility(override val actions: List[Action]) extends Ability(actions) with AbilityInterface {
  override def render: String = {
    if (actions.isEmpty) "No primary actions available"
    else actions.map(_.description).mkString(", ")
  }
}

case class AllyAbility(override val actions: List[Action]) extends Ability(actions) with AbilityInterface {
  override def render: String = {
    if (actions.isEmpty) "No ally actions available"
    else actions.map(_.description).mkString(", ")
  }
}
case class ScrapAbility(override val actions: List[Action]) extends Ability(actions) with AbilityInterface {
  override def render: String = {
    if (actions.isEmpty) "No scrap actions available"
    else actions.map(_.description).mkString(", ")
  }
} */


