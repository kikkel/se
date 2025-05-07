/*package de.htwg.se.starrealms.model

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec
//import de.htwg.se.starrealms.model._

class GameLogicSpec extends AnyWordSpec with Matchers {

  "GameLogic" should {
    "initialize with an options menu and a deck" in {
      val gameLogic = new GameLogic
      gameLogic.optionsMenu() should include("Deck:")
    }

    "turn over a Scout card" in {
      val gameLogic = new GameLogic
      val result = gameLogic.turnOverCard("s")
      result should include("Turned over Scout")
    }

    "turn over a Viper card" in {
      val gameLogic = new GameLogic
      val result = gameLogic.turnOverCard("v")
      result should include("Turned over Viper")
    }

    "handle invalid input" in {
      val gameLogic = new GameLogic
      val result = gameLogic.turnOverCard("invalid")
      result should include("Invalid input")
    }

    "reset the game" in {
      val gameLogic = new GameLogic
      gameLogic.turnOverCard("s")
      gameLogic.resetGame()
      gameLogic.optionsMenu should include("Deck:")
    }
  }
}*/
package de.htwg.se.starrealms.model

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec
import java.io.{ByteArrayOutputStream, PrintStream}

class GameLogicSpec extends AnyWordSpec with Matchers {

  "GameLogic" should {

    "print the options menu" in {
      val gameLogic = new GameLogic
      val output = new ByteArrayOutputStream()
      Console.withOut(new PrintStream(output)) {
        println(gameLogic.optionsMenu())
      }
      val printedOutput = output.toString
      printedOutput should include("Deck:")
      printedOutput should include("#gameLogic")
    }

    "print the result of turning over a Scout card" in {
      val gameLogic = new GameLogic
      val output = new ByteArrayOutputStream()
      Console.withOut(new PrintStream(output)) {
        println(gameLogic.turnOverCard("s"))
      }
      val printedOutput = output.toString
      printedOutput should include("Turned over Scout")
      printedOutput should include("#gameLogic")
    }

    "print the result of turning over a Viper card" in {
      val gameLogic = new GameLogic
      val output = new ByteArrayOutputStream()
      Console.withOut(new PrintStream(output)) {
        println(gameLogic.turnOverCard("v"))
      }
      val printedOutput = output.toString
      printedOutput should include("Turned over Viper")
      printedOutput should include("#gameLogic")
    }

    "print a message when the deck is empty" in {
      val gameLogic = new GameLogic
      // Leere das Deck
      while (gameLogic.turnOverCard("s").contains("Turned over Scout")) {}
      val output = new ByteArrayOutputStream()
      Console.withOut(new PrintStream(output)) {
        println(gameLogic.turnOverCard("s"))
      }
      val printedOutput = output.toString
      printedOutput should include("No Scout cards left in the deck")
      printedOutput should include("#gameLogic")
    }
    "print a message when no Viper cards are left in the deck" in {
      val gameLogic = new GameLogic
      // Leere das Deck von Viper-Karten
      while (gameLogic.turnOverCard("v").contains("Turned over Viper")) {}
      val output = new ByteArrayOutputStream()
      Console.withOut(new PrintStream(output)) {
        println(gameLogic.turnOverCard("v"))
      }
      val printedOutput = output.toString
      printedOutput should include("No Viper cards left in the deck")
      printedOutput should include("#gameLogic")
    }

    "print a message for invalid input" in {
      val gameLogic = new GameLogic
      val output = new ByteArrayOutputStream()
      Console.withOut(new PrintStream(output)) {
        println(gameLogic.turnOverCard("invalid"))
      }
      val printedOutput = output.toString
      printedOutput should include("Invalid input")
      printedOutput should include("#gameLogic")
    }

    "print a message when the game is reset" in {
      val gameLogic = new GameLogic
      val output = new ByteArrayOutputStream()
      Console.withOut(new PrintStream(output)) {
        gameLogic.resetGame()
      }
      val printedOutput = output.toString
      printedOutput shouldBe empty // resetGame gibt keine Ausgabe aus
    }

    "print a message when exiting the game" in {
      val gameLogic = new GameLogic
      val output = new ByteArrayOutputStream()
      Console.withOut(new PrintStream(output)) {
        gameLogic.exitGame()
      }
      val printedOutput = output.toString
      printedOutput should include("Exiting the game")
      printedOutput should include("#gameLogic")
    }
  }
}
