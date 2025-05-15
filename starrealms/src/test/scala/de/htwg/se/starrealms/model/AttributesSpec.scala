package de.htwg.se.starrealms.model

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec
import de.htwg.se.starrealms.model.Ability

class AttributesSpec extends AnyWordSpec with Matchers {

  "An Ability" should {
    "return its actions" in {
      val ability = new Ability(List("Attack", "Heal"))
      ability.getActions should contain allOf ("Attack", "Heal")
    }

    "check if it has actions" in {
      val abilityWithActions = new Ability(List("Attack"))
      val abilityWithoutActions = new Ability(List())
      abilityWithActions.hasActions shouldBe true
      abilityWithoutActions.hasActions shouldBe false
    }

    "render its actions as a string" in {
      val ability = new Ability(List("Attack", "Heal"))
      ability.render() shouldBe "Attack, Heal"

      val emptyAbility = new Ability(List())
      emptyAbility.render() shouldBe "No actions available"
    }
  }

  "A PrimaryAbility" should {
    "render its actions as a string" in {
      val primaryAbility = PrimaryAbility(List("Primary Attack"))
      primaryAbility.render() shouldBe "Primary Attack"

      val emptyPrimaryAbility = PrimaryAbility(List())
      emptyPrimaryAbility.render() shouldBe "No primary actions available"
    }
  }
   "An AllyAbility" should {
    "render its actions as a string" in {
      val allyAbility = AllyAbility(List("Ally Heal"))
      allyAbility.render() shouldBe "Ally Heal"

      val emptyAllyAbility = AllyAbility(List())
      emptyAllyAbility.render() shouldBe "No ally actions available"
    }
  }

  "A ScrapAbility" should {
    "render its actions as a string" in {
      val scrapAbility = ScrapAbility(List("Scrap Draw"))
      scrapAbility.render() shouldBe "Scrap Draw"

      val emptyScrapAbility = ScrapAbility(List())
      emptyScrapAbility.render() shouldBe "No scrap actions available"
    }
  }

  "A CardCost" should {
    "return its cost" in {
      val cardCost = new CardCost(5)
      cardCost.getCost shouldBe 5
    }

    "check if the card is free" in {
      val freeCard = new CardCost(0)
      val paidCard = new CardCost(3)
      freeCard.isFree shouldBe true
      paidCard.isFree shouldBe false
    }
  }
  "A CardDamage" should {
    "return its damage" in {
      val cardDamage = new CardDamage(10)
      cardDamage.getDamage shouldBe 10
    }

    "check if the card has no damage" in {
      val noDamageCard = new CardDamage(0)
      val damageCard = new CardDamage(5)
      noDamageCard.isNoDamage shouldBe true
      damageCard.isNoDamage shouldBe false
    }
  }

  "A CardDefense" should {
    "return its defense" in {
      val cardDefense = new CardDefense(8)
      cardDefense.getDefense shouldBe 8
    }

    "check if the card has no defense" in {
      val noDefenseCard = new CardDefense(0)
      val defenseCard = new CardDefense(4)
      noDefenseCard.isNoDefense shouldBe true
      defenseCard.isNoDefense shouldBe false
    }
  }
}

