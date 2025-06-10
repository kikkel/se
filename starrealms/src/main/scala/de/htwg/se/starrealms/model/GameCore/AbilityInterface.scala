package de.htwg.se.starrealms.model.GameCore

import de.htwg.se.starrealms.model.GameCore.Action

trait AbilityInterface {
  def getActions: List[Action]
  def hasActions: Boolean
  def executeActions(): Unit
  def render: String
}