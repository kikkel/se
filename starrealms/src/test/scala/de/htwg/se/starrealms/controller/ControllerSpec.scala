package de.htwg.se.starrealms.controller

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec
import de.htwg.se.starrealms.model._
import de.htwg.util.Observer
import scala.collection.mutable.ListBuffer

class ControllerSpec extends AnyWordSpec with Matchers {

	class TestObserver extends Observer {
		var notified = false
		override def update: Unit = notified = true
	}

	"A Controller" should {
		val logic = new GameLogic(new Deck())
		val controller = new Controller(logic)
		val observer = new TestObserver()
		controller.addObserver(observer)

		"return correct state string" in {
			controller.getState should include ("Deck")
		}

		"execute Scout command" in {
			observer.notified = false
			val result = controller.processCommand("s")
			result should (include ("Scout") or include ("No Scout cards"))
			observer.notified should be (true)
		}

		"execute Viper command" in {
			observer.notified = false
			val result = controller.processCommand("v")
			result should (include ("Viper") or include ("No Viper cards"))
			observer.notified should be (true)
		}

		"execute Reset command" in {
			observer.notified = false
			val result = controller.processCommand("r")
			result should (include ("reset") or include ("reset"))
			observer.notified should be (true)
		}

		"handle invalid command" in {
			observer.notified = false
			val result = controller.processCommand("invalid")
			result should include ("Invalid")
			observer.notified should be (true)
		}
	}
}