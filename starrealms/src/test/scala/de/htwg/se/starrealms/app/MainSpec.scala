package de.htwg.se.starrealms.app

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec
import de.htwg.util
import de.htwg.se.starrealms.controller.Controller
import de.htwg.se.starrealms.model._
import de.htwg.se.starrealms.view.ConsoleView

/* import java.io.{ByteArrayInputStream, ByteArrayOutputStream, PrintStream}
 */
class MainSpec extends AnyWordSpec with Matchers {

  /* "Main object" should {
    "initialize the model, controller and view without errors" in {
      noException should be thrownBy {

        //simulate initialized process
        val gameLogic = new GameLogic
    	  val deck = new DefaultDeck("DefaultDeck", "Default", List())
        // Create a new instance of DefaultDeck with a CardType and an empty list
        // Create a new instance of Controller with the gameLogic and deck
        // Create a new instance of ConsoleView with the controller
	      val controller = new Controller(gameLogic, deck)
        val view = new ConsoleView(controller)

      }
    }

    /* "run the application without throwing an exception" in {
      noException should be thrownBy {
        //simulate running the application
        Main.main(Array.empty)
      }
    }*/
  } */

}