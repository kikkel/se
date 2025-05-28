package de.htwg.se.starrealms.controller

import de.htwg.se.starrealms.model._
import de.htwg.util.Observer
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec
import scala.util.{Try, Success, Failure}

class CommandSpec extends AnyWordSpec with Matchers {

  "A DrawCardCommand" should {
     "store the drawn card on doMove"  in {
      val controller = new Controller()
      val card = new DefaultCard(
        set = Set("Core Set"),
        cardName = "Viper",
        primaryAbility = Some(new Ability(List(SimpleAction("Gain 1 Trade")))),
        faction = Faction("Unaligned"),
        cardType = Success(new Ship()),
        qty = 1,
        role = "Personal Deck"
      )
      controller.gameState.getHand.foreach(controller.gameState.playCard)
      controller.gameState.returnCardToPlayerDeck(card)
      val command = new DrawCardCommand(controller)
      command.doMove
    }
    "return the drawn card on undoMove and clear it" in {
      val controller = new Controller()
      val card = new DefaultCard(
        set = Set("Core Set"),
        cardName = "Viper",
        primaryAbility = Some(new Ability(List(SimpleAction("Gain 1 Trade")))),
        faction = Faction("Unaligned"),
        cardType = Success(new Ship()),
        qty = 1,
        role = "Personal Deck"
      )
      controller.gameState.getHand.foreach(controller.gameState.playCard)
      controller.gameState.returnCardToPlayerDeck(card)
      val command = new DrawCardCommand(controller)
      command.doMove
      command.undoMove
    }
    "not fail when undoMove is called without a drawn card" in {
      val controller = new Controller()
      val command = new DrawCardCommand(controller)
      command.undoMove // should be a no-op

    }
  }

  "A ResetGameCommand" should {
    "reset the game and allow undo" in {
      val controller = new Controller()
      val command = new ResetGameCommand(controller)
      noException should be thrownBy command.doMove
      noException should be thrownBy command.undoMove
    }
  }

  "A ShowDeckCommand" should {
    "print deck state" in {
      val controller = new Controller()
      val command = new ShowDeckCommand(controller)
      noException should be thrownBy command.doMove
      command.undoMove // should do nothing
    }
  }

  "An InvalidCommand" should {
    "print error message" in {
      val command = new InvalidCommand("sfj")
      noException should be thrownBy command.doMove
      command.undoMove // should do nothing
    }
  }

  "A PlayCardCommand" should {
    "play and undo a card" in {
      val controller = new Controller()
      val card = new DefaultCard(
        set = Set("Core Set"),
        cardName = "Scout",
        primaryAbility = Some(new Ability(List(SimpleAction("Gain 1 Trade")))),
        faction = Faction("Unaligned"),
        cardType = Success(new Ship()),
        qty = 1,
        role = "Personal Deck"
      )
      // Hand leeren
      controller.gameState.getHand.foreach(controller.gameState.playCard)
      // Karte auf die Hand legen
      controller.gameState.returnCardToHand(card)
      val command = new PlayCardCommand(controller, card)
      command.doMove
      controller.gameState.getHand should not contain card
      command.undoMove
      controller.gameState.getHand should contain(card)
    }
  }

  "A BuyCardCommand" should {
    "buy and undo a card" in {
      val controller = new Controller()
      val card = new DefaultCard(
        set = Set("Core Set"),
        cardName = "Scout",
        primaryAbility = Some(new Ability(List(SimpleAction("Gain 1 Trade")))),
        faction = Faction("Unaligned"),
        cardType = Success(new Ship()),
        qty = 1,
        role = "Personal Deck"
      )
      // TradeRow leeren
      controller.gameState.getTradeRow.foreach(controller.gameState.buyCard)
      // Karte in die TradeRow legen
      controller.gameState.returnCardToTradeRow(card)
      val command = new BuyCardCommand(controller, card)
      command.doMove
      controller.gameState.getTradeRow should not contain card
      command.undoMove
      controller.gameState.getTradeRow should contain(card)
    }
  }

