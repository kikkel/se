package de.htwg.se.starrealms.controller

import de.htwg.se.starrealms.model._
import de.htwg.util.Observer
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class CommandSpec extends AnyWordSpec with Matchers {

    "A DrawCardCommand" should {
      "return correct message when the right card is drawn" in {
        val deck = new Deck()
        val card = new DefaultCard(
          set = new Set { def nameOfSet = "Test" },
          cardName = "Scout",
          primaryAbility = None,
          faction = Faction("unaligned"),
          cardType = new Ship()
        )
        deck.setCards(List(card))
        val gameLogic = new GameLogic(deck)
        val command = new DrawCardCommand(gameLogic, "Scout")
        val result = command.execute()
        result should include("Scout")
      }

      " return wrong card message if drawn card doesn't match" in {
        val deck = new Deck()
        val card = new DefaultCard(
          set = new Set { def nameOfSet = "Test" },
          cardName = "Viper",
          primaryAbility = None,
          faction = Faction("unaligned"),
          cardType = new Ship()
        )
        deck.setCards(List(card))
        val gameLogic = new GameLogic(deck)
        val command = new DrawCardCommand(gameLogic, "Scout")
        val result = command.execute()
        result should include("card")
      }
  }
  "A ResetGameCommand" should {
    "reset the game and return confirmation" in {
      val gameLogic = new GameLogic(new Deck())
      val command = new ResetGameCommand(gameLogic)
      val result = command.execute()
      result should include("reset")
    }
  }

  "A ShowDeckCommand" should {
    "show deck state and return message" in {
      val controller = new Controller(new GameLogic(new Deck()))
      val command = new ShowDeckCommand(controller)
      val result = command.execute()
      result should include("Deck")
    }
  }
  "An InvalidCommand" should {
    "return error message with invalid input" in {
      val command = new InvalidCommand("sfj")
      val result = command.execute()
      result should include("Invalid command")
    }
  }
}

