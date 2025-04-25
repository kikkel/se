package de.htwg.se.starrealms.model

abstract class AbstractCard(val name: String, val ability: Ability) {
  def getName: String = name
  def getAbility: Ability = ability

  override def toString: String = s"Card(name=$name, ability=$ability)"

  override def equals(obj: Any): Boolean = obj match {
    case that: AbstractCard => this.name == that.name
    case _ => false
  }

  override def hashCode(): Int = name.hashCode
}