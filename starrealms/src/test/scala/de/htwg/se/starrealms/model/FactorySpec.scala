package de.htwg.se.starrealms.model

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class FactorySpec extends AnyWordSpec with Matchers {
  "Faction Abstract Factory" should {
    "create a TradeFederation faction" in {
      val faction = Faction("trade federation")
      faction.factionName shouldBe "Trade Federation"
      faction.colour shouldBe "Blue"
    }

    "create a StarEmpire faction" in {
      val faction = Faction("star empire")
      faction.factionName shouldBe "Star Empire"
      faction.colour shouldBe "Yellow"
    }

    "create a Blob faction" in {
      val faction = Faction("blob")
      faction.factionName shouldBe "Blob"
      faction.colour shouldBe "Green"
    }

    "create a MachineCult faction" in {
      val faction = Faction("machine cult")
      faction.factionName shouldBe "Machine Cult"
      faction.colour shouldBe "Red"
    }

    "throw an exception for an unknown faction" in {
      val exception = intercept[IllegalArgumentException] {
        Faction("unknown faction")
      }
      exception.getMessage should include("Unknown faction: unknown faction")
    }
  }

  "Set Abstract Factory" should {
    "create a CoreSet" in {
      val set = Set("core set")
      set.nameOfSet shouldBe "Core Set"
    }

    "throw an exception for an unknown set" in {
      val exception = intercept[IllegalArgumentException] {
        Set("unknown set")
      }
      exception.getMessage should include("Unknown set: unknown set")
    }
  }
}


/* package de.htwg.se.starrealms.model

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class CardFactorySpec extends AnyWordSpec with Matchers {

  "The CardFactory" should {

    "create a Scout card" in {
      val card = CardFactory.createCard("Scout")
      card.getName shouldBe "Scout"
      card.getCardType shouldBe "Ship"
      card.getCost shouldBe Some(0)
      card.getPrimaryAbility.map(_.render()) shouldBe Some("1 coin")
      card.getAllyAbility shouldBe None
      card.getScrapAbility shouldBe None
    }

    "create a Viper card" in {
      val card = CardFactory.createCard("Viper")
      card.getName shouldBe "Viper"
      card.getCardType shouldBe "Ship"
      card.getCost shouldBe Some(0)
      card.getPrimaryAbility.map(_.render()) shouldBe Some("1 damage")
      card.getAllyAbility shouldBe None
      card.getScrapAbility shouldBe None
    }

    "create an Explorer card" in {
      val card = CardFactory.createCard("Explorer")
      card.getName shouldBe "Explorer"
      card.getCardType shouldBe "Ship"
      card.getCost shouldBe Some(2)
      card.getPrimaryAbility.map(_.render()) shouldBe Some("2 coins")
      card.getAllyAbility shouldBe None
      card.getScrapAbility.map(_.render()) shouldBe Some("2 damage")
    }

    "create a Trade Federation Base card" in {
      val card = CardFactory.createCard("Trade Federation Base")
      card.getName shouldBe "Trade Federation Base"
      card.getCardType shouldBe "Base"
      card.getCost shouldBe Some(4)
      card.getDefense shouldBe Some("5")
      card.isBase shouldBe true
      //card.isOutPost shouldBe false
      card.getPrimaryAbility.map(_.render()) shouldBe Some("Gain 4 authority")
    }

    "create a Blob Base card" in {
      val card = CardFactory.createCard("Blob Base")
      card.getName shouldBe "Blob Base"
      card.getCardType shouldBe "Base"
      card.getCost shouldBe Some(5)
      card.getDefense shouldBe Some("5")
      card.isBase shouldBe true
      //card.isOutPost shouldBe true
      card.getPrimaryAbility.map(_.render()) shouldBe Some("Gain 3 damage")
    }

    "create a Star Empire Base card" in {
      val card = CardFactory.createCard("Star Empire Base")
      card.getName shouldBe "Star Empire Base"
      card.getCardType shouldBe "Base"
      card.getCost shouldBe Some(4)
      card.getDefense shouldBe Some("1")
      card.isBase shouldBe true
      //card.isOutPost shouldBe false
      card.getPrimaryAbility.map(_.render()) shouldBe Some("Draw a card")
    }

    "create a Machine Cult Base card" in {
      val card = CardFactory.createCard("Machine Cult Base")
      card.getName shouldBe "Machine Cult Base"
      card.getCardType shouldBe "Base"
      card.getCost shouldBe Some(5)
      card.getDefense shouldBe Some("2")
      card.isBase shouldBe true
      //card.isOutPost shouldBe true
      card.getPrimaryAbility.map(_.render()) shouldBe Some("Scrap a card from your hand or discard pile")
    }

    "throw an exception for an unknown card type" in {
      val exception = intercept[IllegalArgumentException] {
        CardFactory.createCard("Unknown Card")
      }
      exception.getMessage should include("Unknown card type: Unknown Card")
    }
  }
}

 */