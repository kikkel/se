package de.htwg.se.starrealms.model

//bridge

trait Card {
    def cardName: String
    def cost: Option[Int]
    def defense: Option[String]
    def primaryAbility: Option[Ability]
    def allyAbility: Option[Ability]
    def scrapAbility: Option[Ability]
    def render(): String
}
trait CardType {
    def cardType: String
    //def render(): String
}

class Ship extends CardType {
    override def cardType: String = "Ship"
    //override def render(): String = "Ship #cardType"
}
class Base extends CardType {
    override def cardType: String = "Base"
   // override def render(): String = "Base #cardType"
    def isOutpost: Boolean

}

class FactionCard extends Card {
    def faction: Faction
}



