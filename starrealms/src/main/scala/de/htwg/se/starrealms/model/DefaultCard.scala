package de.htwg.se.starrealms.model

abstract class DefaultCard(val name: String, val ability: Ability) {
  def getName: String = name
  def getAbility: Ability = ability
}

class ScoutCard(override val ability: Ability = new Ability(List("1 coin")))
  extends DefaultCard("Scout", ability)

class ViperCard(override val ability: Ability = new Ability(List("1 damage")))
  extends DefaultCard("Viper", ability)