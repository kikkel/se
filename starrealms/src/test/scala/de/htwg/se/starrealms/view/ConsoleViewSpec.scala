package de.htwg.se.starrealms.view

import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers
import de.htwg.se.starrealms.controller.CommandProcessor

class ConsoleViewSpec extends AnyWordSpec with Matchers {

  class MockProcessor extends CommandProcessor {
    var lastCommand: String = ""
    override def processCommand(input: String): String = {
      lastCommand = input
      s"Processed: $input"
    }
  }

  "A ConsoleView" should {
    "render the game state when updated" in {
      val processor = new MockProcessor
      val view = new ConsoleView(processor)
      noException should be thrownBy view.render()
    }

    "process input correctly for game commands (not in play phase)" in {
      val processor = new MockProcessor
      val view = new ConsoleView(processor)

      view.processInput("s") shouldBe true
      processor.lastCommand shouldBe "s"

      view.processInput("t") shouldBe true
      processor.lastCommand shouldBe "t"

      view.processInput("z") shouldBe true
      processor.lastCommand shouldBe "z"

      view.processInput("y") shouldBe true
      processor.lastCommand shouldBe "y"

      view.processInput("x") shouldBe false

      view.processInput("unknown") shouldBe true
    }

    "process input correctly for game commands (in play phase)" in {
      val processor = new MockProcessor
      val view = new ConsoleView(processor)
      // Setze Playphase
      val playPhaseField = view.getClass.getDeclaredField("inPlayPhase")
      playPhaseField.setAccessible(true)
      playPhaseField.set(view, true)

      view.processInput("x") shouldBe false

      playPhaseField.set(view, true)
      view.processInput("e") shouldBe true
      processor.lastCommand shouldBe "e"
      playPhaseField.set(view, true)
      view.processInput("z") shouldBe true
      processor.lastCommand shouldBe "z"
      playPhaseField.set(view, true)
      view.processInput("y") shouldBe true
      processor.lastCommand shouldBe "y"
      playPhaseField.set(view, true)
      view.processInput("p 2") shouldBe true
      processor.lastCommand shouldBe "p 2"
      playPhaseField.set(view, true)
      view.processInput("b 3") shouldBe true
      processor.lastCommand shouldBe "b 3"
      playPhaseField.set(view, true)
      view.processInput("1") shouldBe true
      processor.lastCommand shouldBe "p 1"
      playPhaseField.set(view, true)
      view.processInput("foo") shouldBe true
    }

    "update view when model notifies it" in {
      val processor = new MockProcessor
      val view = new ConsoleView(processor)
      noException should be thrownBy view.update
    }
  }
}