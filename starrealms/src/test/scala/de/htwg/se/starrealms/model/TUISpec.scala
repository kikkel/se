package de.htwg.se.starrealms.model

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec
import java.io.{ByteArrayInputStream, ByteArrayOutputStream, PrintStream}

class TUISpec extends AnyWordSpec with Matchers {

  "A TUI" should {
    "display the current state of the game" in {
      val gameLogic = new GameLogic(new PlayingField())
      val tui = new TUI(gameLogic)

      val input = new ByteArrayInputStream("1\n4\n".getBytes)
      val output = new ByteArrayOutputStream()
      System.setIn(input)
      System.setOut(new PrintStream(output))

      tui.run()

      val outputString = output.toString
      outputString should include("Deck: Scout, Scout, Scout, Scout, Scout, Scout, Scout, Scout, Viper, Viper")
      outputString should include("Field: Empty")
    }

    "turn over a Scout card and update the field" in {
      val gameLogic = new GameLogic(new PlayingField())
      val tui = new TUI(gameLogic)

      // "2" (Turn over a card), "s" (Scout), "4" (Exit)
      val input = new ByteArrayInputStream("2\ns\n4\n".getBytes)
      val output = new ByteArrayOutputStream()
      System.setIn(input)
      System.setOut(new PrintStream(output))

      tui.run()

      val outputString = output.toString
      outputString should include("Turned over card: Scout")
      outputString should include("Field: Scout")
    }

    "turn over a Viper card and update the field" in {
      val gameLogic = new GameLogic(new PlayingField())
      val tui = new TUI(gameLogic)

      // Legen wir 8 Scouts manuell, damit nur noch Vipers im Deck sind:
      for (_ <- 1 to 8) gameLogic.turnOverCard("s")

      val input = new ByteArrayInputStream("2\nv\n4\n".getBytes)
      val output = new ByteArrayOutputStream()
      System.setIn(input)
      System.setOut(new PrintStream(output))

      tui.run()

      val outputString = output.toString
      outputString should include("Turned over card: Viper")
      outputString should include("Field: Scout, Scout, Scout, Scout, Scout, Scout, Scout, Scout, Viper")
    }

    "handle invalid card input gracefully" in {
      val gameLogic = new GameLogic(new PlayingField())
      val tui = new TUI(gameLogic)

      val input = new ByteArrayInputStream("2\nx\ns\n4\n".getBytes)
      val output = new ByteArrayOutputStream()
      System.setIn(input)
      System.setOut(new PrintStream(output))

      tui.run()

      val outputString = output.toString
      outputString should include("Invalid input. Please enter 's' for Scout or 'v' for Viper.")
      outputString should include("Turned over card: Scout")
    }

    "reset the game" in {
      val gameLogic = new GameLogic(new PlayingField())
      val tui = new TUI(gameLogic)

      val input = new ByteArrayInputStream("2\ns\n3\n4\n".getBytes)
      val output = new ByteArrayOutputStream()
      System.setIn(input)
      System.setOut(new PrintStream(output))

      tui.run()

      val outputString = output.toString
      outputString should include("Turned over card: Scout")
      outputString should include("Game has been reset.")
      outputString should include("Deck: Scout, Scout, Scout, Scout, Scout, Scout, Scout, Scout, Viper, Viper")
      outputString should include("Field: Empty")
    }
  }
}