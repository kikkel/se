package de.htwg.se.starrealms.model

class Ability(val actions: List[String]) {
  def hasActions: Boolean = {
    actions.nonEmpty
  }

  def getActions: List[String] = {
    actions
  }

}
