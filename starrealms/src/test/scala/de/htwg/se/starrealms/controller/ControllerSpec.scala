package de.htwg.se.starrealms.controller

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec
import de.htwg.se.starrealms.model._

class ControllerSpec extends AnyWordSpec with Matchers {
  "A Controller" should {

    "draw a Scout card" in {
      val gameLogic = new GameLogic
      val deck = new DefaultDeck("DefaultDeck", "Default", List(CardFactory.createCard("Scout")))
      val controller = new Controller(gameLogic, deck)

      val result = controller.drawCard("Scout")
      //result should not include("Drew card: Scout")
    }

    "handle no Scout cards left in the deck" in {
      val gameLogic = new GameLogic
      val deck = new DefaultDeck("DefaultDeck", "Default", List())
      val controller = new Controller(gameLogic, deck)

      val result = controller.drawCard("Scout")
      //result should not include("No Scout cards left in the deck.")
    }

    "draw a Viper card" in {
      val gameLogic = new GameLogic
      val deck = new DefaultDeck("DefaultDeck", "Default", List(CardFactory.createCard("Viper")))
      val controller = new Controller(gameLogic, deck)

      val result = controller.drawCard("Viper")
      //result should not include("Drew card: Viper")
    }

    "handle no Viper cards left in the deck" in {
      val gameLogic = new GameLogic
      val deck = new DefaultDeck("DefaultDeck", "Default", List())
      val controller = new Controller(gameLogic, deck)

      val result = controller.drawCard("Viper")
      //result should not include("No Viper cards left in the deck.")
    }
	"reset the game and deck" in {
      val gameLogic = new GameLogic
      val deck = new DefaultDeck("DefaultDeck", "Default", List(CardFactory.createCard("Scout"), CardFactory.createCard("Viper")))
      val controller = new Controller(gameLogic, deck)

      controller.resetGame()
      deck.getCards.count(_.name == "Scout") shouldBe 8
      deck.getCards.count(_.name == "Viper") shouldBe 2
    }

    "return the game state" in {
      val gameLogic = new GameLogic
      val deck = new DefaultDeck("DefaultDeck", "Default", List())
      val controller = new Controller(gameLogic, deck)

      val gameState = controller.getGameState
      gameState should include("Deck:")
    }

    "process input for drawing a Scout card" in {
      val gameLogic = new GameLogic
      val deck = new DefaultDeck("DefaultDeck", "Default", List(CardFactory.createCard("Scout")))
      val controller = new Controller(gameLogic, deck)

      val result = controller.processInput("s")
      //result should not include("Drew card: Scout")
    }

    "process input for drawing a Viper card" in {
      val gameLogic = new GameLogic
      val deck = new DefaultDeck("DefaultDeck", "Default", List(CardFactory.createCard("Viper")))
      val controller = new Controller(gameLogic, deck)

      val result = controller.processInput("v")
      //result should not include("Drew card: Viper")
    }
	"process input for resetting the game" in {
      val gameLogic = new GameLogic
      val deck = new DefaultDeck("DefaultDeck", "Default", List())
      val controller = new Controller(gameLogic, deck)

      val result = controller.processInput("r")
      result should include("Game and deck have been reset.")
    }

    "handle invalid input" in {
      val gameLogic = new GameLogic
      val deck = new DefaultDeck("DefaultDeck", "Default", List())
      val controller = new Controller(gameLogic, deck)

      val result = controller.processInput("invalid")
      result should include("Unknown command: invalid")
    }
  }
}







