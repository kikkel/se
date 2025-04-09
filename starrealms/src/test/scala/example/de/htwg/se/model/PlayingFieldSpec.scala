package de.htwg.se.starrealms.model

import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers

class PlayingFieldSpec extends AnyWordSpec with Matchers {
  "A PlayingField" should {
    "have the correct dimensions" in {
      val playingField = new PlayingField
      playingField.rows should be(20)
      playingField.columns should be(30)
      playingField.numRecs should be(8)
      playingField.numGaps should be(7)
      playingField.totalGapWidth should be(6)
      playingField.cardRowWidth should be(24)
      playingField.sideGapwidth should be(3)
      playingField.gapWidth should be(2)
      playingField.rectWidth should be(4)
      playingField.rectHeight should be(4)
      playingField.gapHeight should be(4)
    }
  }
}


