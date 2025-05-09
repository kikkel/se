package de.htwg.se.starrealms.app

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec
import de.htwg.se.starrealms.controller.Controller
import de.htwg.se.starrealms.model._
import de.htwg.se.starrealms.view.ConsoleView

class MainSpec extends AnyWordSpec with Matchers {
  "Main" should {
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