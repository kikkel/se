package de.htwg.se.starrealms.controller

import de.htwg.se.starrealms.model._
import de.htwg.util.Observer
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec
import scala.util.Success

class CommandSpec extends AnyWordSpec with Matchers {

  "A DrawCardCommand" should {
    "draw a card and allow undo" in {
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
      controller.gameState.getHand.map(_.cardName) should contain ("Scout")
      command.undoMove
      controller.gameState.getHand.map(_.cardName) should not contain ("Scout")
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
    "replenish and undo" in {
      val controller = new Controller()
      val command = new ReplenishTradeRowCommand(controller)
      noException should be thrownBy command.doMove
      noException should be thrownBy command.undoMove
    }
  }
}