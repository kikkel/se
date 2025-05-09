package de.htwg.se.starrealms.model





class Base(
  name: String,
  cardType: String = "Base",
  faction: Option[Faction] = None,
  cost: Int,
  defense: String,
  isOutPost: Boolean = false, // Hier der neue Parameter
  primaryAbility: Option[Ability] = None,
  allyAbility: Option[Ability] = None,
  scrapAbility: Option[Ability] = None
) extends Card(name, cardType, faction, Some(cost), Some(defense), primaryAbility, allyAbility, scrapAbility) {

  val outpost: Boolean = isOutPost
  def isOutpost: Boolean = outpost
  override def toString: String = {
  s"Base: $name, Defense: $defense, Outpost: $isOutpost, Abilities: " +
    s"Primary: ${primaryAbility.map(_.render()).getOrElse("None")}, " +
    s"Ally: ${allyAbility.map(_.render()).getOrElse("None")}, " +
    s"Scrap: ${scrapAbility.map(_.render()).getOrElse("None")}"
}
}

//----------------------------------------------------------------------------------------




//----------------------------------------------------------------------------------------
/* abstract class Faction(val name: String) {
  def getName: String  // Diese Methode bleibt abstrakt
  def render(): String = getName
}

class TradeFederation extends Faction("Trade Federation") {
  override def getName: String = "Trade Federation"
}

class StarEmpire extends Faction("Star Empire") {
  override def getName: String = "Star Empire"
}

class Blob extends Faction("Blob") {
  override def getName: String = "Blob"
}

class MachineCult extends Faction("Machine Cult") {
  override def getName: String = "Machine Cult"
} */


//----------------------------------------------------------------------------------------

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

case class PrimaryAbility(override val actions: List[String]) extends Ability(actions) {
  override def render(): String = {
    if (actions.isEmpty) {
      "No primary actions available"
    } else {
      actions.mkString(", ")
    }
  } // Return a string representation of the primary actions
}

case class AllyAbility(override val actions: List[String]) extends Ability(actions) {
  override def render(): String = {
    if (actions.isEmpty) {
      "No ally actions available"
    } else {
      actions.mkString(", ")
    }
  } // Return a string representation of the ally actions
}
case class ScrapAbility(override val actions: List[String]) extends Ability(actions) {
  override def render(): String = {
    if (actions.isEmpty) {
      "No scrap actions available"
    } else {
      actions.mkString(", ")
    }
  } // Return a string representation of the scrap actions
}

