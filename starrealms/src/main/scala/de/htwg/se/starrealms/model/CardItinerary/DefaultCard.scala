package de.htwg.se.starrealms.model

class Scout(
  name: String = "Scout",
  cardType: String = "Ship",
  primaryAbility: PrimaryAbility = PrimaryAbility(List("1 coin"))
) extends Ship(name, cardType, None, None, None, Some(primaryAbility), None) {
  override def render(): String = s"Scout Card: $name, Type: $cardType, Ability: ${primaryAbility.render()}"
}

class Viper(
  name: String = "Viper",
  cardType: String = "Ship",
  primaryAbility: PrimaryAbility = PrimaryAbility(List("1 damage"))
) extends Ship(name, cardType, None, None, None, Some(primaryAbility), None) {
  override def render(): String = s"Viper Card: $name, Type: $cardType, Ability: ${primaryAbility.render()}"
}

class Explorer(
  name: String = "Explorer",
  cardType: String = "Ship",
  primaryAbility: PrimaryAbility = PrimaryAbility(List("2 coins")),
  scrapAbility: ScrapAbility = ScrapAbility(List("Draw a card"))
) extends Ship(name, cardType, None, None, None, Some(primaryAbility), Some(scrapAbility)) {
  override def render(): String = s"Explorer Card: $name, Type: $cardType, Ability: ${primaryAbility.render()}, Scrap Ability: ${scrapAbility.render()}"
}

