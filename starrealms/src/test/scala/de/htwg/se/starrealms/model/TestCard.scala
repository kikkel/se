package de.htwg.se.starrealms.model

class TestCard(name: String, cardType: CardType, ability: Ability) extends AbstractCard(name, cardType, ability) {
    def this(name: String) = this(name, new CardType("TestCardType"), new Ability(List())) // Default constructor with empty ability
    
    override def getName: String = name
    override def getCardType: CardType = cardType
    override def getAbility: Ability = ability

    override def toString: String = s"TestCard(name=$name, cardType=$cardType, ability=$ability)"    
}