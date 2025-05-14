package de.htwg.se.starrealms.model

import de.htwg.se.starrealms.model._


class Scout() extends Card {
  override val set = Set("CoreSet")
  override val cardName = "Scout"
  override val primaryAbility = Some(new Ability(List("1 coin")))
  override val faction = Faction("Unaligned")
  override def cardType = new Ship()
  override def render(): String = s"Scout: $cardName, Type: $cardType, Ability: ${primaryAbility.map(_.render()).getOrElse("None")}"
}

class Viper() extends Card {
  override val set = Set("CoreSet")
  override val cardName = "Viper"
  override val primaryAbility = Some(new Ability(List("1 combat")))
  override val faction = Faction("Unaligned")
  override def cardType = new Ship()
  override def render(): String = s"Viper: $cardName, Type: $cardType, Ability: ${primaryAbility.map(_.render()).getOrElse("None")}"
}

class Explorer() extends Card {
  override val set = Set("CoreSet")
  override val cardName = "Explorer"
  val cost = 2
  override val primaryAbility = Some(new Ability(List("2 coins")))
  val scrapAbility = Some(new Ability(List("Gain 2 combat")))
  override val faction = Faction("Unaligned")
  override def cardType = new Ship()
  override def render(): String = s"Explorer Card: $cardName, Cost: $cost, Type: $cardType, " +
    s"Primary Ability: ${primaryAbility.map(_.render()).getOrElse("None")}, " +
    s"Scrap Ability: ${scrapAbility.map(_.render()).getOrElse("None")}"
}
