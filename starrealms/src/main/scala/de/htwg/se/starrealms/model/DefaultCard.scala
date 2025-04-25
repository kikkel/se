package de.htwg.se.starrealms.model

class DefaultCard(val name: String, val ability: Ability) {
  def this(name: String) = this(name, new Ability(List())) // Default constructor with empty ability

  def getName: String = name

  def getAbility: Ability = ability

  override def toString: String = s"Card(name=$name, ability=$ability)"
}

class ViperCard extends DefaultCard("Viper", new Ability(List("1 damage"))) {
  override def toString: String = s"ViperCard(name=$name, ability=$ability)"
}

class ScoutCard extends DefaultCard("Scout", new Ability(List("1 coin"))) {
  override def toString: String = s"ScoutCard(name=$name, ability=$ability)"
}
