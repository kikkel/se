package de.htwg.se.starrealms.model

import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers

class PlayingFieldSpec extends AnyWordSpec with Matchers {

    "A PlayingField" should {

        "have a dimension in Parameter" in {
        val playingField = new PlayingField()
        val playingFieldUnit = playingField.dimension
        }

    // "have a scalable width proportion"

        "contain measuring units" in {
            val playingField = new PlayingField()
            val unit = playingField.dimension / 8
        }

        "consist of 3 horizontal partitions" in {
                val playingField = new PlayingField()
                val tradeSpaceHeight = playingField.unit * 2  // 1/4 of the height
                val player1Height = playingField.unit * 3   // 1/4 + 1/8 (remaining quarter) of the height
                val player2Height = playingField.unit * 3   // 1/4 + 1/8 (remaining quarter) of the height
        }

        "contain a player1 row" in {
            val playingField = new PlayingField()
            val player1Row = playingField.player1Row
        }

        "contain a player2 row" in {
            val playingField = new PlayingField()
            val player2Row = playingField.player2Row
        }

        "contain a trade row between the player rows" in {
            val playingField = new PlayingField()
            val tradeRow = playingField.tradeSpace
        }

        "should be adjustable according to player's turn" in {
            val playingField = new PlayingField()
            val player1Turn = true
            val player2Turn = false
        }
    }

}