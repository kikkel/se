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
    cardType = scala.util.Success(new Ship()),
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

    "shuffle cards" in {
      val deck = new Deck()
      val cards = (1 to 10).map(i => testCard(s"Card$i")).toList
      deck.setCards(cards)
      deck.shuffle()
      deck.getCards.sorted(Ordering.by[Card, String](_.cardName)) should contain theSameElementsAs cards
    }

    "draw cards and handle empty deck" in {
      val deck = new Deck()
      val card = testCard("Scout")
      deck.addCard(card)
      deck.drawCard() should contain(card)
      deck.drawCard() shouldBe None
    }

    "reset deck" in {
      val deck = new Deck()
      deck.addCard(testCard("Scout"))
      deck.resetDeck()
      deck.getCards shouldBe empty
    }

    "render itself" in {
      val deck = new Deck()
      deck.setName("Test Deck")
      deck.addCard(testCard("Scout"))
      deck.render() should include ("Test Deck")
      deck.render() should include ("Scout")
    }
  }

  "Deck companion object" should {
    "create a standard player deck" in {
      val deck = Deck.standardPlayerDeck()
      deck.getName shouldBe "Player Deck"
      val names = deck.getCards.map(_.cardName)
      names.count(_ == "Scout") shouldBe 8
      names.count(_ == "Viper") shouldBe 2
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

      val resetDeck = builder.getProduct()
      resetDeck.getName shouldBe empty
      resetDeck.getCards shouldBe empty
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

  "A Director" should {
    "construct decks from a map" in {
      val builder = new DeckBuilder()
      val deck1 = new Deck(); deck1.setName("A"); deck1.addCard(testCard("Scout"))
      val deck2 = new Deck(); deck2.setName("B"); deck2.addCard(testCard("Viper"))
      val decksByRole = Map("A" -> deck1, "B" -> deck2)
      val result = new Director().constructDecks(builder, decksByRole)
      result.keySet should contain allOf ("A", "B")
      result("A").getName shouldBe "A"
      result("B").getName shouldBe "B"
      result("A").getCards.map(_.cardName) should contain ("Scout")
      result("B").getCards.map(_.cardName) should contain ("Viper")
    }
  }

  "A Builder trait" should {
    "be implemented by DeckBuilder" in {
      val builder: Builder = new DeckBuilder()
      builder.setName("Test Deck")
      builder.setCards(List(testCard("Scout")))
      builder.addCard(testCard("Viper"))
      builder.addCards(List(testCard("Scout")))
      val deck = builder.getProduct()
      deck.getName shouldBe "Test Deck"
      deck.getCards.map(_.cardName) should contain ("Scout")
      deck.getCards.map(_.cardName) should contain ("Viper")
    }
  }
}