package de.htwg.se.starrealms.model

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec
import java.io.{ByteArrayInputStream, ByteArrayOutputStream, PrintStream}

class MainSpec extends AnyWordSpec with Matchers {

  "The Main object" should {
    "run the application without throwing an exception" in {
      // Simuliere Eingabe: direkt beenden (Option 4)
      val input = new ByteArrayInputStream("4\n".getBytes)
      val output = new ByteArrayOutputStream()

      // Standard Input und Output umleiten
      Console.withIn(input) {
        Console.withOut(new PrintStream(output)) {
          noException should be thrownBy {
            Main.main(Array.empty)
          }
        }
      }

      val result = output.toString
      result should include("Welcome to Star Realms - Textual UI!")
      result should include("Exiting the game. Goodbye!")
    }
  }
}