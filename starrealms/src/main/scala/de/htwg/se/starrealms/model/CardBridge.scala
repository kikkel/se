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
class Base(val isOutpost: Boolean) extends CardType {
    override def cardType: String = "Base"
   // override def render(): String = "Base #cardType"

}

class FactionCard(
    override val cardName: String,
    override val cost: Option[Int],
    override val defense: Option[String],
    override val primaryAbility: Option[Ability],
    override val allyAbility: Option[Ability],
    override val scrapAbility: Option[Ability],
    val faction: Faction,
    val cardType: CardType
    ) extends Card {
    override def render(): String = {
        s"FactionCard($cardName, $cost, $defense, $primaryAbility, $allyAbility, $scrapAbility, ${faction.factionName}, ${cardType.cardType}) #BRIDGE: FactionCard"
    }

}



