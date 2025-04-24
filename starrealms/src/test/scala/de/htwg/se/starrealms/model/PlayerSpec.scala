package de.htwg.se.starrealms.model

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class PlayerSpec extends AnyWordSpec with Matchers {

  "A Player" should {
    "have a name" in {
      val player = new Player("name")
      player.name should be("name")
    }
    "have a starting authority" in {
      val player = Player("name")
      player.authority should be(50)
    }

    "have a starting trade" in {
      val player = Player("name")
      player.trade should be(8)
    }

    "have a starting combat" in {
      val player = Player("name")
      player.combat should be(2)
    }

    "have a starting deck size" in {
      val player = new Player("name")
      player.playerDeck should be(10)
    }*/
}
}