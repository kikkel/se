package de.htwg.se.starrealms.model

//bridge

trait Card {
    val set: Set
    val cardName: String
    val primaryAbility: Option[Ability]
    val faction: Faction
    def cardType: CardType
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
    val cost: Int,
    override val primaryAbility: Option[Ability],
    val allyAbility: Option[Ability],
    val scrapAbility: Option[Ability],
    override val faction: Faction,
    override val cardType: CardType
    ) extends Card {
        override def render(): String = {
            s"FactionCard($set, $cardName, $cost, $primaryAbility, " +
                s"$allyAbility, $scrapAbility, ${faction.factionName}, ${cardType.cardType}) #BRIDGE: FactionCard"
    }
}

class DefaultCard(
    override val set: Set,
    override val cardName: String,
    override val primaryAbility: Option[Ability],
    override val faction: Faction,
    override val cardType: CardType
) extends Card {
    override def render(): String = {
        s"DefaultCard($set, $cardName, $primaryAbility, ${cardType.cardType}) #BRIDGE: DefaultCard"
    }
}

class ExplorerCard(
    override val set: Set,
    override val cardName: String,
    val cost: Int,
    override val primaryAbility: Option[Ability],
    val scrapAbility: Option[Ability],
    override val faction: Faction,
    override val cardType: CardType
) extends Card {
    override def render(): String = {
        s"ExplorerCard($set, $cardName, $cost, $primaryAbility, $scrapAbility, ${cardType.cardType}) #BRIDGE: ExplorerCard"
    }
}