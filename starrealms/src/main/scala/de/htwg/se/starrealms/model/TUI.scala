package de.htwg.se.starrealms.model


import scala.io.StdIn.readLine

class TUI (gameLogic: GameLogic) {
  def run(): Unit = {
    var continue = true

    println("Welcome to Star Realms - Textual UI!")
    while (continue) {
      println("\nMenu:")
      println("1. View current state")
      println("2. Turn over a card")
      println("3. Exit")
      print("Enter your choice: ")

      val choice = readLine()
      choice match {
        case "1" =>
          println("\nCurrent state:")
          println(gameLogic.drawField())
        case "2" =>
          println("\nTurning over a card...")
          println(gameLogic.turnOverCard())
        case "3" =>
            println("\nResetting the game...")
            gameLogic.resetGame()
            println("Game has been reset.")
        case "4" =>
          println("\nExiting the game. Goodbye!")
          continue = false
        case _ =>
          println("\nInvalid choice. Please try again.")
      }
    }
  }
}