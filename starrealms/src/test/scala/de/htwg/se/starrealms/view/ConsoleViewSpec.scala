package de.htwg.se.starrealms.view

import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers
import de.htwg.se.starrealms.controller.Controller
import de.htwg.se.starrealms.model.GameLogic
import de.htwg.se.starrealms.model.Deck
import scala.collection.mutable.ListBuffer

class ConsoleViewSpec extends AnyWordSpec with Matchers {
    class MockController extends Controller(new GameLogic(new Deck())) {
        override def getState: String = "Deck:"
        override def processCommand(cmd: String): String = s"Processed: $cmd"
    }

    "A ConsoleView" should {
        "render the game state when updated" in {
            val outputBuffer = new ListBuffer[String]()
            val controller = new MockController()
            val output = controller.getState
            val view = new ConsoleView(controller, output)
            view.render()
            outputBuffer should contain("Deck")
        }

        "process input correctly for game commands" in {
            val outputBuffer = new ListBuffer[String]()
            val controller = new MockController()
            val output = controller.getState
            val view = new ConsoleView(controller, output)
            
            view.processInput("s") shouldBe true
            outputBuffer should contain("Processed")

            view.processInput("r") shouldBe true
            outputBuffer should contain("Processed")
        }

        "process input correctly for exit command" in {
            val outputBuffer = new ListBuffer[String]()
            val controller = new MockController()
            val output = controller.getState
            val view = new ConsoleView(controller, output)
            
            view.processInput("x") shouldBe false
            outputBuffer should contain("Exiting the game...")
        }

        "update view when model notifies it" in {
            val outputBuffer = new ListBuffer[String]()
            val controller = new MockController()
            val output = controller.getState
            val view = new ConsoleView(controller, output)

            view.update
            outputBuffer should contain("Deck:")

        }
    }
}

