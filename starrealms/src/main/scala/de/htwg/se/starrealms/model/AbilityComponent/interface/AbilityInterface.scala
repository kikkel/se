package de.htwg.se.starrealms.model.AbilityComponent.interface

trait AbilityInterface {
  def getActions: List[Action]
  def hasActions: Boolean
  def executeActions(): Unit
  def render: String
}