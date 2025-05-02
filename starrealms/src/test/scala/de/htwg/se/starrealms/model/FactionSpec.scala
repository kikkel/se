package de.htwg.se.starrealms.model

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class FactionSpec extends AnyWordSpec with Matchers {
  "A Faction" should {
    "have a name" in {
      val faction = new Faction("Trade Federation")
      faction.getName should be("Trade Federation")
    }

    "have a toString method" in {
      val faction = new Faction("Trade Federation")
      faction.toString should be("Faction(name=Trade Federation)")
    }

    "not be equal to another faction with a different name" in {
      val faction1 = new Faction("Trade Federation")
      val faction2 = new Faction("Star Empire")
      faction1 should not be faction2
    }

    "not be equal to null" in {
      val faction = new Faction("Trade Federation")
      faction should not be null
    }
  }
}