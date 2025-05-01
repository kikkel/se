package de.htwg.se.starrealms.model

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec
import de.htwg.se.starrealms.util.Observer

class GameLogicSpec extends AnyWordSpec with Matchers {
  "A GameLogic" should {
    val playingField = new PlayingField()
    val gameLogic = new GameLogic(playingField)

    "initialize with an empty field and a full deck" in {
      gameLogic.drawField() should include("Deck")
      gameLogic.drawField() should include("Field: Empty")
    }

    "allow turning over a Scout card" in {
      val result = gameLogic.turnOverCard("s")
      result should include("Scout")
      gameLogic.drawField() should include("Scout")
    }

    "allow turning over a Viper card" in {
      val result = gameLogic.turnOverCard("v")
      result should include("Viper")
      gameLogic.drawField() should include("Viper")
    }

    "handle invalid card types gracefully" in {
      val result = gameLogic.turnOverCard("invalid")
      result should include("Invalid input")
    }

    "reset the game correctly" in {
      gameLogic.resetGame()
      gameLogic.drawField() should include("Field: Empty")
      gameLogic.drawField() should include("Deck")
    }

    "notify observers when the game state changes" in {
      var notified = false
      val observer = new Observer {
        override def update: Unit = notified = true
      }
      gameLogic.addObserver(observer)
      gameLogic.turnOverCard("s")
      notified should be(true)
    }
  }
}