package de.htwg.se.starrealms.model

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec
import java.io.{ByteArrayInputStream, ByteArrayOutputStream, PrintStream}

class MainSpec extends AnyWordSpec with Matchers {

  "The Main object" should {
    "run the application without throwing an exception" in {
      // Simuliere Eingabe: direkt beenden (Option 5)
      val input = new ByteArrayInputStream("5\n".getBytes)
      val output = new ByteArrayOutputStream()

      // Standard Input und Output umleiten
      System.setIn(input)
      System.setOut(new PrintStream(output))
        noException should be thrownBy {
          Main.main(Array.empty)
        }



      val result = output.toString
    }
  }
}