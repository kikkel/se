package de.htwg.se.starrealms.model

class DefaultCard(name: String, cardType: CardType, ability: Ability) extends AbstractCard(name, cardType, ability) {
  def this(name: String, cardType: CardType) = this(name, cardType, new Ability(List())) // Default constructor with empty ability

  override def getName: String = name
  override def getCardType: CardType = new CardType(name) // Assuming card type is derived from name
  override def getAbility: Ability = ability
}

class ViperCard extends DefaultCard("Viper", new CardType("Default"), new Ability(List("1 damage"))) {
  override def render(): String = ???
}
/* class ViperCard extends DefaultCard("Viper", new Ability(List("1 damage"))) {
  override def toString: String = s"ViperCard(name=$name, ability=$ability)"
} */

class ScoutCard extends DefaultCard("Scout", new CardType("Default"), new Ability(List("1 coin"))) {
  override def toString: String = s"ScoutCard(name=$name, cardType=$cardType, ability=$ability)"
}
