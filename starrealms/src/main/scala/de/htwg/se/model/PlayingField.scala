package de.htwg.se.starrealms.model

class PlayingField(val dimension: Int=20) { // Default dimension is 20

    val borderWidth = dimension * 3/2+3 // Top/Bottom border
    val unit = dimension / 8

    val tradeRowHeight = unit * 2  // 1/4 of the height
    val player1Height = unit * 3   // 1/4 + 1/8 (remaining quarter) of the height
    val player2Height = unit * 3   // 1/4 + 1/8 (remaining quarter) of the height

    val tradeRow = (0 until dimension).map(_ => " " * unit)

    val player1Row = (0 until dimension).map(_ => " " * unit)
    val player2Row = (0 until dimension).map(_ => " " * unit)

    val player1Turn = true
    val player2Turn = false

    def drawField(): Unit = {
        val border = "+" + ("-" * borderWidth) + "+" // Top/Bottom border
        println(border)

        for (row <- 0 until dimension) {
            if (row < player1Height) {
                println("|" + player1Row.mkString + "|") // Player 1 row
            } else if (row < player1Height + tradeRowHeight) {
                println("|" + tradeRow.mkString + "|") // Trade row
            } else {
                println("|" + player2Row.mkString + "|") // Player 2 row
            }
        }

        println(border) // Bottom border
    }



}