package de.htwg.se.starrealms.model

import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers

class PlayingFieldSpec extends AnyWordSpec with Matchers {
  "A PlayingField" should {
    "have a height and width that is scalable" in {
        val playingField = new PlayingField
        playingField.rows shouldEqual 20
        playingField.columns shouldEqual 30

    }
    "contain 8 card slots in the top third" in {
        val playingField = new PlayingField
        playingField.numRecs shouldEqual 8
    }

    "calculate correct card width and height with 3:2 ratio" in {
        val playingField = new PlayingField
        playingField.rectHeight.toFloat / playingField.rectWidth.toFloat shouldBe  1.5f +- 0.01f
    }
    "center the cards in the top third vertically" in {
        val playingField = new PlayingField
        val expectedTopThird = playingField.rows / 3
        val spaceAboveCards = playingField.centeredRow
        val spaceBelowCards = playingField.rows - (playingField.centeredRow + playingField.rectHeight)
    }
  }
}


