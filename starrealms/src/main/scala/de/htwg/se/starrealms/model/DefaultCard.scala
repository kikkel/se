package de.htwg.se.starrealms.model

class DefaultCard(name: String, ability: Ability) extends AbstractCard(name, ability) {
  def this(name: String) = this(name, new Ability(List())) // Default constructor with empty ability

  override def getName: String = name
  override def getAbility: Ability = ability
}

class ViperCard extends DefaultCard("Viper", new Ability(List("1 damage"))) {
  override def toString: String = s"ViperCard(name=$name, ability=$ability)"
}

class ScoutCard extends DefaultCard("Scout", new Ability(List("1 coin"))) {
  override def toString: String = s"ScoutCard(name=$name, ability=$ability)"
}
