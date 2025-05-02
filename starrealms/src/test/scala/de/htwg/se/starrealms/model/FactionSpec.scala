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
class TradeFederationSpec extends AnyWordSpec with Matchers {
  "A Trade Federation" should {
    "have a name" in {
      val faction = new TradeFederation()
      faction.getName should be("Trade Federation")
    }

    "have a toString method" in {
      val faction = new TradeFederation()
      faction.toString should be("TradeFederation(name=Trade Federation)")
    }
  }
}

class StarEmpireSpec extends AnyWordSpec with Matchers {
  "A Star Empire" should {
    "have a name" in {
      val faction = new StarEmpire()
      faction.getName should be("Star Empire")
    }

    "have a toString method" in {
      val faction = new StarEmpire()
      faction.toString should be("StarEmpire(name=Star Empire)")
    }
  }
}

class BlobSpec extends AnyWordSpec with Matchers {
  "A Blob" should {
    "have a name" in {
      val faction = new Blob()
      faction.getName should be("Blob")
    }

    "have a toString method" in {
      val faction = new Blob()
      faction.toString should be("Blob(name=Blob)")
    }
  }
}

class MachineCultSpec extends AnyWordSpec with Matchers {
  "A MachineCult" should {
    "have a name" in {
      val faction = new MachineCult()
      faction.getName should be("MachineCult")
    }

    "have a toString method" in {
      val faction = new MachineCult()
      faction.toString should be("MachineCult(name=MachineCult)")
    }
  }
}