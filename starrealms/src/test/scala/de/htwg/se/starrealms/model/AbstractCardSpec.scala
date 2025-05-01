package de.htwg.se.starrealms.model

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

abstract class AbstractCardSpec extends AnyWordSpec with Matchers {
  "an AbstractCard" should {
    val ability = new Ability(List("TestAbility"))
    val cardType = new CardType("TestCardType")
    val card = new TestCard("TestCard", cardType, ability)

    "have a name" in {
      card.getName should be("TestCard")
    }
    "belong to "
    "have at least one ability" in {
      card.getAbility.getActions should contain("TestAbility")
    }
    "have a main ability" in {
      card.getAbility should be(ability)
    }
    "have a toString method" in {
      card.toString should be("Card(name=TestCard, cardType=CardType, ability=Ability(actions=[]))")
    }
    "be equal to another card with the same name" in {
      val card1 = new TestCard("TestCard", new CardType("TestCardType"), new Ability(List()))
      val card2 = new TestCard("TestCard", new CardType("TestCardType"), new Ability(List()))
      card1 should be(card2)
    }
    "not be equal to another card with a different name" in {
      val card1 = new TestCard("TestCard1", new CardType("TestCardType"), new Ability(List()))
      val card2 = new TestCard("TestCard2", new CardType("TestCardType"), new Ability(List()))
      card1 should not be card2
    }
    "not be equal to null" in {
      card should not be null
    }
    

  }
}