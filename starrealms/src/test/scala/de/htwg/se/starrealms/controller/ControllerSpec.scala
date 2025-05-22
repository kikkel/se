package de.htwg.se.starrealms.controller

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec
import de.htwg.se.starrealms.model._
import de.htwg.util.Observer

class ControllerSpec extends AnyWordSpec with Matchers {

  class TestObserver extends Observer {
    var notified = false
    override def update: Unit = notified = true
  }

  "A Controller" should {
    val controller = new Controller()
    val observer = new TestObserver()
    controller.addObserver(observer)

    "return correct state string" in {
      controller.getState should include ("Deck")
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
      val card = new DefaultCard(
        set = Set("Test"),
        cardName = "Scout",
        primaryAbility = None,
        faction = Faction("unaligned"),
        cardType = scala.util.Success(new Ship()),
        qty = 1,
        role = "Personal Deck"
      )
      controller.gameState.returnCardToHand(card)
      controller.playCard(card)
      observer.notified shouldBe true
    }

    "buy a card and notify observers" in {
      observer.notified = false
      // Lege eine Karte in die TradeRow, falls leer
      val card = new DefaultCard(
        set = Set("Test"),
        cardName = "Scout",
        primaryAbility = None,
        faction = Faction("unaligned"),
        cardType = scala.util.Success(new Ship()),
        qty = 1,
        role = "Personal Deck"
      )
      controller.gameState.returnCardToTradeRow(card)
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