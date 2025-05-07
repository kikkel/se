package de.htwg.se.starrealms.controller

import de.htwg.se.starrealms.model._
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class CommandSpec extends AnyWordSpec with Matchers {

    "A DrawCardCommand" should {

    "execute and draw a card of the specified type" in {
      val gameLogic = new GameLogic
      val deck = new DefaultDeck("DefaultDeck", "Default", List(CardFactory.createCard("Scout")))
      val controller = new Controller(gameLogic, deck)
      val command = new DrawCardCommand(controller, "Scout")

      val result = command.execute()
      result should include("Drew card")
    }

    "return a message if no cards of the specified type are left" in {
      val gameLogic = new GameLogic
      val deck = new DefaultDeck("DefaultDeck", "Default", List())
      val controller = new Controller(gameLogic, deck)
      val command = new DrawCardCommand(controller, "Scout")

      val result = command.execute()
      result should not include("No Scout cards left in the deck.")
    }
  }
  "A ResetGameCommand" should {

    "execute and reset the game and deck" in {
      val gameLogic = new GameLogic
      val deck = new DefaultDeck("DefaultDeck", "Default", List(CardFactory.createCard("Scout")))
      val controller = new Controller(gameLogic, deck)
      val command = new ResetGameCommand(controller)

      val result = command.execute()
      result should include("Game and deck have been reset.")
      deck.getCards.count(_.name == "Scout") shouldBe 8
      deck.getCards.count(_.name == "Viper") shouldBe 2
    }
  }
  "A ShowDeckCommand" should {

    "execute and return the current deck state" in {
      val gameLogic = new GameLogic
      val deck = new DefaultDeck("DefaultDeck", "Default", List(CardFactory.createCard("Scout")))
      val controller = new Controller(gameLogic, deck)
      val command = new ShowDeckCommand(controller)

      val result = command.execute()
      result should not include("Deck Name: DefaultDeck")
      result should include("Scout")
    }
  }
  "An InvalidCommand" should {

    "execute and return an error message for unknown input" in {
      val command = new InvalidCommand("unknown")

      val result = command.execute()
      result should include("Unknown command: unknown")
    }
  }


}

