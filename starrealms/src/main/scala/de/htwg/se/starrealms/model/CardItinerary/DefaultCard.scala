package de.htwg.se.starrealms.model

class Scout extends Ship(
  name = "Scout",
  cardType = "Default",
  primaryAbility = Some(new Ability(List("1 coin")))
) {
  override def render(): String = s"Scout Card: $name, Type: $cardType, Ability: ${primaryAbility.map(_.render()).getOrElse("None")}"
}

class Viper extends Ship(
  name = "Viper",
  cardType = "Default",
  primaryAbility = Some(new Ability(List("1 damage")))
) {
  override def render(): String = s"Viper Card: $name, Type: $cardType, Ability: ${primaryAbility.map(_.render()).getOrElse("None")}"
}

class Explorer extends Ship(
  name = "Explorer",
  cardType = "Explorer",
  primaryAbility = Some(new Ability(List("2 coins"))),
  scrapAbility = Some(new Ability(List("2 damage")))
) {
  override def render(): String = s"Explorer Card: $name, Type: $cardType, Ability: ${primaryAbility.map(_.render()).getOrElse("None")}, Scrap Ability: ${scrapAbility.map(_.render()).getOrElse("None")}"
}

