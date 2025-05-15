package de.htwg.se.starrealms.controller

import de.htwg.se.starrealms.model._
import de.htwg.util._
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec


class GameStateSpec extends AnyWordSpec with Matchers {

  "A GameState" should {
    var updateCalled = false
    "draw a card and notify observers" in {
      var drawCardCalled = false
      val observer = new Observer { override def update: Unit = { updateCalled = true } }

      val card = new DefaultCard(
        set = Set("Core Set"),
        cardName = "Test Card",
        primaryAbility = None,
        faction = Faction("Unaligned"),
        cardType = new Ship(),
        qty = 1,
        role = "Trade Deck"
      )
      val mdeck = new Deck { 
        override def drawCard(): Option[Card] = {
          drawCardCalled = true
          Some(card) 
        }
      }
      val gameState = new GameState { override val deck: Deck = mdeck}

      gameState.addObserver(observer)

      val drawnCard = gameState.drawCard("Ship")

      drawnCard should not be (None)

      assert(drawCardCalled, "drawCard should have been called")

      assert(updateCalled, "update should have been called")
    }
    "reset the game state and notify observers" in {
      var resetDeckCalled = false
      val mdeck = new Deck {
        override def resetDeck(): Unit = {
          resetDeckCalled = true
        }
      }

      val observer = new Observer { override def update: Unit = { updateCalled} }
      val gameState = new GameState  { override val deck: Deck = mdeck}
      gameState.addObserver(observer)

      gameState.reset()

      assert(resetDeckCalled, "resetDeck should have been called")
      assert(updateCalled, "update should have been called")
    
    }
  }
}