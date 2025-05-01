package de.htwg.se.starrealms.controller

import de.htwg.se.starrealms.model.{GameLogic, PlayingField}
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class ControllerSpec extends AnyWordSpec with Matchers {
  "A Controller" should {
	"process input 's' and turn over a Scout card" in {
	  val gameLogic = new GameLogic(new PlayingField())
	  val controller = new Controller(gameLogic)

	  val result = controller.processInput("s")
	  result should include("Scout") // Verify that a Scout card was turned over
	}

	"process input 'v' and turn over a Viper card" in {
	  val gameLogic = new GameLogic(new PlayingField())
	  val controller = new Controller(gameLogic)

	  val result = controller.processInput("v")
	  result should include("Viper") // Verify that a Viper card was turned over
	}

	"process input 'reset' and reset the game" in {
	  val gameLogic = new GameLogic(new PlayingField())
	  val controller = new Controller(gameLogic)

	  val result = controller.processInput("reset")
	  result should be("Game has been reset.") // Verify that the game was reset
	}

	"process unknown input and return an error message" in {
	  val gameLogic = new GameLogic(new PlayingField())
	  val controller = new Controller(gameLogic)

	  val result = controller.processInput("unknown")
	  result should include("Unknown command") // Verify that an error message is returned
	}

	"return the current game state" in {
	  val gameLogic = new GameLogic(new PlayingField())
	  val controller = new Controller(gameLogic)

	  val gameState = controller.getGameState
	  gameState should include("Deck") // Verify that the game state includes the deck
	  gameState should include("Field") // Verify that the game state includes the field
	}
  }
}

