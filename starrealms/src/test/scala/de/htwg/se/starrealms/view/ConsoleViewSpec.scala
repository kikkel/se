package de.htwg.se.starrealms.view

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec
import de.htwg.se.starrealms.controller.Controller
import de.htwg.se.starrealms.model.{GameLogic, PlayingField}

class ConsoleViewSpec extends AnyWordSpec with Matchers {
    "A ConsoleView" should {
        "render the game state when updated" in {
            val gameLogic = new GameLogic(new PlayingField())
            val controller = new Controller(gameLogic)
            val consoleView = new ConsoleView(controller)

            // Simulate a state change
            controller.processInput("s")
            noException should be thrownBy consoleView.render()
        }

        "process valid input correctly" in {
            val gameLogic = new GameLogic(new PlayingField())
            val controller = new Controller(gameLogic)
            val consoleView = new ConsoleView(controller)

            // Process valid input
            noException should be thrownBy consoleView.processInputLine("s")
            noException should be thrownBy consoleView.processInputLine("v")
            noException should be thrownBy consoleView.processInputLine("reset")
            noException should be thrownBy consoleView.processInputLine("exit")
        }

        "handle invalid input gracefully" in {
            val gameLogic = new GameLogic(new PlayingField())
            val controller = new Controller(gameLogic)
            val consoleView = new ConsoleView(controller)

            // Process invalid input
            noException should be thrownBy consoleView.processInputLine("invalid")
	}
  }
}

