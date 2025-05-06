package de.htwg.se.starrealms.model



abstract class Card(
  val name: String,
  val cardType: String,
  val faction: Option[Faction] = None,
  val cost: Option[Int] = None,
  val defense: Option[String] = None,
  val primaryAbility: Option[Ability] = None,
  val allyAbility: Option[Ability] = None,
  val scrapAbility: Option[Ability] = None
) {
  def isBase: Boolean = cardType == "Base"
  def isShip: Boolean = cardType == "Ship"
  def getName: String = name
  def getCardType: String = cardType
  def getFaction: Option[Faction] = faction
  def getCost: Option[Int] = cost
  def getDefense: Option[String] = defense
  def getPrimaryAbility: Option[Ability] = primaryAbility
  def getAllyAbility: Option[Ability] = allyAbility
  def getScrapAbility: Option[Ability] = scrapAbility

  // Überschreibe die toString-Methode
  override def toString: String = {
  s"$name ($cardType), Abilities: " +
    s"Primary: ${primaryAbility.map(_.render()).getOrElse("None")}, " +
    s"Ally: ${allyAbility.map(_.render()).getOrElse("None")}, " +
    s"Scrap: ${scrapAbility.map(_.render()).getOrElse("None")}"
}

  def render(): String = {
    s"Card Name: $name, Card Type: $cardType, Faction: ${faction.map(_.render()).getOrElse("None")}, primaryAbility: ${primaryAbility.map(_.render()).getOrElse("None")}, allyAbility: ${allyAbility.map(_.render()).getOrElse("None")}, scrapAbility: ${scrapAbility.map(_.render()).getOrElse("None")}"
  }
}

class Ship(
  name: String,
  cardType: String = "Ship",
  faction: Option[Faction] = None,
  cost: Option[Int] = None,
  primaryAbility: Option[Ability] = None,
  allyAbility: Option[Ability] = None,
  scrapAbility: Option[Ability] = None
) extends Card(name, cardType, faction, cost, None, primaryAbility, allyAbility, scrapAbility) {
  override def toString: String = {
  s"Ship: $name, Faction: ${faction.map(_.name).getOrElse("None")}, Cost: ${cost.getOrElse("Unknown")}, Abilities: " +
    s"Primary: ${primaryAbility.map(_.render()).getOrElse("None")}, " +
    s"Ally: ${allyAbility.map(_.render()).getOrElse("None")}, " +
    s"Scrap: ${scrapAbility.map(_.render()).getOrElse("None")}"
}
}

class Base(
  name: String,
  cardType: String = "Base",
  faction: Option[Faction] = None,
  cost: Int,
  defense: String,
  isOutpost: Boolean = false, // Hier der neue Parameter
  primaryAbility: Option[Ability] = None,
  allyAbility: Option[Ability] = None,
  scrapAbility: Option[Ability] = None
) extends Card(name, cardType, faction, Some(cost), Some(defense), primaryAbility, allyAbility, scrapAbility) {
  override def toString: String = {
  s"Base: $name, Defense: $defense, Outpost: $isOutpost, Abilities: " +
    s"Primary: ${primaryAbility.map(_.render()).getOrElse("None")}, " +
    s"Ally: ${allyAbility.map(_.render()).getOrElse("None")}, " +
    s"Scrap: ${scrapAbility.map(_.render()).getOrElse("None")}"
}
}

//----------------------------------------------------------------------------------------




//----------------------------------------------------------------------------------------
abstract class Faction(val name: String) {
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
}


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

