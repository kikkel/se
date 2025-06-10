package de.htwg.se.starrealms.model.AbilityComponent

import de.htwg.se.starrealms.model.AbilityComponent.Action
trait AbilityInterface {
  def getActions: List[Action]
  def hasActions: Boolean
  def executeActions(): Unit
  def render: String
}