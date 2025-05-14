package de.htwg.se.starrealms.model

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class CardBridgeSpec extends AnyWordSpec with Matchers {

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
        set = new Set { def nameOfSet = "TestSet" },
        cardName = "TestCard",
        cost = 5,
        primaryAbility = Some(new Ability(List("TestAbility"))),
        allyAbility = Some(new Ability(List("TestAbility"))),
        scrapAbility = Some(new Ability(List("TestAbility"))),
        faction = Faction("Unaligned"),
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
        set = new Set { def nameOfSet = "TestSet" },
        cardName = "TestCard",
        primaryAbility = Some(new Ability(List("TestAbility"))),
        faction = Faction("Unaligned"),
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
        set = new Set { def nameOfSet = "TestSet" },
        cardName = "Explorer",
        primaryAbility = Some(new Ability(List("TestAbility"))),
        scrapAbility = Some(new Ability(List("TestAbility"))),
        faction = Faction("Unaligned"),
        cardType = new Ship()
      )
      val rendered = card.render()
      rendered should include("Explorer")
      rendered should include("Unaligned")
      rendered should include("Ship")
    }
  }
}