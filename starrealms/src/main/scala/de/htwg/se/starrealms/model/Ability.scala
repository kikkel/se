package de.htwg.se.starrealms.model

class Ability(val actions: List[String]) {
  def getActions: List[String] = actions // Return the list of actions
  def hasActions: Boolean = actions.nonEmpty // Check if the list is not empty

  override def toString: String = s"Ability(actions=$actions)" // String representation of the Ability class
/*   override def equals(obj: Any): Boolean = obj match {
    case that: Ability => this.actions == that.actions // Compare actions for equality
    case _ => false
  }
  override def hashCode(): Int = actions.hashCode // Hash code based on actions
  def addAction(action: String): Ability = {
    new Ability(actions :+ action) // Create a new Ability with the added action
  }
  def removeAction(action: String): Ability = {
    new Ability(actions.filterNot(_ == action)) // Create a new Ability without the specified action
  }
  def clearActions(): Ability = {
    new Ability(List()) // Create a new Ability with an empty action list
  }
   */

}