package de.htwg.se.starrealms.model

import de.htwg.se.starrealms.model._

class TestCard(name: String, cardType: String, ability: Ability)
  extends Card(name, cardType, None, None, None, Some(ability), None, None) {

    def this(name: String) = this(name, "TestCardType", new Ability(List()))

    override def isBase: Boolean = false
    override def isShip: Boolean = true
    override def getName: String = name
    override def getCardType: String = cardType
    override def getFaction: Option[Faction] = None
    override def getPrimaryAbility: Option[Ability] = Some(ability)
    override def getAllyAbility: Option[Ability] = None
    override def getScrapAbility: Option[Ability] = None

    override def render(): String = {
        s"Card Name: $name, Card Type: $cardType, Faction: None, Primary Ability: ${ability.render()}, Ally Ability: None, Scrap Ability: None"
    }

    override def toString: String = s"TestCard(name=$name, cardType=$cardType, ability=$ability)"
}