package de.htwg.se.starrealms.model

abstract class Card(
  val name: String, 
  val cardType: String, 
  val faction: Option[Faction] = None,
  val primaryAbility: Option[Ability] = None,
  val allyAbility: Option[Ability] = None,
  val scrapAbility: Option[Ability] = None
) {
  def isBase: Boolean
  def isShip: Boolean
  def getName: String = name
  def getCardType: String = cardType
  def getFaction: Option[Faction] = faction
  def getPrimaryAbility: Option[Ability] = primaryAbility
  def getAllyAbility: Option[Ability] = allyAbility
  def getScrapAbility: Option[Ability] = scrapAbility

  def render(): String = {
    s"Card Name: $name, Card Type: $cardType, Faction: ${faction.map(_.render()).getOrElse("None")}, primaryAbility: ${primaryAbility.map(_.render()).getOrElse("None")}, allyAbility: ${allyAbility.map(_.render()).getOrElse("None")}, scrapAbility: ${scrapAbility.map(_.render()).getOrElse("None")}"
  }
}

case class Ship(
  name: String, 
  cardType: String = "Ship", 
  faction: Option[Faction] = None,
  primaryAbility: Option[Ability] = None,
  allyAbility: Option[Ability] = None,
  scrapAbility: Option[Ability] = None
) extends Card(name, cardType, faction, primaryAbility, allyAbility, scrapAbility) {
  override def isBase: Boolean = false
  override def isShip: Boolean = true
}

case class Base(
  name: String, 
  cardType: String = "Base", 
  faction: Option[Faction] = None,
  primaryAbility: Option[Ability] = None,
  allyAbility: Option[Ability] = None,
  scrapAbility: Option[Ability] = None
) extends Card(name, cardType, faction, primaryAbility, allyAbility, scrapAbility) {
  override def isBase: Boolean = true
  override def isShip: Boolean = false

  def isOutPost: Boolean = isOutPost
}

//----------------------------------------------------------------------------------------




//----------------------------------------------------------------------------------------
case class Faction(val name: String) {
    def getName: String = name
    def render(): String = name // Return the name of the faction    
    override def equals(obj: Any): Boolean = obj match {
        case that: Faction => this.name == that.name
        case _ => false
    }
    //override def hashCode(): Int = name.hashCode
}
class TradeFederation extends Faction("Trade Federation") {
    override def render(): String = "Trade Federation"
}
class StarEmpire extends Faction("Star Empire") {
    override def render(): String = "Star Empire"
}
class Blob extends Faction("Blob") {
    override def render(): String = "Blob"
}
class MachineCult extends Faction("MachineCult") {
    override def render(): String = "Machine Cult"
}


//----------------------------------------------------------------------------------------

case class Ability(val actions: List[String]) {
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



