package de.htwg.se.starrealms.model
class PlayingField {
    val pfUnit = 10 //playing field unit
    val rows = 2 * pfUnit
    val columns = 3 * pfUnit
    val borderWidth = columns * 3/2+3 // Top/Bottom border



    val gapWidth = (columns / 25).toInt
    val rectWidth = gapWidth * 2 //ratio 2:3
    val rectHeight = Math.min((gapWidth * 3), (rows / 5)) // Ensure it doesn't exceed top third

    val numRecs = 8
    val numGaps = numRecs - 1
    val totalGapWidth = columns - (numRecs * rectWidth)
    val cardRowWidth = numRecs * rectWidth + numGaps * gapWidth
    
    val sideGapwidth = 0 //(columns - cardRowWidth) / 2
    val gapHeight = rows / 5

    val topThird = rows / 3
    val centeredRow = Math.max((topThird - rectHeight) / 2, 0) // Ensure non-negative start
    //--------------------------------------------------------------------------------------------------------------------------------------//
    def drawField(): Unit = {
        val border = "+" + ("-" * borderWidth) + "+" // Top/Bottom border //!!! *3/2+3 ?? verallgemeinern
        println(border)

        for (row <- 0 until rows) {
          if (row >= centeredRow && row < centeredRow + rectHeight) {
              // First few rows contain cards (height of each card)
              print("|") // Left border
              print((" " * sideGapwidth))

              for (_ <- 0 until numRecs) {
                if (row == centeredRow) {   //top of card
                  print(" " * gapWidth + "+" + "-" * (rectWidth) + "+" + " " * gapWidth)
                } else if (row == centeredRow + rectHeight-1) {     //bottom of card
                  print(" " * gapWidth + "+" + "-" * (rectWidth) + "+" + " " * gapWidth)
                } else {    //middle of card
                  print(" " * gapWidth + "|" + " " * (rectWidth) + "|" + " " * gapWidth)
                }
              }

              print(" " * sideGapwidth)
              println( "|") // Right border
          } else {
              // Lower two-thirds remain empty
              println("|" + " " * (borderWidth) + "|") //!!!!! +18 ?? verallgemeinern
          }
        }

    println(border) // Bottom border
    }
  }
