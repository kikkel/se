package de.htwg.se.starrealms.view

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec
import de.htwg.se.starrealms.controller.Controller
import de.htwg.se.starrealms.model._

class ConsoleViewSpec extends AnyWordSpec with Matchers {
    "A ConsoleView" should {
        val gameLogic = new GameLogic(new PlayingField())
        val deck = new DefaultDeck("DefaultDeck", new CardType("Default"), List())
        val controller = new Controller(gameLogic, deck)
        val view = new ConsoleView(controller)

        "render the game state" in {
            noException should be thrownBy view.render()
        }

        "process valid input for drawing a Scout card" in {
            view.processInputLine("s") should be(true)
        }

        "process valid input for drawing a Viper card" in {
            view.processInputLine("v") should be(true)
        }

        "process input to reset the game" in {
            view.processInputLine("reset") should be(true)
        }

        "process input to exit the game" in {
            view.processInputLine("exit") should be(false)
        }

        "handle invalid input gracefully" in {
            view.processInputLine("invalid") should be(true)
        }
    }
}

/* package de.htwg.se.starrealms.view

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec
import de.htwg.se.starrealms.controller.Controller
import de.htwg.se.starrealms.model._

class ConsoleViewSpec extends AnyWordSpec with Matchers {
    "A ConsoleView" should {
        "render the game state when updated" in {
            val gameLogic = new GameLogic(new PlayingField())
    	    val deck = new DefaultDeck()
	        val controller = new Controller(gameLogic, deck)
            val consoleView = new ConsoleView(controller)

            // Simulate a state change
            controller.processInput("s")
            noException should be thrownBy consoleView.render()
        }

        "process valid input correctly" in {
            val gameLogic = new GameLogic(new PlayingField())
    	    val deck = new DefaultDeck()
	        val controller = new Controller(gameLogic, deck)
            val consoleView = new ConsoleView(controller)

            // Process valid input
            noException should be thrownBy consoleView.processInputLine("s")
            noException should be thrownBy consoleView.processInputLine("v")
            noException should be thrownBy consoleView.processInputLine("reset")
            noException should be thrownBy consoleView.processInputLine("exit")
        }

        "handle invalid input gracefully" in {
            val gameLogic = new GameLogic(new PlayingField())
    	    val deck = new DefaultDeck()
	        val controller = new Controller(gameLogic, deck)
            val consoleView = new ConsoleView(controller)

            // Process invalid input
            noException should be thrownBy consoleView.processInputLine("invalid")
	}
  }
}

 */