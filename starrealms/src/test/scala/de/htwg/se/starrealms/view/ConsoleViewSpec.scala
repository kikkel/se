package de.htwg.se.starrealms.view

import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers
import de.htwg.se.starrealms.controller.Controller
import de.htwg.se.starrealms.model._
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
            val view = new ConsoleView(controller, outputBuffer += _)
            view.render()
            outputBuffer.exists(_.contains("Deck:")) shouldBe true
        }

        "process input correctly for game commands" in {
            val outputBuffer = new ListBuffer[String]()
            val controller = new MockController()
            val output = controller.getState
            val view = new ConsoleView(controller, outputBuffer += _)
            
            view.processInput("s") shouldBe true
            outputBuffer.exists(_.contains("Processed")) shouldBe true

            view.processInput("r") shouldBe true
            outputBuffer.exists(_.contains("Processed")) shouldBe true
        }

        "process input correctly for exit command" in {
            val outputBuffer = new ListBuffer[String]()
            val controller = new MockController()
            val output = controller.getState
            val view = new ConsoleView(controller, outputBuffer += _)
            
            view.processInput("x") shouldBe false
            outputBuffer.exists(_.contains("Exiting the game")) shouldBe true

        "update view when model notifies it" in {
            val outputBuffer = new ListBuffer[String]()
            val controller = new MockController()
            val output = controller.getState
            val view = new ConsoleView(controller, outputBuffer += _)

            view.update
            outputBuffer.exists(_.contains("Deck:")) shouldBe true
        }

        }
    }
}

