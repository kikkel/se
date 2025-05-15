package de.htwg.se.starrealms.model

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class CardBridgeSpec extends AnyWordSpec with Matchers {
  val dummyFaction = Faction("Unaligned")
  val  dummySet = Set("Test Set")
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
        primaryAbility = Some(new Ability(List("TestAbility"))),
        allyAbility = Some(new Ability(List("TestAbility"))),
        scrapAbility = Some(new Ability(List("TestAbility"))),
        faction = dummyFaction,
        cardType = new Ship()
      )
      val rendered = card.render()
      rendered should include("TestCard")
      rendered should include("5")
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
        cardType = new Ship()
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
        cardType = new Ship()
      )
      val rendered = card.render()
      rendered should include("Explorer")
      rendered should include("Unaligned")
      rendered should include("Ship")
    }
  }
}