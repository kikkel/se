package de.htwg.se.starrealms.model

//bridge

trait Card {
    def set: Set
    def cardName: String
    def cost: Option[Int]
    //def defense: Option[String]
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
class Base(val defense: String, val isOutpost: Boolean) extends CardType {
    override def cardType: String = "Base"
   // override def render(): String = "Base #cardType"

}

class FactionCard(
    override val set: Set,
    override val cardName: String,
    override val cost: Option[Int],
    //override val defense: Option[String],
    override val primaryAbility: Option[Ability],
    override val allyAbility: Option[Ability],
    override val scrapAbility: Option[Ability],
    val faction: Faction,
    val colour: String,
    val cardType: CardType
    ) extends Card {
    override def render(): String = {
        s"FactionCard($set, $cardName, $cost, $primaryAbility, " +
            s"$allyAbility, $scrapAbility, ${faction.factionName}, ${cardType.cardType}) #BRIDGE: FactionCard"
    }

}



