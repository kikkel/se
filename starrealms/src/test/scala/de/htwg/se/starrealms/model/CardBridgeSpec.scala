package de.htwg.se.starrealms.model

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class CardBridgeSpec extends AnyWordSpec with Matchers {
  val dummyFaction = Faction("Unaligned")
  val dummySet = Set("Core Set")
  val abilities = new Ability(List("TestAbility"))
  "A Ship" should {
    "return correct cardType" in {
      val ship = new Ship
      ship.cardType shouldBe "Ship"
    }
  }

  "A Base" should {
    "return correct cardType and store defense and outpost flag" in {
      val base = new Base("5", isOutpost = true)
      base.cardType shouldBe "Base"
      base.defense shouldBe "5"
      base.isOutpost shouldBe true
    }
  }


  "A FactionCard" should {
    "render all fields correctly" in {
      
      val card = new FactionCard(
        set = dummySet,
        cardName = "TestCard",
        cost = 5,
        primaryAbility = Some(abilities),
        allyAbility = Some(abilities),
        scrapAbility = Some(abilities),
        faction = dummyFaction,
        cardType = new Ship(),
        qty = 1,
        role = "Trade Deck"
      )
      val rendered = card.render()
      rendered should include("Core Set")
      rendered should include("TestCard")
      rendered should include("5")
      rendered should include("TestAbility")
      rendered should include("Unaligned")
      rendered should include("Ship")
    }
  }

  "A DefaultCard" should {
    "render all fields correctly" in {
      val card = new DefaultCard(
        set = dummySet,
        cardName = "TestCard",
        primaryAbility = Some(new Ability(List("TestAbility"))),
        faction = dummyFaction,
        cardType = new Ship(),
        qty = 1,
        role = "Personal Deck"
      )
      val rendered = card.render()
      rendered should include("TestCard")
      rendered should include("Unaligned")
      rendered should include("Ship")
    }
  }
  "An ExplorerCard" should {
    "render all fields correctly" in {
      val card = new ExplorerCard(
        set = dummySet,
        cardName = "Explorer",
        cost = 2,
        primaryAbility = Some(new Ability(List("TestAbility"))),
        scrapAbility = Some(new Ability(List("TestAbility"))),
        faction = dummyFaction,
        cardType = new Ship(),
        qty = 1,
        role = "Explorer Deck"
      )
      val rendered = card.render()
      rendered should include("Explorer")
      rendered should include("Unaligned")
      rendered should include("Ship")
    }
  }
}