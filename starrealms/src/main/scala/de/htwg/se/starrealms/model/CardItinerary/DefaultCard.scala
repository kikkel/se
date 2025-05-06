package de.htwg.se.starrealms.model

<<<<<<< HEAD
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
=======
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
>>>>>>> 9b6a5df8a6375410fa96957422890f0be45640ed
}

