package de.htwg.se.starrealms.model

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
  }

  "Set Abstract Factory" should {
    "return correct instances for known set names" in {
      Set("core set").nameOfSet shouldBe "Core Set"
    }
    "throw an exception for unknown set names" in {
      an [IllegalArgumentException] should be thrownBy { Set("pirates set") }
    }
  }
}