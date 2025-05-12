/* package de.htwg.se.starrealms.model

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

//import de.htwg.se.starrealms.model._


class DeckBuilderSpec extends AnyWordSpec with Matchers {
    "A Deck" should {
        "initialize with an empty name" in {
            val deck = new Deck("", List())
            deck.getName should be("")
        }
        
        "be empty when initialized" in {
            val deck = new Deck("TestDeck", List())
            deck.isEmpty should be(true)
        }

        "receive initial cards" in {

        }

        "have a name" in {
            val deck = new Deck("TestDeck", List())
            deck.getName should be("TestDeck")
        }

        "add a card to the deck" in {
            val deck = new Deck("TestDeck", List())
            val card = new Card("TestCard", 1, "Ship")
            deck.addCard(card)
            deck.getCards should contain(card)
        }

        "remove a card from the deck" in {
            val card = new Card("TestCard", 1, "Ship")
            val deck = new Deck("TestDeck", List(card))
            deck.removeCard(card)
            deck.getCards should not contain card
        }
    }

}
 */