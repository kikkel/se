package de.htwg.se.starrealms.controller

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec
import de.htwg.se.starrealms.model._
import de.htwg.util.Observer
import scala.util.Success

class ControllerSpec extends AnyWordSpec with Matchers {

  class TestObserver extends Observer {
    var notified = false
    override def update: Unit = notified = true
  }

  // Hilfswerte für Dummy-Objekte
  val edition = Edition("Core Set")
  val player1 = Player("Player 1")
  val player2 = Player("Player 2")
  val decksByRole = Map("Personal Deck" -> new Deck(), "Trade Deck" -> new Deck(), "Trade Row" -> new Deck())
  val gameState = new GameState(decksByRole, player1, player2)
  val gameLogic = new GameLogic(gameState)
  val controller = new Controller(gameLogic)
  val observer = new TestObserver()
  controller.addObserver(observer)

  def dummyCard(name: String = "Scout") = new DefaultCard(
    edition,
    name,
    None,
    Faction("Unaligned"),
    Success(new Ship()),
    1,
    "Personal Deck"
  )

  "A Controller" should {

    "return correct state string" in {
      // Passe den Test an den tatsächlichen Rückgabewert an!
      val state = controller.getState
      state should include ("Active Player")
      state should include ("Opponent")
      state should include ("Hand")
      state should include ("Discard Pile")
      state should include ("TradeRow")
    }

    "draw cards and notify observers" in {
      observer.notified = false
      controller.drawCards(2)
      observer.notified shouldBe true
    }

    "replenish trade row and notify observers" in {
      observer.notified = false
      controller.replenishTradeRow()
      observer.notified shouldBe true
    }

    "draw a card and notify observers" in {
      observer.notified = false
      controller.drawCard()
      observer.notified shouldBe true
    }

    "play a card and notify observers" in {
      observer.notified = false
      // Lege eine Karte auf die Hand, falls leer
      val card = dummyCard("Scout")
      gameState.setHand(player1, List(card))
      controller.playCard(card)
      observer.notified shouldBe true
    }

    "buy a card and notify observers" in {
      observer.notified = false
      // Lege eine Karte in die TradeRow, falls leer
      val card = dummyCard("Scout")
      gameState.setTradeRow(List(card))
      controller.buyCard(card)
      observer.notified shouldBe true
    }

    "end turn and notify observers" in {
      observer.notified = false
      controller.endTurn()
      observer.notified shouldBe true
    }

    "reset game and notify observers" in {
      observer.notified = false
      controller.resetGame()
      observer.notified shouldBe true
    }

    "undo and redo actions and notify observers" in {
      observer.notified = false
      controller.undo()
      observer.notified shouldBe true
      observer.notified = false
      controller.redo()
      observer.notified shouldBe true
    }
  }
}