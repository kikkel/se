package de.htwg.se.starrealms.model

class TestCard(name: String, cardType: CardType, ability: Ability) extends Card(name, cardType, ability) {
    def this(name: String) = this(name, new CardType("TestCardType"), new Ability(List())) // Default constructor with empty ability
    
    override def isBase: Boolean = false
    override def isShip: Boolean = true
    override def getName: String = name
    override def getCardType: CardType = cardType
    override def getFaction: Option[Faction] = None
    override def getPrimaryAbility: Option[Ability] = Some(ability)
    override def getAllyAbility: Option[Ability] = None
    override def getScrapAbility: Option[Ability] = None
    override def render(): String = {
        s"Card Name: $name, Card Type: ${cardType.render()}, Faction: None, Primary Ability: ${ability.render()}, Ally Ability: None, Scrap Ability: None"
    }
    

    override def toString: String = s"TestCard(name=$name, cardType=$cardType, ability=$ability)"    
}