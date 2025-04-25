package de.htwg.se.starrealms.model

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class GameLogicSpec extends AnyWordSpec with Matchers {
  "GameLogic" should {
    "initialize with default deck and empty field" in {
      val gameLogic = new GameLogic(new PlayingField())
      gameLogic.drawField() should include("Deck: Scout, Scout, Scout, Scout, Scout, Scout, Scout, Scout, Viper, Viper")
      gameLogic.drawField() should include("Field: Empty")
    }

    "turn over Scout and Viper cards" in {
      val gameLogic = new GameLogic(new PlayingField())
      gameLogic.turnOverCard("s") should include("Turned over Scout")
      gameLogic.turnOverCard("v") should include("Turned over Viper")
    }

    "handle no Scout or Viper left" in {
      val gameLogic = new GameLogic(new PlayingField())
      (1 to 8).foreach(_ => gameLogic.turnOverCard("s"))
      gameLogic.turnOverCard("s") should include("No Scout cards left")
      (1 to 2).foreach(_ => gameLogic.turnOverCard("v"))
      gameLogic.turnOverCard("v") should include("No Viper cards left")
    }

    "handle invalid input" in {
      val gameLogic = new GameLogic(new PlayingField())
      gameLogic.turnOverCard("x") should include("Invalid input")
    }

    "reset the game correctly" in {
      val gameLogic = new GameLogic(new PlayingField())
      gameLogic.turnOverCard("s")
      gameLogic.resetGame()
      gameLogic.drawField() should include("Deck: Scout, Scout, Scout, Scout, Scout, Scout, Scout, Scout, Viper, Viper")
      gameLogic.drawField() should include("Field: Empty")
    }
    "exit the game correctly" in {
      val gameLogic = new GameLogic(new PlayingField())
      gameLogic.exitGame() should be(true)
    }
  

  }
}