package de.htwg.se.starrealms.model

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class MainSpec extends AnyWordSpec with Matchers {

  "The Main" should {
    "initialize the game" in {
      val playingField = new PlayingField()
      playingField should not be null
    }
    "draw the field" in {
      val playingField = new PlayingField()
      playingField.drawField()
    }
/*     "have a playerActionSpace" in {
      val playingField = new PlayingField()
      val actionSpace = playingField.actionSpace
      actionSpace should not be null
    } */





  }
}