package de.htwg.se.starrealms.model

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec
import de.htwg.se.starrealms.model._

class DefaultDeckSpec extends AnyWordSpec with Matchers {
  "A DefaultDeck" should {
    val deck = new DefaultDeck("DefaultDeck", new CardType("Default"), List())
    
    "initialize with default cards" in {
      deck.getCards.count(_.isInstanceOf[ScoutCard]) shouldEqual 8
      deck.getCards.count(_.isInstanceOf[ViperCard]) shouldEqual 2
    }
    "be empty" in {
      deck.isEmpty shouldBe false
    }
    "have a name" in {
      deck.getName shouldEqual "DefaultDeck"
    }
   /*  "have an addCard method" in {
      val newCard = new ScoutCard()
      deck.addCard(newCard)
      deck.getCards should contain(newCard)
    }
    "have a removeCard method" in {
      val cardToRemove = deck.getCards.head
      deck.removeCard(cardToRemove)
      deck.getCards should not contain cardToRemove
    }
    "have a shuffle method" in {
      val initialOrder = deck.getCards
      deck.shuffle()
      deck.getCards should not equal initialOrder
    } */
    "have a drawCard method" in {
      val drawnCard = deck.drawCard()

      drawnCard shouldBe defined
      deck.getCards should not contain drawnCard.get
    }

/*     "have a drawCard method that returns None when the deck is empty" in {
      val emptyDeck = new DefaultDeck("EmptyDeck", new CardType("Empty"), List())
      emptyDeck.drawCard() shouldBe None
    } */

/*     "have a viewDiscardPile method" in {
      deck.viewDiscardPile shouldEqual "Empty"
    } */

    "reset the deck to its default state" in {
      deck.drawCard()
      deck.resetDeck()
      deck.getCards.count(_.isInstanceOf[ScoutCard]) shouldEqual 8
      deck.getCards.count(_.isInstanceOf[ViperCard]) shouldEqual 2
    }
  }
}


/* package de.htwg.se.starrealms.model

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class DefaultDeckSpec extends AnyWordSpec with Matchers {

  "A DefaultDeck" should {
    "initialize with 8 Scouts and 2 Vipers" in {
      val deck = new DefaultDeck()
      deck.getDeckState should include("Scout")
      deck.getDeckState should include("Viper")
    }
    "allow drawing a Scout card" in {
      val deck = new DefaultDeck()
      deck.drawCard("scout") should be(Some("Scout"))
      deck.getDeckState should not include "Scout"
    }

    "allow drawing a Viper card" in {
      val deck = new DefaultDeck()
      deck.drawCard("viper") should be(Some("Viper"))
      deck.getDeckState should not include "Viper"
    }
    "return None when no cards of the requested type are left" in {
      val deck = new DefaultDeck()
      (1 to 8).foreach(_ => deck.drawCard("scout"))
      deck.drawCard("scout") should be(None)
    } 
    "return an empty deck state when all cards are drawn" in {
      val deck = new DefaultDeck()
      (1 to 10).foreach(_ => deck.drawCard("scout") orElse deck.drawCard("viper"))
      deck.getDeckState should be("Empty")
    }           
    "contain only DefaultCards" in {
      val deck = new DefaultDeck()
      deck.getAllCards.forall(_.isInstanceOf[DefaultCard]) should be(true)
    }
    "have ScoutCards with the correct ability" in {
      val deck = new DefaultDeck()
      deck.getScoutCards.foreach(_.getAbility.actions should contain("1 coin"))
    }
    "have ViperCards with the correct ability" in {
      val deck = new DefaultDeck()
      deck.getViperCards.foreach(_.getAbility.actions should contain("1 damage"))
    }
  }
} */