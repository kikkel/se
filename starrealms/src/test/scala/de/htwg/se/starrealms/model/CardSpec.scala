package de.htwg.se.starrealms.model

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class CardSpec extends AnyWordSpec with Matchers {
  "A Card" should {

    "return its name" in {
      val card = new Ship("Scout")
      card.getName shouldBe "Scout"
    }

    "return its card type" in {
      val card = new Ship("Scout")
      card.getCardType shouldBe "Ship"
    }

    "identify itself as a Ship" in {
      val card = new Ship("Scout")
      card.isShip shouldBe true
      card.isBase shouldBe false
    }

    "identify itself as a Base" in {
      val card = new Base("Outpost", cost = 3, defense = "5", isOutPost = true)
      card.isBase shouldBe true
      card.isShip shouldBe false
    }

    "return its faction if present" in {
      val faction = Some(new TradeFederation)
      val card = new Ship("Scout", faction = faction)
      card.getFaction shouldBe faction
    }

    "return None for faction if not present" in {
      val card = new Ship("Scout")
      card.getFaction shouldBe None
    }

    "return its cost if present" in {
      val card = new Ship("Scout", cost = Some(3))
      card.getCost shouldBe Some(3)
    }
    "return None for cost if not present" in {
      val card = new Ship("Scout")
      card.getCost shouldBe None
    }

    "return its defense if present" in {
      val card = new Base("Outpost", cost = 3, defense = "5", isOutPost = true)
      card.getDefense shouldBe Some("5")
    }

    "return None for defense if not present" in {
      val card = new Ship("Scout")
      card.getDefense shouldBe None
    }

    "return its primary ability if present" in {
      val primaryAbility = Some(PrimaryAbility(List("Gain 3 Trade")))
      val card = new Ship("Scout", primaryAbility = primaryAbility)
      card.getPrimaryAbility shouldBe primaryAbility
    }

    "return None for primary ability if not present" in {
      val card = new Ship("Scout")
      card.getPrimaryAbility shouldBe None
    }

    "return its ally ability if present" in {
      val allyAbility = Some(AllyAbility(List("Gain 5 Authority")))
      val card = new Ship("Scout", allyAbility = allyAbility)
      card.getAllyAbility shouldBe allyAbility
    }

    "return None for ally ability if not present" in {
      val card = new Ship("Scout")
      card.getAllyAbility shouldBe None
    }
    "return its scrap ability if present" in {
      val scrapAbility = Some(ScrapAbility(List("Destroy target base")))
      val card = new Ship("Scout", scrapAbility = scrapAbility)
      card.getScrapAbility shouldBe scrapAbility
    }

    "return None for scrap ability if not present" in {
      val card = new Ship("Scout")
      card.getScrapAbility shouldBe None
    }

    "handle toString method with all attributes" in {
      val faction = Some(new TradeFederation)
      val primaryAbility = Some(PrimaryAbility(List("Gain 3 Trade")))
      val allyAbility = Some(AllyAbility(List("Gain 5 Authority")))
      val scrapAbility = Some(ScrapAbility(List("Destroy target base")))
      val card = new Ship(
        "Scout",
        faction = faction,
        primaryAbility = primaryAbility,
        allyAbility = allyAbility,
        scrapAbility = scrapAbility
      )

      card.toString shouldBe   "Ship: Scout, Faction: Trade Federation, Cost: Unknown, Abilities: " +
        "Primary: Gain 3 Trade, " +
        "Ally: Gain 5 Authority, " +
        "Scrap: Destroy target base"
    }

/*     "handle missing attributes gracefully in the toString method" in {
      val faction = Some(new TradeFederation)
      val primaryAbility = Some(PrimaryAbility(List("Gain 3 Trade")))
      val allyAbility = Some(AllyAbility(List("Gain 5 Authority")))
      val scrapAbility = None
      val card = new Ship(
        "Scout",
        faction = faction,
        primaryAbility = primaryAbility,
        allyAbility = allyAbility,
        scrapAbility = scrapAbility
      )

      card.toString shouldBe "Ship: Scout, Faction: Trade Federation, Cost: Unknown, Abilities: Primary: Gain 3 Trade, Ally: Gain 5 Authority, Scrap: None"
    } */

     "render a detailed string representation" in {
      val faction = Some(new TradeFederation)
      val primaryAbility = Some(PrimaryAbility(List("Gain 3 Trade")))
      val allyAbility = Some(AllyAbility(List("Gain 5 Authority")))
      val scrapAbility = Some(ScrapAbility(List("Destroy target base")))
      val card = new Ship(
        "Scout",
        faction = faction,
        primaryAbility = primaryAbility,
        allyAbility = allyAbility,
        scrapAbility = scrapAbility
      )

      card.render() shouldBe "Card Name: Scout, Card Type: Ship, Faction: Trade Federation, primaryAbility: Gain 3 Trade, allyAbility: Gain 5 Authority, scrapAbility: Destroy target base"
    }

    "handle missing attributes gracefully in the detailed string representation" in {
      val card = new Ship("Scout")
      card.render() shouldBe "Card Name: Scout, Card Type: Ship, Faction: None, primaryAbility: None, allyAbility: None, scrapAbility: None"
    }
  }




  "A Ship" should {
    "handle toString method with all attributes" in {
      val faction = Some(new TradeFederation)
      val primaryAbility = Some(PrimaryAbility(List("Gain 3 Trade")))
      val allyAbility = Some(AllyAbility(List("Gain 5 Authority")))
      val scrapAbility = Some(ScrapAbility(List("Destroy target base")))
      val card = new Ship(
        "Scout",
        faction = faction,
        primaryAbility = primaryAbility,
        allyAbility = allyAbility,
        scrapAbility = scrapAbility
      )

      card.toString shouldBe "Ship: Scout, Faction: Trade Federation, Cost: Unknown, Abilities: Primary: Gain 3 Trade, Ally: Gain 5 Authority, Scrap: Destroy target base"
    }
    "handle missing attributes gracefully in the toString method" in {
      val faction = Some(new TradeFederation)
      val primaryAbility = Some(PrimaryAbility(List("Gain 3 Trade")))
      val allyAbility = Some(AllyAbility(List("Gain 5 Authority")))
      val scrapAbility = None
      val card = new Ship(
        "Scout",
        faction = faction,
        primaryAbility = primaryAbility,
        allyAbility = allyAbility,
        scrapAbility = scrapAbility
      )

      card.toString shouldBe "Ship: Scout, Faction: Trade Federation, Cost: Unknown, Abilities: Primary: Gain 3 Trade, Ally: Gain 5 Authority, Scrap: None"
    }
  }

  "A Base" should {
    "be an outpost" in {
      val card = new Base("Test Card", cost = 3, defense = "5", isOutPost = true)
      card.isOutpost should be(true)
    }
    "not be an outpost" in {
      val card = new Base("Test Card", cost = 3, defense = "5", isOutPost = false)
      card.isOutpost should be(false)
    }
    "handle toString method with all attributes" in {
      val card = new Base("Test Card", cost = 3, defense = "5", isOutPost = true)
      card.toString shouldBe "Base: Test Card, Defense: 5, Outpost: true, Abilities: Primary: None, Ally: None, Scrap: None"
    }
    "handle missing attributes gracefully in the toString method" in {
      val card = new Base("Test Card", cost = 3, defense = "5", isOutPost = false)
      card.toString shouldBe "Base: Test Card, Defense: 5, Outpost: false, Abilities: Primary: None, Ally: None, Scrap: None"
    }


  }

  "A CardType" should {

    "have a name" in {
      val cardType = "Ship"
      cardType should be("Ship")
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
    /* "be equal to another faction with the same name" in {
      val faction1 = new TradeFederation
      val faction2 = new TradeFederation
      faction1 should be(faction2)
    } */
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

    "return the list of actions" in {
      val actions = List("Gain 3 Trade", "Destroy target base")
      val ability = new Ability(actions)

      ability.getActions shouldBe actions
    }

    "check if it has actions" in {
      val abilityWithActions = new Ability(List("Gain 3 Trade"))
      val abilityWithoutActions = new Ability(List())

      abilityWithActions.hasActions shouldBe true
      abilityWithoutActions.hasActions shouldBe false
    }

    "render a string representation of actions" in {
      val actions = List("Gain 3 Trade", "Destroy target base")
      val ability = new Ability(actions)

      ability.render() shouldBe "Gain 3 Trade, Destroy target base"
    }

    "render 'No actions available' if there are no actions" in {
      val ability = new Ability(List())

      ability.render() shouldBe "No actions available"
    }
  }
  "A PrimaryAbility" should {

    "render a string representation of primary actions" in {
      val actions = List("Gain 3 Trade", "Destroy target base")
      val primaryAbility = new PrimaryAbility(actions)

      primaryAbility.render() shouldBe "Gain 3 Trade, Destroy target base"
    }

    "render 'No primary actions available' if there are no actions" in {
      val primaryAbility = new PrimaryAbility(List())

      primaryAbility.render() shouldBe "No primary actions available"
    }
  }
  "An AllyAbility" should {

    "return the list of actions" in {
      val actions = List("Gain 5 Authority", "Draw a card")
      val allyAbility = AllyAbility(actions)

      allyAbility.getActions shouldBe actions
    }

    "check if it has actions" in {
      val allyAbilityWithActions = AllyAbility(List("Gain 5 Authority"))
      val allyAbilityWithoutActions = AllyAbility(List())

      allyAbilityWithActions.hasActions shouldBe true
      allyAbilityWithoutActions.hasActions shouldBe false
    }

    "render a string representation of ally actions" in {
      val actions = List("Gain 5 Authority", "Draw a card")
      val allyAbility = AllyAbility(actions)

      allyAbility.render() shouldBe "Gain 5 Authority, Draw a card"
    }

    "render 'No ally actions available' if there are no actions" in {
      val allyAbility = AllyAbility(List())

      allyAbility.render() shouldBe "No ally actions available"
    }
  }
  "A ScrapAbility" should {

    "return the list of actions" in {
      val actions = List("Destroy target base", "Gain 2 Trade")
      val scrapAbility = ScrapAbility(actions)

      scrapAbility.getActions shouldBe actions
    }

    "check if it has actions" in {
      val scrapAbilityWithActions = ScrapAbility(List("Destroy target base"))
      val scrapAbilityWithoutActions = ScrapAbility(List())

      scrapAbilityWithActions.hasActions shouldBe true
      scrapAbilityWithoutActions.hasActions shouldBe false
    }

    "render a string representation of scrap actions" in {
      val actions = List("Destroy target base", "Gain 2 Trade")
      val scrapAbility = ScrapAbility(actions)

      scrapAbility.render() shouldBe "Destroy target base, Gain 2 Trade"
    }

    "render 'No scrap actions available' if there are no actions" in {
      val scrapAbility = ScrapAbility(List())

      scrapAbility.render() shouldBe "No scrap actions available"
    }
  }
}
