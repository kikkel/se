package de.htwg.se.starrealms.controller

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec
import de.htwg.se.starrealms.model._

class ControllerSpec extends AnyWordSpec with Matchers {
  "A Controller" should {
	val gameLogic = new GameLogic
	val deck = new DefaultDeck("DefaultDeck", "Default", 	List())
	val controller = new Controller(gameLogic, deck)

	"get the current deck state" in {
	  val result = controller.getDeckState
	  result should include("Scout")
	  result should include("Viper")
	}

	"process input to draw a Scout card" in {
	  val result = controller.processInput("s")
	  result should include("Drew card:")
	}

	"process input to draw a Viper card" in {
	  val result = controller.processInput("v")
	  result should include("Drew card:")
	}

	"reset the game and deck" in {
	  val result = controller.processInput("r")
	  result should include("Game and deck have been reset")
	}
	 "handle deck state" in {
	  val result = controller.getDeckState
	  //result should include("Deck:")
	  result should include("Scout")
	  result should include("Viper")
	}

	"handle unknown commands" in {
	  val result = controller.processInput("unknown")
	  result should include("Unknown command")
	}
  }


}