  "An EndTurnCommand" should {
    "end and undo turn" in {
      val controller = new Controller()
      // Hand leeren
      controller.gameState.getHand.foreach(controller.gameState.playCard)
      val command = new EndTurnCommand(controller)
      noException should be thrownBy command.doMove
      noException should be thrownBy command.undoMove
    }
  }

  "A DrawCardsCommand" should {
    "draw multiple cards and undo" in {
      val controller = new Controller()
      val card = new DefaultCard(
        set = Set("Core Set"),
        cardName = "Scout",
        primaryAbility = Some(new Ability(List(SimpleAction("Gain 1 Trade")))),
        faction = Faction("Unaligned"),
        cardType = Success(new Ship()),
        qty = 1,
        role = "Personal Deck"
      )
      // Hand leeren
      controller.gameState.getHand.foreach(controller.gameState.playCard)
      // Deck mit Karten füllen
      (1 to 3).foreach(_ => controller.gameState.returnCardToPlayerDeck(card))
      val command = new DrawCardsCommand(controller, 2)
      command.doMove
      controller.gameState.getHand.size shouldBe 2
      command.undoMove
      controller.gameState.getHand.size shouldBe 0
    }
  }

  "A ReplenishTradeRowCommand" should {
    "call replenishTradeRow on doMove" in {
      val controller = new Controller()
      val command = new ReplenishTradeRowCommand(controller)
      val card = new FactionCard(
        set = Set("Core Set"),
        cardName = "Card A",
        cost = 1,
        primaryAbility = Some(new Ability(List(SimpleAction("Gain 1 Trade")))),
        allyAbility = None,
        scrapAbility = None,
        faction = Faction("Unaligned"),
        cardType = Success(new Ship()),
        qty = 1,
        role = "Trade Row"
      )

      command.doMove

    }
    "call undoReplenish for each card on undoMove" in {
      val controller = new Controller()
      val command = new ReplenishTradeRowCommand(controller)
      val card1 = new FactionCard(
        set = Set("Core Set"),
        cardName = "Card A",
        cost = 1,
        primaryAbility = Some(new Ability(List(SimpleAction("Gain 1 Trade")))),
        allyAbility = None,
        scrapAbility = None,
        faction = Faction("Unaligned"),
        cardType = Success(new Ship()),
        qty = 1,
        role = "Trade Row"
      )
      val card2 = new FactionCard(
        set = Set("Core Set"),
        cardName = "Card B",
        cost = 2,
        primaryAbility = Some(new Ability(List(SimpleAction("Gain 2 Trade")))),
        allyAbility = None,
        scrapAbility = None,
        faction = Faction("Unaligned"),
        cardType = Success(new Ship()),
        qty = 1,
        role = "Trade Row"
      )

      command.setRep(List(card1, card2))
      command.undoMove
    }

    "call replenishTradeRow on redoMove" in {
      val controller = new Controller()
      val command = new ReplenishTradeRowCommand(controller)
      val card = new FactionCard(
        set = Set("Core Set"),
        cardName = "Card A",
        cost = 1,
        primaryAbility = Some(new Ability(List(SimpleAction("Gain 1 Trade")))),
        allyAbility = None,
        scrapAbility = None,
        faction = Faction("Unaligned"),
        cardType = Success(new Ship()),
        qty = 1,
        role = "Trade Row"
      )

      command.doMove
      command.redoMove
    }
  }
"undoMove" should {

    "return a message when there are no moves to undo" in {
      val manager = new UndoManager
      manager.getUndoStack shouldBe empty

      val result = manager.undoMove

      result shouldBe "No moves to undo #Command"
      manager.getRedoStack shouldBe empty
    }
  }

  "redoMove" should {

    "return a message when there are no moves to redo" in {
      val manager = new UndoManager
      manager.getRedoStack shouldBe empty

      val result = manager.redoMove

      result shouldBe "No moves to redo #Command"
      manager.getUndoStack shouldBe empty
    }
  }

}