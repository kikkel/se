package de.htwg.se.starrealms.model

class Ability(val actions: List[String]) {
  def getActions: List[String] = actions // Return the list of actions
  def hasActions: Boolean = actions.nonEmpty // Check if the list is not empty

  def render(): String = {
    if (actions.isEmpty) {
      "No actions available"
    } else {
      actions.mkString(", ")
    }
  } // Return a string representation of the actions

}