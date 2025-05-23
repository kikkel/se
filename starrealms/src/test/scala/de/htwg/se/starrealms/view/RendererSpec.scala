package de.htwg.se.starrealms.view

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec
import de.htwg.se.starrealms.model._
import scala.util.Success

class RendererSpec extends AnyWordSpec with Matchers {
  object DummySet extends de.htwg.se.starrealms.model.Set {
    override def nameOfSet: String = "Core Set"
  }
  val dummySet: de.htwg.se.starrealms.model.Set = DummySet
  val dummyFaction: Faction = Faction("Unaligned")
  val abilities = new Ability(List(SimpleAction("TestAbility")))


  "A CardRenderer" should {
    val renderer = new CardRenderer

    "render a DefaultCard correctly" in {
      val card = new DefaultCard(
        set = dummySet,
        cardName = "TestCard",
        primaryAbility = Some(abilities),
        faction = dummyFaction,
        cardType = Success(new Ship()),
        qty = 1,
        role = "Trade Deck"
      )
      val rendered = renderer.render(card)
      rendered should include("TestCard")
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
        cardType = Success(new Ship()),
        qty = 1,
        role = "Trade Deck"
      )
      val rendered = renderer.render(card)
      rendered should include("Explorer")
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
        cardType = Success(new Ship()),
        qty = 1,
        role = "Trade Deck"
      )
      val rendered = renderer.render(card)
      rendered should include("TestCard")
      rendered should include("5")
      rendered should include("Unaligned")
      rendered should include("Ship")
    }
  }
}