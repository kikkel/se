/* package de.htwg.se.starrealms.model

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

abstract class AbstractDeck extends AnyWordSpec with Matchers {
    "an AbstractDeck" should {
        "have a name" in {
            val cardType = new CardType("TestCardType")
            val deck = new TestDeck("TestDeck", cardType, List())
            deck.getName should be("TestDeck")
        }
        "have a card type" in {
            val cardType = new CardType("TestCardType")
            val deck = new TestDeck("TestDeck", cardType, List())
            deck.getCardType should be(cardType)
        }
        "have a toString method" in {
            val cardType = new CardType("TestCardType")
            val deck = new TestDeck("TestDeck", cardType, List())
            deck.toString should be("TestDeck(name=TestDeck, cardType=CardType(name=TestCardType))")
        }
        "have a shuffle method" in {
            val cardType = new CardType("TestCardType")
            val deck = new TestDeck("TestDeck", cardType, List())
            deck.shuffle() should be(true) // Assuming shuffle always returns true
        }
        "have a drawCard method" in {
            val cardType = new CardType("TestCardType")
            val deck = new TestDeck("TestDeck", cardType, List())
            deck.drawCard() should be(None) // Assuming drawCard returns None when no cards are present
        }

        "have a size method" in {
            val cardType = new CardType("TestCardType")
            val deck = new TestDeck("TestDeck", cardType, List())
            deck.size() should be(0) // Assuming size returns 0 when no cards are present
        }
        "have a isEmpty method" in {
            val cardType = new CardType("TestCardType")
            val deck = new TestDeck("TestDeck", cardType, List())
            deck.isEmpty() should be(true) // Assuming isEmpty returns true when no cards are present
        }
        
    }




}
   */