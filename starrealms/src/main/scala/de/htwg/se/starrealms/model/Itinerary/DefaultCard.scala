package de.htwg.se.starrealms.model

import de.htwg.se.starrealms.model._


class Scout() extends Card {
  override def name = "Scout"
  override def primaryAbility = Some(new Ability(List("1 coin")))
  override def cardType = new Ship()
  override def render(): String = s"Scout: $name, Type: $cardType, Ability: ${primaryAbility.map(_.render()).getOrElse("None")}"
}

class Viper() extends Card {
  override def name = "Viper"
  override def primaryAbility = Some(new Ability(List("1 combat")))
  override def cardType = new Ship()
  override def render(): String = s"Viper: $name, Type: $cardType, Ability: ${primaryAbility.map(_.render()).getOrElse("None")}"
}

class Explorer() extends Card {
  override def name = "Explorer"
  override def primaryAbility = Some(new Ability(List("2 coins")))
  override def scrapAbility = Some(new Ability(List("Gain 2 combat")))
  override def cardType = new Ship()
  override def render(): String = s"Explorer Card: $name, Type: $cardType, Ability: ${primaryAbility.map(_.render()).getOrElse("None")}, Scrap Ability: ${scrapAbility.map(_.render()).getOrElse("None")}"
}
