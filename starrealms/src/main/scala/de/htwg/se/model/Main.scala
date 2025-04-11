package de.htwg.se.starrealms.model

object Main {
  def main(args: Array[String]): Unit = {
    val playingField = new PlayingField(20) // Adjust the dimension as needed
    playingField.drawField()
  }
}