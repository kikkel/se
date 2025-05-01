package de.htwg.se.starrealms.model


class PlayingField(var dimension: Int = 30) { // Default dimension is 30
    private val unit: Int = dimension / 8 // Unit size based on dimension

    // Generate a row of spaces based on the current dimension and unit size
    private def generateRow(): String = " " * (dimension * unit)

    // Calculate the width of the border based on the row length
    private def borderWidth: Int = generateRow().length

    // Draw the playing field with borders and rows
    def drawField(): Unit = {
        val border = "+" + ("-" * borderWidth) + "+" // Top/Bottom border
        println(border)

        for (_ <- 0 until dimension) {
            println("|" + generateRow() + "|") // Empty rows
        }

        println(border) // Bottom border
    }

    // Resize the playing field by updating the dimension
    def resize(newDimension: Int): Unit = {
        if (newDimension > 0) {
            dimension = newDimension
        } else {
            throw new IllegalArgumentException("Dimension must be greater than 0. #playingField")
        }
    }

    // Get the current dimensions of the playing field
    def dimensions: Int = dimension

    override def toString: String = s"PlayingField(dimension=$dimension) #playingField"
}