package de.htwg.se.starrealms.model

//bridge

trait Card {
    def name: String
    def faction: Option[Faction]
    def cost: Option[Int]
    def defense: Option[String]
    def primaryAbility: Option[Ability]
    def allyAbility: Option[Ability]
    def scrapAbility: Option[Ability]
    def render(): String
}
trait CardType {
    def cardType: String
    def description: String
    def render(): String
}

class Ship extends CardType {
    override def cardType: String = "Ship"
    override def description: String = "pew pew #bridge"
    override def render(): String = "Ship #cardType"
}
class Base extends CardType {
    override def cardType: String = "Base"
    override def description: String = "chilling #bridge"
    override def render(): String = "Base #cardType"
    def isOutpost: Boolean

}

class FactionCard (
    override val name: String,
    override val cardType: CardType,
    override val faction: Option[Faction] = None,
    override val cost: Option[Int] = None,
    override val defense: Option[String] = None,
    override val isOutPost: Boolean = false,
    override val primaryAbility: Option[Ability] = None,
    override val allyAbility: Option[Ability] = None,
    override val scrapAbility: Option[Ability] = None
) extends Card {
    override def render(): String = {
        s"FactionCard Name: $name, " +
            s"Card Type: $cardType, " +
            s"Faction: ${faction.map(_.render()).getOrElse("None")}, " +
            s"Cost: ${cost.getOrElse("None")}, " +
            s"Defense: ${defense.getOrElse("None")}, Outpost: $isOutPost, " +
            s"Primary Ability: ${primaryAbility.map(_.render()).getOrElse("None")}, " +
            s"Ally Ability: ${allyAbility.map(_.render()).getOrElse("None")}, " +
            s"Scrap Ability: ${scrapAbility.map(_.render()).getOrElse("None")} #bridge"
    }
}

class DefaultCard (
    override val name: String,
    override val cardType: CardType,
    override val primaryAbility: Option[Ability] = None,
    override val scrapAbility: Option[Ability] = None
) extends Card {
    override def render(): String = {
        s"DefaultCard Name: $name, " +
            s"Card Type: ${cardType.render()}, " +
            s"Primary Ability: ${primaryAbility.map(_.render()).getOrElse("None")}, " +
            s"Scrap Ability: ${scrapAbility.map(_.render()).getOrElse("None")} #bridge"
    }
}

