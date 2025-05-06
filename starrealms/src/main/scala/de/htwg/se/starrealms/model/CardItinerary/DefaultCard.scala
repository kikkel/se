package de.htwg.se.starrealms.model

class ScoutCard extends Card(
  name = "Scout",
  cardType = new CardType("Default"),
  primaryAbility = Some(new Ability(List("1 coin")))
) {
  override def render(): String = s"Scout Card: $name, Type: ${cardType.render()}, Ability: ${primaryAbility.map(_.render()).getOrElse("None")}"
}

class ViperCard extends Card(
  name = "Viper",
  cardType = new CardType("Default"),
  primaryAbility = Some(new Ability(List("1 damage")))
) {
  override def render(): String = s"Viper Card: $name, Type: ${cardType.render()}, Ability: ${primaryAbility.map(_.render()).getOrElse("None")}"
}

class ExplorerCard extends Card(
  name = "Explorer",
  cardType = new CardType("Explorer"),
  primaryAbility = Some(new Ability(List("2 coins"))),
  scrapAbility = Some(new Ability(List("2 damage")))
) {
  override def render(): String = s"Explorer Card: $name, Type: ${cardType.render()}, Ability: ${primaryAbility.map(_.render()).getOrElse("None")}, Scrap Ability: ${scrapAbility.map(_.render()).getOrElse("None")}"
}

