package de.htwg.se.starrealms.model

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class CardSpec extends AnyWordSpec with Matchers {
  "A Card" should {
    val scout = new Ship("Scout", "Ship", None, Some(0), Some(new Ability(List("1 coin"))), None, None)
    val ship = new Ship("Default", "Ship", None, Some(0), None, None, None)
    "have a name" in {
      val card = new Ship("Test Card", "Ship")
      card.getName should be("Test Card")
    }

    "have a cost" in {
      val card = new Ship("Test Card", "Ship")
      card.getCardType should be("Ship")
    }

    "have a faction" in {
      val card = new Ship("Test Card", "Ship", Some(new TradeFederation))
      card.getFaction should be(Some(new TradeFederation))
    }

    "have no faction" in {
      val card = new Ship("Test Card", "Ship", None)
      card.getFaction should be(None)
    }

    "have a primary ability" in {
      val ability = new Ability(List("Draw a card"))
      val card = new Ship("Test Card", "Ship", None, Some(1), Some(ability), None, None)
      card.getPrimaryAbility should be(Some(ability))
    }

    "have no primary ability" in {
      val card = new Ship("Test Card", "Ship", None, None)
      card.getPrimaryAbility should be(None)
    }

    "have an ally ability" in {
      val allyAbility = new Ability(List("Gain 2 Trade"))
      val card = new Ship("Test Card", "Ship", None, None, Some(allyAbility))
      card.getAllyAbility should be(Some(allyAbility))
    }

    "have no ally ability" in {
      val card = new Ship("Test Card", "Ship", None, None, None)
      card.getAllyAbility should be(None)
    }

    "have a scrap ability" in {
      val scrapAbility = new Ability(List("Scrap this card"))
      val card = new Ship("Test Card", "Ship", None, None, None, Some(scrapAbility))
      card.getScrapAbility should be(Some(scrapAbility))
    }

    "have no scrap ability" in {
      val card = new Ship("Test Card", "Ship", None, None, None, None)
      card.getScrapAbility should be(None)
    }
  }

  "A Ship" should {

    "be a ship" in {
      val card = new Ship("Test Card", "Ship")
      card.isShip should be(true)
    }

    "not be a base" in {
      val card = new Ship("Test Card", "Ship")
      card.isBase should be(false)
    }
  }

  "A Base" should {

    "be a base" in {
      val card = new Base("Test Card", "Base", None, 2, "5", true, None, None, None)
      card.isBase should be(true)
    }

    "not be a ship" in {
      val card = new Base("Test Card", "Base", None, 2, "5", true, None, None, None)
      card.isShip should be(false)
    }
    "be an outpost" in {
      val card = new Base("Test Card", "Base", None, 2, "5", true, None, None, None)
      card.isOutpost should be(true)
    }
    "not be an outpost" in {
      val card = new Base("Test Card", "Base", None, 2, "5", false, None, None, None)
      card.isOutpost should be(false)
    }
  }

  "A CardType" should {

    "have a name" in {
      val cardType = "Ship"
      cardType should be("Ship")
    }

    "render its name" in {
      val cardType = "Ship"
      cardType.render() should be("Ship")
    }
  }

  "A Faction" should {

    "have a name" in {
      val faction = new TradeFederation
      faction.getName should be("Trade Federation")
    }

    "render its name" in {
      val faction = new TradeFederation
      faction.render() should be("Trade Federation")
    }
    "be equal to another faction with the same name" in {
      val faction1 = new TradeFederation
      val faction2 = new TradeFederation
      faction1 should be(faction2)
    }
    "not be equal to another faction with a different name" in {
      val faction1 = new TradeFederation
      val faction2 = new StarEmpire
      faction1 should not be faction2
    }
  }

  "A TradeFederation" should {

    "be a faction" in {
      val faction = new TradeFederation
      faction.getName should be("Trade Federation")
    }

    "render its name" in {
      val faction = new TradeFederation
      faction.render() should be("Trade Federation")
    }
  }

  "A StarEmpire" should {

    "be a faction" in {
      val faction = new StarEmpire
      faction.getName should be("Star Empire")
    }

    "render its name" in {
      val faction = new StarEmpire
      faction.render() should be("Star Empire")
    }
  }

  "A Blob" should {

    "be a faction" in {
      val faction = new Blob
      faction.getName should be("Blob")
    }

    "render its name" in {
      val faction = new Blob
      faction.render() should be("Blob")
    }
  }

  "A MachineCult" should {

    "be a faction" in {
      val faction = new MachineCult
      faction.getName should be("Machine Cult")
    }

    "render its name" in {
      val faction = new MachineCult
      faction.render() should be("Machine Cult")
    }
  }

  "An Ability" should {
    "have actions" in {
      val ability = new Ability(List("Draw a card", "Gain 2 Trade"))
      ability.actions should be(List("Draw a card", "Gain 2 Trade"))
    }
    "not have actions" in {
      val ability = new Ability(List())
      ability.actions should be(List())
    }
    "render when actions are present" in {
      val ability = new Ability(List("Draw a card", "Gain 2 Trade"))
      ability.render() should be("Draw a card, Gain 2 Trade")
    }
    "render when no actions are present" in {
      val ability = new Ability(List())
      ability.render() should be("No actions available")
    }

  }
}