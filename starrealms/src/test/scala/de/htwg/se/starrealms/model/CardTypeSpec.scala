package de.htwg.se.starrealms.model

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class CardTypeSpec extends AnyWordSpec with Matchers {
  "a CardType" should {
    "have a name" in {
      val cardType = new CardType("TestCardType")
      cardType.getName should be("TestCardType")
    }
    "have a toString method" in {
        val cardType = new CardType("TestCardType")
        cardType.toString should be("CardType(name=TestCardType)")
    }
    "be equal to another card type with the same name" in {
      val cardType1 = new CardType("TestCardType")
      val cardType2 = new CardType("TestCardType")
      cardType1 should be(cardType2)
    }
    "not be equal to another card type with a different name" in {
      val cardType1 = new CardType("TestCardType1")
      val cardType2 = new CardType("TestCardType2")
      cardType1 should not be cardType2
    }
    "not be equal to null" in {
      val cardType = new CardType("TestCardType")
      cardType should not be null
    }
  }
}