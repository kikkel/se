package de.htwg.se.starrealms.model

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec
import java.io.{ByteArrayInputStream, ByteArrayOutputStream, PrintStream}

class TUISpec extends AnyWordSpec with Matchers {

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
  }

  "A TUI" should {
    "display the current state of the game" in {
      val gameLogic = new GameLogic(new PlayingField())
      val tui = new TUI(gameLogic)

      val input = new ByteArrayInputStream("1\n5\n".getBytes) 
      val output = new ByteArrayOutputStream()
      System.setIn(input) 
      System.setOut(new PrintStream(output))

      tui.run()

      val outputString = output.toString
      outputString should include("Current state:")
      outputString should include("Deck: Scout, Scout, Scout, Scout, Scout, Scout, Scout, Scout, Viper, Viper")
      outputString should include("Field: Empty")
    }

    "draw the playing field" in {
      val gameLogic = new GameLogic(new PlayingField())
      val tui = new TUI(gameLogic)


      val output = io("4\n5\n") {
        tui.run()
      }

      output should include("Drawing the playing field...")
      output should include("Deck:")
      output should include("Field:")

      //val outputString = output.toString


    }

    "turn over a card after valid input" in {
      val output = io("2\ns\n4\n") {
        new TUI(new GameLogic(new PlayingField())).run()
      }
      output should include("Turned over Scout")
    }

    "handle invalid card input then valid" in {
      val output = io("2\nx\nv\n4\n") {
        new TUI(new GameLogic(new PlayingField())).run()
      }
      output should include("Invalid input")
      output should include("Turned over Viper")
    }

    "reset the game" in {
      val output = io("2\ns\n3\n1\n4\n") {
        new TUI(new GameLogic(new PlayingField())).run()
      }
      output should include("Game has been reset.")
      output should include("Field: Empty")
    }

    "exit the game properly" in {
      val output = io("4\n") {
        new TUI(new GameLogic(new PlayingField())).run()
      }
      output should include("Exiting the game. Goodbye!")
    }

    "handle invalid menu input" in {
      val output = io("9\n4\n") {
        new TUI(new GameLogic(new PlayingField())).run()
      }
      output should include("Invalid choice. Please try again.")
    }
  }
}