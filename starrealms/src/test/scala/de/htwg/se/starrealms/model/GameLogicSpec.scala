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

    "handle edge cases for turnOverCard" in {
      val gameLogic = new GameLogic(new PlayingField())
      gameLogic.turnOverCard("") should include("Invalid input")
      gameLogic.turnOverCard(" ") should include("Invalid input")
    }

    "handle multiple resets" in {
      val gameLogic = new GameLogic(new PlayingField())
      gameLogic.turnOverCard("s")
      gameLogic.resetGame()
      gameLogic.turnOverCard("v")
      gameLogic.resetGame()
      gameLogic.drawField() should include("Deck: Scout, Scout, Scout, Scout, Scout, Scout, Scout, Scout, Viper, Viper")
      gameLogic.drawField() should include("Field: Empty")
    }

    "handle drawing the field after multiple actions" in {
      val gameLogic = new GameLogic(new PlayingField())
      gameLogic.turnOverCard("s")
      gameLogic.turnOverCard("v")
      gameLogic.drawField() should include("Deck: Scout, Scout, Scout, Scout, Scout, Scout, Scout, Viper")
      gameLogic.drawField() should include("Field: Scout, Viper")
    }

    "handle empty deck and field correctly" in {
      val gameLogic = new GameLogic(new PlayingField())
      (1 to 8).foreach(_ => gameLogic.turnOverCard("s"))
      (1 to 2).foreach(_ => gameLogic.turnOverCard("v"))
      gameLogic.drawField() should include("Deck: Empty")
      gameLogic.drawField() should include("Field: Scout, Scout, Scout, Scout, Scout, Scout, Scout, Scout, Viper, Viper")
    }
   /*  "exit the game correctly" in {
      val gameLogic = new GameLogic(new PlayingField())
      gameLogic.exitGame() should be(true)
    } */
  

  }
}