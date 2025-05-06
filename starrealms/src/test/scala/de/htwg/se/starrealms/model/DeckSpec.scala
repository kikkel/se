package de.htwg.se.starrealms.model

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class DeckSpec extends AnyWordSpec with Matchers {

    "A Deck" should {
        /* "be empty when initialized with no cards" in {
        val deck = new DefaultDeck("TestDeck", "Scout", List())
        deck.isEmpty should be(true)
        } */

        "not be empty when initialized with cards" in {
        val deck = new DefaultDeck("TestDeck", "Scout", List())
        deck.isEmpty should be(false)
        }

        "add a card to the deck" in {
        val card = CardFactory.createCard("Scout")
        val deck = new DefaultDeck("TestDeck", "Scout", List(card))

        deck.addCard(card)
        deck.getCards should contain(card)
        }

        "remove a card from the deck" in {
        val card = CardFactory.createCard("Scout")
        val deck = new DefaultDeck("TestDeck", "Scout", List(card))
        deck.removeCard(card)
        deck.getCards should not contain card
        }

        "shuffle the deck" in {
        val deck = new DefaultDeck("TestDeck", "Scout", List(CardFactory.createCard("Scout"), CardFactory.createCard("Viper")))
        val originalOrder = deck.getCards
        deck.shuffle()
        deck.getCards should not equal originalOrder
        }

        /* "draw a card from the deck" in {
        val card = CardFactory.createCard("Scout")
        val deck = new DefaultDeck("TestDeck", "Scout", List(card))
        val drawnCard = deck.drawCard()
        drawnCard shouldEqual Some(card)
        }*/

        /* "return None when drawing from an empty deck" in {
        val deck = new DefaultDeck("TestDeck", "Scout", List())
        val drawnCard = deck.drawCard()
        drawnCard should be(None)
        } */
    }
    "A DefaultDeck" should {
        "have a default state with 8 Scouts and 2 Vipers" in {
            val deck = new DefaultDeck("TestDeck", "Scout", List())
            deck.getCards should have size 10
            deck.getCards.count(_.name == "Scout") should be(8)
            deck.getCards.count(_.name == "Viper") should be(2)
        }
        "draw a card and remove it from the deck" in {
            val deck = new DefaultDeck("TestDeck", "Scout", List())
            val drawnCard = deck.drawCard()
            drawnCard should not be None
            deck.getCards should have size 9
        }
        "return the deck state as a string" in {
            val deck = new DefaultDeck("TestDeck", "Scout", List())
            val state = deck.getDeckState
            state should include("Scout")
            state should include("Viper")
        }
        /* "view the discard pile" in {
            val deck = new DefaultDeck("TestDeck", "Scout", List())
            discardPile should include("Scout")
            discardPile should include("Viper")
        } */

        "reset to its default state" in {
            val deck = new DefaultDeck("TestDeck", "Scout", List())
            deck.resetDeck()
            deck.getCards should have size 10
            deck.getCards.count(_.name == "Scout") should be(8)
            deck.getCards.count(_.name == "Viper") should be(2)
        }
    }
}