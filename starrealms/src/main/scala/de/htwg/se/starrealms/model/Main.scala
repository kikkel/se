package de.htwg.se.starrealms.model

object Main {
  def main(args: Array[String]): Unit = {
    val gameLogic = new GameLogic(new PlayingField()) // Create a new instance of GameLogic with a PlayingField
    val tui = new TUI(gameLogic) // Create a new TUI instance with the game logic

    tui.run() // Start the TUI
  }
}
