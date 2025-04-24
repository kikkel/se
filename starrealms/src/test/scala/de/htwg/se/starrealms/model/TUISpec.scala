package de.htwg.se.starrealms.model

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class TUISpec extends AnyWordSpec with Matchers {

  "A TUI" should {
    "display the current state of the game" in {
        val gameLogic = new GameLogic()
        val tui = new TUI(gameLogic)

        // Simulate user input for "1" (View current state) and "4" (Exit)
        val input = new ByteArrayInputStream("1\n4\n".getBytes)
        val output = new ByteArrayOutputStream()
        Console.setIn(input)
        Console.setOut(new PrintStream(output))

        tui.run()

        val outputString = output.toString
        outputString should include("Deck: Scout, Scout, Scout, Scout, Scout, Scout, Scout, Scout, Viper, Viper")
        outputString should include("Field: Empty")
    }

    "turn over a card and update the field" in {
        val gameLogic = new GameLogic()
        val tui = new TUI(gameLogic)

        // Simulate user input for "2" (Turn over a card) and "4" (Exit)
        val input = new ByteArrayInputStream("2\n4\n".getBytes)
        val output = new ByteArrayOutputStream()
        Console.setIn(input)
        Console.setOut(new PrintStream(output))

        tui.run()

        val outputString = output.toString
        outputString should include("Turned over card: Scout")
      outputString should include("Deck: Scout, Scout, Scout, Scout, Scout, Scout, Scout, Viper, Viper")
      outputString should include("Field: Scout")
    }

    "reset the game" in {
      val gameLogic = new GameLogic()
      val tui = new TUI(gameLogic)

      // Simulate user input for "2" (Turn over a card), "3" (Reset game), and "4" (Exit)
      val input = new ByteArrayInputStream("2\n3\n4\n".getBytes)
      val output = new ByteArrayOutputStream()
      Console.setIn(input)
      Console.setOut(new PrintStream(output))

      tui.run()

      val outputString = output.toString
      outputString should include("Turned over card: Scout")
      outputString should include("Game has been reset.")
      outputString should include("Deck: Scout, Scout, Scout, Scout, Scout, Scout, Scout, Scout, Viper, Viper")
      outputString should include("Field: Empty")
    }
  }
}