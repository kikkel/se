package de.htwg.se.starrealms.app

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class MainSpec extends AnyWordSpec with Matchers {
  "Main" should {
    "run the main method without exceptions" in {
      noException should be thrownBy Main.main(Array.empty)
    }
  }
}