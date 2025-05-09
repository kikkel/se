package de.htwg.se.starrealms.app

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec
import de.htwg.se.starrealms.controller.Controller
import de.htwg.se.starrealms.model._
import de.htwg.se.starrealms.view.ConsoleView
import java.io.{ByteArrayOutputStream, PrintStream}

class MainSpec extends AnyWordSpec with Matchers {
  "Main" should {

    "print 'Welcome to Star Realms!' on startup" in {
      val outStream = new ByteArrayOutputStream()
      Console.withOut(new PrintStream(outStream)) {
        Main.main(Array.empty)
      }
      outStream.toString.trim should include("Welcome to Star Realms!")
    }
		"contain a run method" in {
		val gameLogic = new GameLogic
		val deck = new DefaultDeck("DefaultDeck", "Default", List())
		val controller = new Controller(gameLogic, deck)
		val view = new ConsoleView(controller)

		// Test the run method
		def testInputProvider(): String = "x"
		view.render()
		view.processInputLine(testInputProvider())
		}
	}
}