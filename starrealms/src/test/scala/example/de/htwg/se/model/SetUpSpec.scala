package de.htwg.se.starrealms.model

import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers

class PlayingFieldSpec extends AnyWordSpec with Matchers {

  "A PlayingField" should {
   /*  "have a starting Unit to measure PlayingField" in {
      val playingField = new PlayingField()
      val playingFieldUnit = playingField.dimension
    } */

    "have a dimension that is scalable" in {
        val playingField = new PlayingField()
        playingField.borderWidth.toInt should equal (playingField.dimension * 3/2+3)

    }

    /* "calculate correct card width and height with 3:2 ratio" in {
        val playingField = new PlayingField()
        playingField.rectHeight.toFloat / playingField.rectWidth.toFloat shouldBe  1.5f +- 0.01f
    } */

/*     "contain 8 cards in the trade row" in {
        val playingField = new PlayingField()
        playingField.numRecs should equal (8)
    } */

  /*   "contain 7 gaps between the cards" in {
        val playingField = new PlayingField()
        playingField.numGaps should equal (playingField.numRecs - 1)
    } */

/*     "contain half a gap on either end of the row" in {
        val playingField = new PlayingField()
        playingField.sideGapwidth should equal (0)
    } */
/* 
    "calculate the total gap width" in {
        val playingField = new PlayingField()
        playingField.totalGapWidth.toFloat should equal (playingField.dimension - (playingField.numRecs * playingField.rectWidth))
    } */

    "calculate the card row width" in {
        val playingField = new PlayingField()
        playingField.cardRowWidth.toFloat should equal (playingField.numRecs * playingField.rectWidth + playingField.numGaps * playingField.gapWidth)
    }

    "calculate the gap width" in {
        val playingField = new PlayingField()
        playingField.gapWidth should equal (playingField.dimension / 25)
    }

    "calculate the card width" in {
        val playingField = new PlayingField()
        playingField.rectWidth should equal (playingField.gapWidth * 2)
    }

    "calculate the card height" in {
        val playingField = new PlayingField()
        playingField.rectHeight should equal (Math.min((playingField.gapWidth * 3), (playingField.rows / 5)))
    }

    "calculate the gap height" in {
        val playingField = new PlayingField()
        playingField.gapHeight should equal (playingField.rows / 5)
    }

    "calculate the top third of the playing field" in {
        val playingField = new PlayingField()
        playingField.topThird should equal (playingField.rows / 3)
    }

    "calculate the centered row" in {
        val playingField = new PlayingField()
        playingField.centeredRow should equal (Math.max((playingField.topThird - playingField.rectHeight) / 2, 0))
    }



   /* "center the cards in the top third vertically" in {
        val playingField = new PlayingField()
        val expectedTopThird = playingField.rows / 3
        val spaceAboveCards = playingField.centeredRow
        val spaceBelowCards = playingField.rows - (playingField.centeredRow + playingField.rectHeight)
    } */

    "draw borders around the playing field" in {
        val playingField = new PlayingField()
        val border = "+" + ("-" * (playingField.borderWidth)) + "+"
        println(border) // This will print the top and bottom border
    }

    "draw the playing field with cards" in {
        val playingField = new PlayingField()
        playingField.drawField() // This will print the playing field to the console
    }

    "draw the playing field with empty spaces" in {
        val playingField = new PlayingField()
        playingField.drawField() // This will print the playing field to the console
    }

   

  }
}


