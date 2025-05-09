package de.htwg.se.starrealms.app

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec
import de.htwg.se.starrealms.controller.Controller
import de.htwg.se.starrealms.model._
import de.htwg.se.starrealms.view.ConsoleView

class MainSpec extends AnyWordSpec with Matchers {
  "Main" should {
	"run the game loop and exit on 'x'" in {
	  val gameLogic = new GameLogic
	  val deck = new DefaultDeck("DefaultDeck", "Default", List())
	  val controller = new Controller(gameLogic, deck)
	  val view = new ConsoleView(controller)

	  var inputs = List("s", "v", "r", "x").iterator
	  def mockInputProvider(): String = inputs.next()

	  // Run the game loop with mock input
	  Main.view = mock[ConsoleView]
    when(Main.view.render()).thenReturn(())
    
    Main.run(mockInputProvider)

	  // Assertions can be added here if needed
	  // For example, check the state of the controller or view
	}
  }
}