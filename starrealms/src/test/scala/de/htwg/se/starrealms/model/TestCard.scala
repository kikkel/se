package de.htwg.se.starrealms.model

class TestCard(name: String, ability: Ability) extends AbstractCard(name, ability) {
    def this(name: String) = this(name, new Ability(List())) // Default constructor with empty ability
    
    override def getName: String = name
    override def getCardType: CardType = new CardType(name) // Assuming card type is derived from name
    override def getAbility: Ability = ability

    override def toString: String = s"TestCard(name=$name, ability=$ability)"    
}