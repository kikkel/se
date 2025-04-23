package de.htwg.se.starrealms.model

object Main {
  def main(args: Array[String]): Unit = {
    val playingField = new PlayingField() 
    playingField.drawField()
  }
}