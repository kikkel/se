package de.htwg.se.starrealms.model

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class MainSpec extends AnyWordSpec with Matchers {

  "The Main" should {
    "initialize the game" in {
      val playingField = new PlayingField()
      playingField should not be null
    }

    "handle viewing the current state" in {
      val playingField = new PlayingField()
      playingField.getDeck should not be empty
      playingField.getField shouldBe empty
    }

    "handle turning over a card" in {
      val playingField = new PlayingField()
      val initialDeckSize = playingField.getDeck.size
      val initialFieldSize = playingField.getField.size

      playingField.turnOverCard()

      playingField.getDeck.size should be(initialDeckSize - 1)
      playingField.getField.size should be(initialFieldSize + 1)
    }

    "handle an empty deck gracefully" in {
      val playingField = new PlayingField()
      while (playingField.getDeck.nonEmpty) {
        playingField.turnOverCard()
      }

      playingField.getDeck shouldBe empty
      playingField.turnOverCard() // Should handle empty deck
    }

   /*  "draw the field" in {
      val playingField = new PlayingField()
      playingField.drawField()
      playingField.getDeck should not be empty
      playingField.getField shouldBe empty
    }

    "turn over a card" in {
      val playingField = new PlayingField()
      val initialDeckSize = playingField.getDeck.size
      val initialFieldSize = playingField.getField.size

      playingField.turnOverCard()

      playingField.getDeck.size should be(initialDeckSize - 1)
      playingField.getField.size should be(initialFieldSize + 1)
    } */
/*     "have a playerActionSpace" in {
      val playingField = new PlayingField()
      val actionSpace = playingField.actionSpace
      actionSpace should not be null
    } */





  }
}