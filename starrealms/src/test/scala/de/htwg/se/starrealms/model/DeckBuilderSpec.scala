package de.htwg.se.starrealms.model

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class DeckBuilderSpec extends AnyWordSpec with Matchers {
  val testSet: Set = Set("Core Set")
  val testFaction: Faction = Faction("Unaligned")
  def testCard(name: String): Card = new DefaultCard(
      set = testSet,
      cardName = name,
      primaryAbility = None,
      faction = testFaction,
      cardType = new Ship(),
      qty = 1,
      role = "Test Deck"
    )

  "A Deck" should {
    "set and get name and cards properly" in {
        val deck = new Deck()
        deck.setName("Test Deck")
        deck.getName should be("Test Deck")

        val cards = List(testCard("Viper"), testCard("Scout"))
        deck.setCards(cards)
        deck.getCards should contain theSameElementsAs cards
    }

    "add and remove cards correctly" in {
        val deck = new Deck()
        val card1 = testCard("Viper")
        val card2 = testCard("Scout")
        deck.addCard(card1)
        deck.addCard(card2)
        deck.getCards should contain allOf (card1, card2)

        deck.removeCard(card1)
        deck.getCards should contain only card2
    }
  }

  "A DeckBuilder" should {
    "build a deck and reset itself afterward" in {
        val builder = new DeckBuilder()
        val cards = List(testCard("Viper"), testCard("Scout"))
        builder.setName("Test Deck")
        builder.setCards(cards)
        val builtDeck = builder.getProduct()

        builtDeck.getName shouldBe "Test Deck"
        builtDeck.getCards should contain theSameElementsAs cards

        val resetDeck =  builder.getProduct()
        resetDeck.getName shouldBe empty
    }

    "add cards individually and in bulk" in {
        val builder = new DeckBuilder()
        val card1 = testCard("Viper")
        val card2 = testCard("Scout")
        builder.setName("Test Deck")
        builder.addCard(card1)
        builder.addCards(List(card2))

        val builtDeck = builder.getProduct()
        builtDeck.getName shouldBe "Test Deck"
        builtDeck.getCards should contain allOf (card1, card2)
    }
  }


}