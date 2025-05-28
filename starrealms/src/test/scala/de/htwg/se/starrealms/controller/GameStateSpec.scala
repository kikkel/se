package de.htwg.se.starrealms.controller

import de.htwg.se.starrealms.model._
import de.htwg.util._
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec
import scala.util.Success

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
        cardType = Success(new Ship()),
        qty = 1,
        role = "Trade Deck"
      )
      val mdeck = new Deck {
        override def drawCard(): Option[Card] = {
          drawCardCalled = true
          Some(card)
        }
      }
      val gameState = new GameState { override val playerDeck: Deck = mdeck }
      gameState.addObserver(observer)

      val drawnCard = gameState.drawCard()

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
      val observer = new Observer { override def update: Unit = { updateCalled = true } }
      val gameState = new GameState { override val playerDeck: Deck = mdeck }
      gameState.addObserver(observer)

      gameState.resetGame()

      assert(resetDeckCalled, "resetDeck should have been called")
      assert(updateCalled, "update should have been called")
    }

    "test playCard, buyCard, returnCardToPlayerDeck, returnCardToHand, undoReplenish, returnCardToTradeRow, endTurn, undoEndTurn, undoResetGame, getHand, getTradeRow, getDeckState" in {
      val card = new DefaultCard(
        set = Set("Core Set"),
        cardName = "Test Card",
        primaryAbility = None,
        faction = Faction("Unaligned"),
        cardType = Success(new Ship()),
        qty = 1,
        role = "Trade Deck"
      )
      val gameState = new GameState

      // playCard
      gameState.drawCards(1)
      val handBefore = gameState.getHand.size
      gameState.playCard(gameState.getHand.head)
      gameState.getHand.size shouldBe (handBefore - 1)

      // buyCard
      gameState.initTradeRow()
      val tradeRowBefore = gameState.getTradeRow.size
      if (gameState.getTradeRow.nonEmpty) {
        val buyCard = gameState.getTradeRow.head
        gameState.buyCard(buyCard)
        gameState.getTradeRow.size should be <= tradeRowBefore
      }

      // returnCardToPlayerDeck
      gameState.drawCards(1)
      if (gameState.getHand.nonEmpty) {
        val cardToReturn = gameState.getHand.head
        gameState.returnCardToPlayerDeck(cardToReturn)
        gameState.getHand should not contain cardToReturn
      }

      // returnCardToHand
      gameState.drawCards(1)
      if (gameState.getHand.nonEmpty) {
        val cardToReturn = gameState.getHand.head
        gameState.playCard(cardToReturn)
        gameState.returnCardToHand(cardToReturn)
        gameState.getHand should contain(cardToReturn)
      }

      // undoReplenish
      gameState.initTradeRow()
      if (gameState.getTradeRow.nonEmpty) {
        val cardToUndo = gameState.getTradeRow.head
        gameState.undoReplenish(cardToUndo)
        gameState.getTradeRow should not contain cardToUndo
        gameState.tradeDeck.getCards should contain(cardToUndo)

      }

      // returnCardToTradeRow
      gameState.initTradeRow()
      if (gameState.getTradeRow.nonEmpty) {
        val cardToReturn = gameState.getTradeRow.head
        gameState.buyCard(cardToReturn)
        gameState.returnCardToTradeRow(cardToReturn)
        gameState.getTradeRow should contain(cardToReturn)
      }

      // endTurn und undoEndTurn
      gameState.drawCards(5)
      gameState.endTurn()
      gameState.undoEndTurn()

      // undoResetGame
      gameState.undoResetGame()

      // getHand, getTradeRow, getDeckState
      gameState.getHand shouldBe a [List[_]]
      gameState.getTradeRow shouldBe a [List[_]]
      gameState.getDeckState shouldBe a [String]
    }
    
  }
}