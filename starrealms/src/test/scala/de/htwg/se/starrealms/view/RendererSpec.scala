package de.htwg.se.starrealms.view

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec
import de.htwg.se.starrealms.model._

class RendererSpec extends AnyWordSpec with Matchers {
  val dummySet: Set = Set("Core Set")
  val dummyFaction: Faction = Faction("Unaligned")
  val abilities = new Ability(List("TestAbility"))

  "A CardRenderer" should {
    val renderer = new CardRenderer
    

    "render a DefaultCard correctly" in {
      val card = new DefaultCard(
        set = dummySet,
        cardName = "TestCard",
        primaryAbility = Some(abilities),
        faction = dummyFaction,
        cardType = new Ship(),
        qty = 1,
        role = "Trade Deck"
      )
      val rendered = renderer.render(card)
      rendered should include("TestCard")
      rendered should include("TestAbility")
      rendered should include("Ship")
    }

    "render an ExplorerCard correctly" in {
      val card = new ExplorerCard(
        set = dummySet,
        cardName = "Explorer",
        cost = 2,
        primaryAbility = Some(abilities),
        scrapAbility = Some(abilities),
        faction = dummyFaction,
        cardType = new Ship(),
        qty = 1,
        role = "Trade Deck"
      )
      val rendered = renderer.render(card)
      rendered should include("Explorer")
      rendered should include("TestAbility")
      rendered should include("2")
      rendered should include("Ship")
    }

    "render a FactionCard correctly" in {
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
      val rendered = renderer.render(card)
      rendered should include("TestCard")
      rendered should include("5")
      rendered should include("TestAbility")
      rendered should include("Unaligned")
      rendered should include("Ship")
    }
  }
}