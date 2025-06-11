package de.htwg.se.starrealms.model.GameCore

trait Action { def description: String; def doMove: Unit }

trait AbilityInterface {
  def getActions: List[Action]
  def hasActions: Boolean
  def executeActions(): Unit
  def render: String
}

