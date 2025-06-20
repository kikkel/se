package de.htwg.se.starrealms.model.GameCore

trait ActionInterface { def description: String; def doMove: Unit }

trait AbilityInterface {
  def getActions: List[ActionInterface]
  def hasActions: Boolean
  def executeActions(): Unit
  def render: String
}

