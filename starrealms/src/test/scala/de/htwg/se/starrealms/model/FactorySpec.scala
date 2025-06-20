/* package de.htwg.se.starrealms.model

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class FactorySpec extends AnyWordSpec with Matchers {
  "Faction Abstract Factory" should {
    "return correct instances for known faction names" in {
      Faction("star empire").factionName shouldBe "Star Empire"
      Faction("trade federation").factionName shouldBe "Trade Federation"
      Faction("blob").factionName shouldBe "Blob"
      Faction("machine cult").factionName shouldBe "Machine Cult"
      Faction("unaligned").factionName shouldBe "Unaligned"
    }

    "throw an exception for unknown faction names" in {
      an [IllegalArgumentException] should be thrownBy { Faction("pirates") }
    }

    "handle composite factions" in {
      val composite = Faction("star empire / blob")
      composite.factionName shouldBe "Star Empire / Blob"
      composite.matches(Faction("star empire")) shouldBe true
      composite.matches(Faction("blob")) shouldBe true
      composite.matches(Faction("machine cult")) shouldBe false
    }
  }

  "Edition Abstract Factory" should {
    "return correct instances for known edition names" in {
      Edition("core set").nameOfEdition shouldBe "Core Set"
      Edition("colony wars").nameOfEdition shouldBe "Colony Wars"
    }
    "throw an exception for unknown edition names" in {
      an [IllegalArgumentException] should be thrownBy { Edition("pirates set") }
    }
  }
} */