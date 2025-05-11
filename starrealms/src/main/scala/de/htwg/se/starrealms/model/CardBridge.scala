package de.htwg.se.starrealms.model

//bridge

trait Card {
    def cardName: String
    def cost: Option[Int]
    def defense: Option[String]
    def primaryAbility: Option[Ability]
    def allyAbility: Option[Ability]
    def scrapAbility: Option[Ability]
    def render(): String = {
        s"Card: $cardName, Cost: ${cost.getOrElse("N/A")}, Defense: ${defense.getOrElse("N/A")}, " +
        s"Primary Ability: ${primaryAbility.map(_.render()).getOrElse("N/A")}, " +
        s"Ally Ability: ${allyAbility.map(_.render()).getOrElse("N/A")}, " +
        s"Scrap Ability: ${scrapAbility.map(_.render()).getOrElse("N/A")} #traitCard"
    }
}
trait CardType {
    def cardType: String
    def render(): String
}

class Ship extends CardType {
    override def cardType: String = "Ship"
    override def render(): String = "Ship #cardType"
}
class Base extends CardType {
    override def cardType: String = "Base"
    override def render(): String = "Base #cardType"
    def isOutpost: Boolean

}

class FactionCard extends Card {
    def faction: Faction
    override def render(): String = {
        s"FactionCard: $cardName, Cost: ${cost.getOrElse("N/A")}, Defense: ${defense.getOrElse("N/A")}, " +
        s"Primary Ability: ${primaryAbility.map(_.render()).getOrElse("N/A")}, " +
        s"Ally Ability: ${allyAbility.map(_.render()).getOrElse("N/A")}, " +
        s"Scrap Ability: ${scrapAbility.map(_.render()).getOrElse("N/A")} #factionCard"
    }
}



