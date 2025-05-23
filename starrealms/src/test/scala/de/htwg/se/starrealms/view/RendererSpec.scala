package de.htwg.se.starrealms.view

import de.htwg.se.starrealms.model._
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec
import scala.util.Success

class RendererSpec extends AnyWordSpec with Matchers {

  "An OptionsMenuRender" should {
    "render an options menu with options" in {
      val menu = new OptionsMenu
      menu.addOption(new OptionItem("Start", () => ()))
      menu.addOption(new OptionItem("Exit", () => ()))
      val renderer = new OptionsMenuRender
      val output = renderer.render(menu)
      output should include ("Options Menu")
      output should include ("Start")
      output should include ("Exit")
    }
  }

  "A MainMenuRenderer" should {
    "render a main menu with options" in {
      val menu = new MainMenu
      menu.addOption(new OptionItem("Play", () => ()))
      menu.addOption(new OptionItem("Quit", () => ()))
      val renderer = new MainMenuRenderer
      val output = renderer.render(menu)
      output should include ("Main Menu")
      output should include ("Play")
      output should include ("Quit")
    }
  }

  "A CardRenderer" should {
    val renderer = new CardRenderer

    "render a DefaultCard correctly" in {
      val card = new DefaultCard(
        set = Set("Core Set"),
        cardName = "Scout",
        primaryAbility = Some(new Ability(List(SimpleAction("Gain 1 Trade")))),
        faction = Faction("Unaligned"),
        cardType = Success(new Ship()),
        qty = 1,
        role = "Personal Deck"
      )
      val output = renderer.render(card)
      output should include ("Name: Scout")
      output should include ("Ability")
      output should include ("Gain 1 Trade")
    }

    "render an ExplorerCard correctly" in {
      val card = new ExplorerCard(
        set = Set("Core Set"),
        cardName = "Explorer",
        cost = 2,
        primaryAbility = Some(new Ability(List(SimpleAction("Gain 2 Trade")))),
        scrapAbility = Some(new Ability(List(SimpleAction("Scrap for 2 Combat")))),
        faction = Faction("Unaligned"),
        cardType = Success(new Ship()),
        qty = 1,
        role = "Trade Deck"
      )
      val output = renderer.render(card)
      output should include ("Name: Explorer")
      output should include ("Cost: 2")
      output should include ("Ability")
      output should include ("Scrap Ability")
      output should include ("Gain 2 Trade")
      output should include ("Scrap for 2 Combat")
    }

    "render a FactionCard correctly" in {
      val card = new FactionCard(
        set = Set("Core Set"),
        cardName = "Blob World",
        cost = 8,
        primaryAbility = Some(new Ability(List(SimpleAction("Draw a card")))),
        allyAbility = Some(new Ability(List(SimpleAction("Ally: Draw another card")))),
        scrapAbility = Some(new Ability(List(SimpleAction("Scrap: Gain 5 Combat")))),
        faction = Faction("Blob"),
        cardType = Success(new Base("5", false)),
        qty = 1,
        role = "Trade Deck"
      )
      val output = renderer.render(card)
      output should include ("Name: Blob World")
      output should include ("Type")
      output should include ("Cost: 8")
      output should include ("Primary Ability")
      output should include ("Ally Ability")
      output should include ("Scrap Ability")
      output should include ("Faction: Blob")
      output should include ("Draw a card")
      output should include ("Ally: Draw another card")
      output should include ("Scrap: Gain 5 Combat")
    }

    "render abilities as None if not present" in {
      val card = new DefaultCard(
        set = Set("Core Set"),
        cardName = "Scout",
        primaryAbility = None,
        faction = Faction("Unaligned"),
        cardType = Success(new Ship()),
        qty = 1,
        role = "Personal Deck"
      )
      val output = renderer.render(card)
      output should include ("Ability: None")
    }
  }
}