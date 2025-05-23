package de.htwg.se.starrealms.model

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec
import scala.util.Success

class CardBridgeSpec extends AnyWordSpec with Matchers {
  val dummyFaction = Faction("Unaligned")
  object DummySet extends de.htwg.se.starrealms.model.Set {
    override def nameOfSet: String = "Core Set"
  }
  val dummySet: de.htwg.se.starrealms.model.Set = DummySet
  val abilities = new Ability(List(SimpleAction("TestAbility")))

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
        cardType = Success(new Ship()),
        qty = 1,
        role = "Trade Deck"
      )
      val rendered = card.render()
      rendered should include("Core Set")
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
        primaryAbility = Some(new Ability(List(SimpleAction("TestAbility")))),
        faction = dummyFaction,
        cardType = Success(new Ship()),
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
        primaryAbility = Some(new Ability(List(SimpleAction("TestAbility")))),
        scrapAbility = Some(new Ability(List(SimpleAction("TestAbility")))),
        faction = dummyFaction,
        cardType = Success(new Ship()),
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