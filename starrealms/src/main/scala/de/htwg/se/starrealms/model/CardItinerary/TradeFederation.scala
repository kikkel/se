package de.htwg.se.starrealms.model.CardItinerary

import de.htwg.se.starrealms.model._
import de.htwg.se.starrealms.model.CardBridge._
import de.htwg.se.starrealms.model.FactionFactory._

class TradeFederation(
    override val name: String = "Trade Federation",
    override val cardType: CardType = new Base,
    override val faction: Option[Faction] = Some(Faction("Trade Federation")),
    override val cost: Option[Int] = Some(3),
    override val defense: Option[String] = Some("2"),
    override val primaryAbility: Option[Ability] = Some(new Ability("Gain 1 trade.")),
    override val allyAbility: Option[Ability] = Some(new Ability("Gain 1 trade.")),
    override val scrapAbility: Option[Ability] = Some(new Ability("Scrap this card."))
) extends FactionCard {
  override def render(): String = super.render()

  override def getName: String = name
  override def getCardType: String = cardType.render()
  override def getFaction: Option[Faction] = faction
  override def getCost: Option[Int] = cost
  override def getDefense: Option[String] = defense
  override def isBase: Boolean = true
  override def isOutPost: Boolean = false

  override def getPrimaryAbility: Option[Ability] = primaryAbility
  override def getAllyAbility: Option[Ability] = allyAbility
  override def getScrapAbility: Option[Ability] = scrapAbility
  override def render(): String = super.render()

  override def toString: String = {
    s"TradeFederation: $name, Cost: $cost, Defense: $defense, " +
      s"Primary Ability: ${primaryAbility.map(_.render()).getOrElse("None")}, " +
      s"Ally Ability: ${allyAbility.map(_.render()).getOrElse("None")}, " +
      s"Scrap Ability: ${scrapAbility.map(_.render()).getOrElse("None")}"
  }
}

