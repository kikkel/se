/* package de.htwg.se.starrealms.app

import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers
import scala.collection.mutable.ListBuffer

class GameAppSpec extends AnyWordSpec with Matchers {

  "GameApp" should {
    "run full session with simulated input" in {
      val outputBuffer = ListBuffer[String]()
      val outputFunc: String => Unit = outputBuffer += _

      val inputs = Iterator("s", "v", "r", "x")
      val inputFunc = () => inputs.next()

      val app = new GameApp(inputFunc, outputFunc)
      app.run()

      val output = outputBuffer.mkString("\n")

      output should include("Welcome to Star Realms!")
      output should include("Scout") // if scout was drawn
      output should include("Viper") // if viper was drawn
      output should include("reset") // if game reset occurred
      output should include("Game exited") // clean shutdown
    }
  }
} */