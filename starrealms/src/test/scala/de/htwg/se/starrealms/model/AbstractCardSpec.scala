package de.htwg.se.starrealms.model

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

abstract class AbstractCardSpec extends AnyWordSpec with Matchers {
  "AbstractCard" should {
    "have a name" in {
      val card = new TestCard("TestCard", new Ability(List()))
      card.getName should be("TestCard")
    }
    "have at least one ability" in {
      val ability = new Ability(List("TestAbility"))
      val card = new TestCard("TestCard", ability)
      card.getAbility.getActions should contain("TestAbility")
    }
    "have a main ability" in {
      val ability = new Ability(List("TestAbility"))
      val card = new TestCard("TestCard", ability)
      card.getAbility should be(ability)
    }
    "have a toString method" in {
      val card = new TestCard("TestCard", new Ability(List()))
      card.toString should be("Card(name=TestCard, ability=Ability(actions=[]))")
    }
    "be equal to another card with the same name" in {
      val card1 = new TestCard("TestCard", new Ability(List()))
      val card2 = new TestCard("TestCard", new Ability(List()))
      card1 should be(card2)
    }
    "not be equal to another card with a different name" in {
      val card1 = new TestCard("TestCard1", new Ability(List()))
      val card2 = new TestCard("TestCard2", new Ability(List()))
      card1 should not be card2
    }
    "not be equal to null" in {
      val card = new TestCard("TestCard", new Ability(List()))
      card should not be null
    }
    

  }
}