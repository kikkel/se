package de.htwg.se.starrealms.model

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class GameLogicSpec extends AnyWordSpec with Matchers {
  "GameLogic" should {
	"initialize with a default deck and an empty field" in {
	  val gameLogic = new GameLogic(new PlayingField())
	  gameLogic.drawField() should include("Deck: Scout, Scout, Scout, Scout, Scout, Scout, Scout, Scout, Viper, Viper")
	  gameLogic.drawField() should include("Field: Empty")
	}

	"turn over a card and update the field" in {
	  val gameLogic = new GameLogic(new PlayingField())
	  gameLogic.turnOverCard() should include("Turned over card: Scout")
	  gameLogic.drawField() should include("Field: Scout")
	}

	"handle an empty deck gracefully" in {
	  val gameLogic = new GameLogic(new PlayingField())
	  while (gameLogic.turnOverCard().contains("Turned over card")) {}
	  gameLogic.turnOverCard() should include("The deck is empty!")
	}

	"reset the game" in {
	  val gameLogic = new GameLogic(new PlayingField())
	  gameLogic.turnOverCard()
	  gameLogic.resetGame()
	  gameLogic.drawField() should include("Deck: Scout, Scout, Scout, Scout, Scout, Scout, Scout, Scout, Viper, Viper")
	  gameLogic.drawField() should include("Field: Empty")
	}
  }
}

