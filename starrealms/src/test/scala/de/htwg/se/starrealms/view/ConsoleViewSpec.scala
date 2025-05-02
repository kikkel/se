package de.htwg.se.starrealms.view

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec
import de.htwg.se.starrealms.controller.Controller
import de.htwg.se.starrealms.model._

class ConsoleViewSpec extends AnyWordSpec with Matchers {
    "A ConsoleView" should {
        val gameLogic = new GameLogic
        val deck = new DefaultDeck("DefaultDeck", new CardType("Default"), List())
        val controller = new Controller(gameLogic, deck)
        val view = new ConsoleView(controller)

        //ensure components are properly connected
        controller.addObserver(view)

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
        "update when notified by the controller" in {
            // Simuliere eine Benachrichtigung durch den Controller
            controller.addObserver(view)
            noException should be thrownBy controller.notifyObservers()
        }
    }
}

