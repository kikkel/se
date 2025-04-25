/* package de.htwg.se.starrealms.model

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec
import scala.Console.withOut


class TUISpec extends AnyWordSpec with Matchers {
/* 
  def io(inputStr: String)(testBlock: => Unit): String = {
    val input = new ByteArrayInputStream(inputStr.getBytes) // Simulate user input
    val output = new ByteArrayOutputStream() // Simulate output stream
    val originalIn = System.in
    val originalOut = System.out

    try {
      System.setIn(input) //redirect to avoid interfering with the test
      System.setOut(new PrintStream(output)) // Redirect to capture output
      testBlock
    } finally {
      System.setIn(originalIn) // Restore
      System.setOut(originalOut) // Restore
    }

    output.toString
  } */

  "A TUI" should {
    val gameLogic = new GameLogic(new PlayingField())
    val inputs = Iterator("1", "5") // Simulate user input
    val tui = new TUI(gameLogic, () => inputs.next()) // Pass the input source
    val input = System.in
    val output = System.out
    
    "display the current state of the game" in {
      val output = new StringBuilder
      Console.withOut(output) {
        tui.run()
      }
      val outputString = input.toString
      outputString should include("Current state:")
      outputString should include("Deck: Scout, Scout, Scout, Scout, Scout, Scout, Scout, Scout, Viper, Viper")
      outputString should include("Field: Empty")
    }

    "draw the playing field" in {
      val gameLogic = new GameLogic(new PlayingField())
      val inputs = Iterator("4", "5") // Simulate user input
      val tui = new TUI(gameLogic, () => inputs.next()) // Pass the input source


      val output = new StringBuilder
      Console.withOut(output) {
        tui.run()
      }


      val outputString = output.toString
      output should include("Drawing the playing field...")


    }

    "handle invalid card input then valid" in {
      val output = new StringBuilder
      Console.withOut(output) {
        tui.run()
      }
      output should include("Invalid input")
      output should include("Turned over Viper")
    }

    "handle invalid menu input" in {
      val output = new StringBuilder
      Console.withOut(output) {
        tui.run()
      }
      output should include("Invalid choice. Please try again.")
    }
  }
} */