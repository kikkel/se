package de.htwg.se.starrealms.model

abstract class AbstractCard(val name: String, val cardType: CardType, val ability: Ability) {
  def getName: String
  def getCardType: CardType
  def getAbility: Ability

  def render(): String = {
    s"Card Name: $name, Card Type: ${cardType.render()}, Ability: ${ability.render()}"
  }
/*   override def equals(obj: Any): Boolean = obj match { 
    case that: AbstractCard => this.name == that.name //
    case _ => false
  }

  override def hashCode(): Int = name.hashCode */
}