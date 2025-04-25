package de.htwg.se.starrealms.model

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec
import java.io.{ByteArrayInputStream, ByteArrayOutputStream, PrintStream}

class TUISpec extends AnyWordSpec with Matchers {

  def withSimulatedIO(inputStr: String)(testBlock: => Unit): String = {
    val input = new ByteArrayInputStream(inputStr.getBytes)
    val output = new ByteArrayOutputStream()
    Console.withIn(input) {
      Console.withOut(new PrintStream(output)) {
        testBlock
      }
    }
    output.toString
  }

  "A TUI" should {
    "display the current state of the game" in {
      val output = withSimulatedIO("1\n4\n") {
        new TUI(new GameLogic(new PlayingField())).run()
      }
      output should include("Deck: Scout")
      output should include("Field: Empty")
    }

    "turn over a card after valid input" in {
      val output = withSimulatedIO("2\ns\n4\n") {
        new TUI(new GameLogic(new PlayingField())).run()
      }
      output should include("Turned over Scout")
    }

    "handle invalid card input then valid" in {
      val output = withSimulatedIO("2\nx\nv\n4\n") {
        new TUI(new GameLogic(new PlayingField())).run()
      }
      output should include("Invalid input")
      output should include("Turned over Viper")
    }

    "reset the game" in {
      val output = withSimulatedIO("2\ns\n3\n1\n4\n") {
        new TUI(new GameLogic(new PlayingField())).run()
      }
      output should include("Game has been reset.")
      output should include("Field: Empty")
    }

    "exit the game properly" in {
      val output = withSimulatedIO("4\n") {
        new TUI(new GameLogic(new PlayingField())).run()
      }
      output should include("Exiting the game. Goodbye!")
    }

    "handle invalid menu input" in {
      val output = withSimulatedIO("9\n4\n") {
        new TUI(new GameLogic(new PlayingField())).run()
      }
      output should include("Invalid choice. Please try again.")
    }
  }
}