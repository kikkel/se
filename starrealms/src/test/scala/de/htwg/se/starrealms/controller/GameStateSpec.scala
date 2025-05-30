package de.htwg.se.starrealms.controller

import de.htwg.se.starrealms.model._
import de.htwg.util._
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec
import scala.util.Success

class GameStateSpec extends AnyWordSpec with Matchers {

  "A GameState" should {
    var updateCalled = false
    val gameLogic = new GameLogic(new GameState(Map(), Player("Player1", 50), Player("Player2", 50)) ) {
      override def notifyObservers(): Unit = { updateCalled = true }
    }
   
 


    "draw a card and notify observers" in {
      var drawCardCalled = false
      val observer = new Observer { override def update: Unit = { updateCalled = true } }

      val card = new DefaultCard(
        edition = Edition("Core Set"),
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

      val drawnCard = gameLogic.drawCard()

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

      gameLogic.resetGame()

      assert(resetDeckCalled, "resetDeck should have been called")
      assert(updateCalled, "update should have been called")
    }

    "test playCard, buyCard, returnCardToPlayerDeck, returnCardToHand, undoReplenish, returnCardToTradeRow, endTurn, undoEndTurn, undoResetGame, getHand, getTradeRow, getDeckState" in {
      val card = new DefaultCard(
        edition = Edition("Core Set"),
        cardName = "Test Card",
        primaryAbility = None,
        faction = Faction("Unaligned"),
        cardType = Success(new Ship()),
        qty = 1,
        role = "Trade Deck"
      )
      val gameState = new GameState(
        gameLogic.gameState.decksByRole,
        Player("Player1", 50),
        Player("Player2", 50)
      )
      val player1 = gameState.getCurrentPlayer


      // playCard
      gameLogic.drawCards(1)
      val handBefore = gameLogic.gameState.getHand(player1).size
      gameLogic.playCard(gameLogic.gameState.getHand(player1).head)
      gameLogic.gameState.getHand(player1).size shouldBe (handBefore - 1)

      // buyCard
      gameLogic.gameState.getTradeRow
      val tradeRowBefore = gameState.getTradeRow.size
      if (gameState.getTradeRow.nonEmpty) {
        val buyCard = gameState.getTradeRow.head
        gameLogic.buyCard(buyCard)
        gameState.getTradeRow.size should be <= tradeRowBefore
      }

      // returnCardToPlayerDeck
      gameLogic.drawCards(1)
      if (gameLogic.gameState.getHand(player1).nonEmpty) {
        val cardToReturn = gameState.getHand(player1).head
        gameLogic.returnCardToPlayerDeck(cardToReturn)
        gameState.getHand(player1) should not contain cardToReturn
      }

      // returnCardToHand
      gameLogic.drawCards(1)
      if (gameState.getHand(player1).nonEmpty) {
        val cardToReturn = gameState.getHand(player1).head
        gameLogic.playCard(cardToReturn)
        gameLogic.returnCardToHand(cardToReturn)
        gameState.getHand(player1) should contain(cardToReturn)
      }

      // undoReplenish
      gameLogic.replenishTradeRow()
      if (gameState.getTradeRow.nonEmpty) {
        val cardToUndo = gameState.getTradeRow.head
        gameLogic.undoReplenish(cardToUndo)
        gameState.getTradeRow should not contain cardToUndo
        gameState.getTradeDeck.getCards should contain(cardToUndo)

      }

      // returnCardToTradeRow
      gameLogic.replenishTradeRow()
      if (gameState.getTradeRow.nonEmpty) {
        val cardToReturn = gameState.getTradeRow.head
        gameLogic.buyCard(cardToReturn)
        gameLogic.returnCardToTradeRow(cardToReturn)
        gameState.getTradeRow should contain(cardToReturn)
      }

      // endTurn und undoEndTurn
      gameLogic.drawCards(5)
      gameLogic.endTurn()
      gameLogic.undoEndTurn()

      // undoResetGame
      gameLogic.undoResetGame()

      // getHand, getTradeRow, getDeckState
      //gameState.getHand(player1). shouldBe a [List[_]]
      gameState.getTradeRow shouldBe a [List[_]]
      gameState.getDeckState shouldBe a [String]
    }
    
    "allow access to basic getters and state updates" in {
      val p1 = Player("Player1", health = 50)
      val p2 = Player("Player2", health = 50)
      val decks = Map("Personal Deck" -> new Deck(), "Trade Deck" -> new Deck(), "Explorer Pile" -> new Deck())
      val gameState = new GameState(decks, p1, p2)

      gameState.getCurrentPlayer shouldBe p1
      gameState.getOpponent shouldBe p2

      gameState.setCurrentPlayer(p2)
      gameState.setOpponent(p1)
      gameState.getCurrentPlayer shouldBe p2
      gameState.getOpponent shouldBe p1

      val newDeck = new Deck()
      gameState.setPlayerDeck(p1, newDeck)
      gameState.getPlayerDeck(p1) shouldBe newDeck

      gameState.getDiscardPile(p1) shouldBe empty
      gameState.getDiscardPiles should contain key p1

      gameState.setLastDiscardedHand(p1, List())
      gameState.getLastDiscardedHand(p1) shouldBe empty

      val tradeDeck = new Deck()
      val explorerDeck = new Deck()
      gameState.setTradeDeck(tradeDeck)
      gameState.setExplorerPile(explorerDeck)
      gameState.getTradeDeck shouldBe tradeDeck
      gameState.getExplorerPile shouldBe explorerDeck

      noException should be thrownBy gameState.notifyStateChange()
    }

    "check game over conditions correctly" in {
      val p1dead = Player("P1", health = 0)
      val p2alive = Player("P2", health = 50)
      val gameOverState = new GameState(Map(), p1dead, p2alive)
      gameOverState.checkGameOver() shouldBe Some("P2 won!")

      val p1alive = Player("P1", health = 50)
      val p2dead = Player("P2", health = 0)
      val gameOverState2 = new GameState(Map(), p1alive, p2dead)
      gameOverState2.checkGameOver() shouldBe Some("P1 won!")

      val drawState = new GameState(Map(), p1alive, p2alive)
      drawState.checkGameOver() shouldBe None
    }
  }
}