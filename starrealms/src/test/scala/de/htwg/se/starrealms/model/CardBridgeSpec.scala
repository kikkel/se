package de.htwg.se.starrealms.model

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec
import scala.util.{Success, Failure, Try}

class CardBridgeSpec extends AnyWordSpec with Matchers {
  val dummyFaction = Faction("Unaligned")
  val dummyEdition = Edition("Core Set")
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
    "render all fields correctly (Success)" in {
      val card = new FactionCard(
        edition = dummyEdition,
        cardName = "TestCard",
        cost = 5,
        primaryAbility = Some(abilities),
        allyAbility = Some(abilities),
        scrapAbility = Some(abilities),
        faction = dummyFaction,
        cardType = Success(new Ship()),
        qty = 1,
        role = "Trade Deck",
        notes = Some("Testnote")
      )
      val rendered = card.render()
      rendered should include("Core Set")
      rendered should include("TestCard")
      rendered should include("5")
      rendered should include("Unaligned")
      rendered should include("Ship")
      rendered should include("#BRIDGE: FactionCard")
      rendered should include("Testnote")
    }
    "render error if cardType is Failure" in {
      val card = new FactionCard(
        edition = dummyEdition,
        cardName = "TestCard",
        cost = 5,
        primaryAbility = None,
        allyAbility = None,
        scrapAbility = None,
        faction = dummyFaction,
        cardType = Failure(new Exception("failtype")),
        qty = 1,
        role = "Trade Deck",
        notes = None
      )
      val rendered = card.render()
      rendered should include("Error: failtype")
    }
  }

  "A DefaultCard" should {
    "render all fields correctly (Success)" in {
      val card = DefaultCard(
        edition = dummyEdition,
        cardName = "TestCard",
        primaryAbility = Some(abilities),
        faction = dummyFaction,
        cardType = Success(new Ship()),
        qty = 1,
        role = "Personal Deck"
      )
      val rendered = card.render()
      rendered should include("TestCard")
      rendered should include("Unaligned")
      rendered should include("Ship")
      rendered should include("#BRIDGE: DefaultCard")
    }
    "render error if cardType is Failure" in {
      val card = DefaultCard(
        edition = dummyEdition,
        cardName = "TestCard",
        primaryAbility = None,
        faction = dummyFaction,
        cardType = Failure(new Exception("failtype")),
        qty = 1,
        role = "Personal Deck"
      )
      val rendered = card.render()
      rendered should include("Error: failtype")
    }
  }

  "An ExplorerCard" should {
    "render all fields correctly (Success)" in {
      val card = new ExplorerCard(
        edition = dummyEdition,
        cardName = "Explorer",
        cost = 2,
        primaryAbility = Some(abilities),
        scrapAbility = Some(abilities),
        faction = dummyFaction,
        cardType = Success(new Ship()),
        qty = 1,
        role = "Explorer Deck"
      )
      val rendered = card.render()
      rendered should include("Explorer")
      rendered should include("Unaligned")
      rendered should include("Ship")
      rendered should include("#BRIDGE: ExplorerCard")
    }
    "render error if cardType is Failure" in {
      val card = new ExplorerCard(
        edition = dummyEdition,
        cardName = "Explorer",
        cost = 2,
        primaryAbility = None,
        scrapAbility = None,
        faction = dummyFaction,
        cardType = Failure(new Exception("failtype")),
        qty = 1,
        role = "Explorer Deck"
      )
      val rendered = card.render()
      rendered should include("Error: failtype")
    }
  }
}