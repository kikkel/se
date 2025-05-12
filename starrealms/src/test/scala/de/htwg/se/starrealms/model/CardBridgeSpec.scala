package de.htwg.se.starrealms.model

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class CardBridgeSpec extends AnyWordSpec with Matchers {

  "A Ship CardType" should {
    "return its type" in {
      val ship = new Ship
      ship.cardType shouldBe "Ship"
    }
  }

  "A Base CardType" should {
    "return its type" in {
      val base = new Base(isOutPost = true)
      base.cardType shouldBe "Base"
    }
  }

  "A FactionCard" should {
    "render correctly with all attributes" in {
      val faction = new Faction { override def render(): String = "FactionName" }
      val primaryAbility = new Ability(List("Attack", "Heal"))
      val allyAbility = new Ability(List("Boost"))
      val scrapAbility = new Ability(List("Draw"))

      val factionCard = new FactionCard(
        cardName = "FactionBase",
        cost = Some(5),
        primaryAbility = Some(primaryAbility),
        allyAbility = Some(allyAbility),
        scrapAbility = Some(scrapAbility),
        faction = faction,
        cardType = new Base("5", isOutpost = true)
      )

      factionCard.render() should include("FactionBase")
      factionCard.render() should include("Cost: Some(5)")
      factionCard.render() should include("Primary Ability: Attack, Heal")
      factionCard.render() should include("Ally Ability: Boost")
      factionCard.render() should include("Scrap Ability: Draw")
      factionCard.render() should include("FactionName")
      factionCard.render() should include("Base")
    }

    "render correctly with missing optional attributes" in {
      val faction = new Faction { override def render(): String = "FactionName" }
      val primaryAbility = None
      val allyAbility = None
      val scrapAbility = None

      val factionCard = new FactionCard(
        cardName = "FactionShip",
        cost = Some(5),
        primaryAbility = None,
        allyAbility = None,
        scrapAbility = None,
        faction = faction,
        cardType = new Ship()
      )

      factionCard.render() should include("FactionShip")
      factionCard.render() should include("Cost: Some(5)")
      factionCard.render() should include("Primary Ability: None")
      factionCard.render() should include("Ally Ability: None")
      factionCard.render() should include("Scrap Ability: None")
      factionCard.render() should include("FactionName")
      factionCard.render() should include("Ship")
    }
  }
}