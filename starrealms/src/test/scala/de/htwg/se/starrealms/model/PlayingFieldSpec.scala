package de.htwg.se.starrealms.model

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec
import java.io.{ByteArrayOutputStream, PrintStream}

class PlayingFieldSpec extends AnyWordSpec with Matchers {

  "A PlayingField" should {


    "initialize with a default dimension" in { 
      val playingField = new PlayingField()
      playingField.dimensions should be(30)
    }

    "initialize with a custom dimension" in {
      val playingField = new PlayingField(20) 
      playingField.dimensions should be(20)
    }

    "resize to a new valid dimension" in {
      val playingField = new PlayingField(30)
      playingField.resize(50)
      playingField.dimensions should be(50)
    }

    "throw an exception when resizing to an invalid dimension" in {
      val playingField = new PlayingField(30)
      an[IllegalArgumentException] should be thrownBy playingField.resize(-10)
    }

    "return the current dimensions" in {
      val playingField = new PlayingField(40)
      playingField.dimensions should be(40)
    }

    "draw the playing field with borders and rows" in {
      val dimension = 5
      val playingField = new PlayingField(dimension)
      val output = new ByteArrayOutputStream()
      Console.withOut(new PrintStream(output)) {
        playingField.drawField()
      }
      val outputString = output.toString
    
      // Dynamically calculate the expected border and rows
      val unit = dimension / 8
      val rowWidth = dimension * unit
      val border = "+" + ("-" * rowWidth) + "+"
      val row = "|" + (" " * rowWidth) + "|"
    
      // Build the expected output
      val expectedOutput = (border + "\n") +
        (row + "\n").repeat(dimension) +
        (border + "\n")
    
      outputString should be(expectedOutput)
    }

    "return a string representation of the playing field" in {
      val playingField = new PlayingField(25)
      playingField.toString should be("PlayingField(dimension=25)")
    }
  }
}