/* package de.htwg.se.starrealms.view

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec
import de.htwg.se.starrealms.model._

class RendererSpec extends AnyWordSpec with Matchers {
  "A CardRenderer" should {

    "render a card with abilities" in {
      val primaryAbility = Some(PrimaryAbility(List("Gain 3 Trade")))
      val card = new Ship("Scout", primaryAbility = primaryAbility)
      val renderer = new CardRenderer

      val result = renderer.render(card)
      result should include("Card Name: Scout")
      result should include("Card Type: Ship")
      result should include("Abilities: Gain 3 Trade")
    }

    "render a card without abilities" in {
      val card = new Ship("Scout")
      val renderer = new CardRenderer

      val result = renderer.render(card)
      result should include("Card Name: Scout")
      result should include("Card Type: Ship")
      result should include("Abilities: None")
    }
  }
  "A DeckRenderer" should {

    "render a deck with cards" in {
      val card1 = new Ship("Scout", primaryAbility = Some(PrimaryAbility(List("Gain 3 Trade"))))
      val card2 = new Ship("Viper", primaryAbility = Some(PrimaryAbility(List("Deal 3 Damage"))))
      val deck = new DefaultDeck("TestDeck", "Default", List(card1, card2))
      val renderer = new DeckRenderer

      val result = renderer.render(deck)
      result should include("Deck Name: TestDeck")
      result should include("Card Name: Scout")
      result should include("Card Name: Viper")
      result should not include("Abilities: Gain 3 Trade")
      result should not include("Abilities: Deal 3 Damage")
    }

    "render an empty deck" in {
      val deck = new DefaultDeck("EmptyDeck", "Default", List())
      val renderer = new DeckRenderer

      val result = renderer.render(deck)
      result should include("Deck Name: EmptyDeck")
      result should include("Cards:")
      result should include( "Card Name:")
    }
  }
  "A GameStateRenderer" should {

    "render the game state" in {
      val gameLogic = new GameLogic
      val renderer = new GameStateRenderer

      val result = renderer.render(gameLogic)
      result should include("#gameLogic") // Assuming optionsMenu includes this tag
    }
  }
} */