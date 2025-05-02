package de.htwg.se.starrealms.model

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec
//import de.htwg.se.starrealms.model._

class GameLogicSpec extends AnyWordSpec with Matchers {

  "GameLogic" should {
    "initialize with an options menu and a deck" in {
      val gameLogic = new GameLogic
      gameLogic.optionsMenu() should include("Deck:")
    }

    "turn over a Scout card" in {
      val gameLogic = new GameLogic
      val result = gameLogic.turnOverCard("s")
      result should include("Turned over Scout")
    }

    "turn over a Viper card" in {
      val gameLogic = new GameLogic
      val result = gameLogic.turnOverCard("v")
      result should include("Turned over Viper")
    }

    "handle invalid input" in {
      val gameLogic = new GameLogic
      val result = gameLogic.turnOverCard("invalid")
      result should include("Invalid input")
    }

    "reset the game" in {
      val gameLogic = new GameLogic
      gameLogic.turnOverCard("s")
      gameLogic.resetGame()
      gameLogic.optionsMenu should include("Deck:")
    }
  }
}