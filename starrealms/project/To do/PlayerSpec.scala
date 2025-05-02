/* package de.htwg.se.starrealms.model

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class PlayerSpec extends AnyWordSpec with Matchers {

  "A Player" should {
    "have a name" in {
      val player = new Player()
      player.getName should be("name")
    }
    "have a starting authority" in {
      val player = new Player()
      player.getAuthority should be(50)
    }

    "start with a default deck" in {
      val player = new Player()
      player.getDefaultDeck should be(10)
    }
  }
} */