/* package de.htwg.se.starrealms.model

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

abstract class AbstractDeck extends AnyWordSpec with Matchers {
    "an AbstractDeck" should {
         "be empty when initialized" in {
            val deck = new TestDeck("TestDeck", List())
            deck.isEmpty should be(true)
         }
        "not be empty when cards are added" in {
            val deck = new TestDeck("TestDeck", List())
            deck.addCard(new TestCard("TestCard"))
            deck.isEmpty should be(false)
        }
        "have a name" in {
            val deck = new TestDeck("TestDeck", List())
            deck.getName should be("DefaultDeck")
        }
        "have an addCard method" in {
            val deck = new TestDeck("TestDeck", List())
            val newCard = new TestCard("TestCard")
            deck.addCard(newCard)
            deck.getCards should contain(newCard)
        }
        "have a removeCard method" in {
            val deck = new TestDeck("TestDeck", List())
            val cardToRemove = new TestCard("TestCard")
            deck.addCard(cardToRemove)
            deck.removeCard(cardToRemove)
            deck.getCards should not contain cardToRemove
        }
        "have a shuffle method" in {
            val deck = new TestDeck("TestDeck", List())
            val initialOrder = deck.getCards
            deck.shuffle()
            deck.getCards should not equal initialOrder
        }
        "have a drawCard method" in {
            val deck = new TestDeck("TestDeck", List())
            val drawnCard = deck.drawCard()

            drawnCard shouldBe defined
            deck.getCards should not contain drawnCard.get
        }
        "have a drawCard method that returns None when the deck is empty" in {
            val emptyDeck = new TestDeck("TestDeck", List())
            emptyDeck.drawCard() shouldBe None
        }
       


    }
}
   */