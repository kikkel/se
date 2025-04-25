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
      println("3. Reset game")
      println("4. Draw playing field")
      println("5. Exit")
      print("Enter your choice: ")

      val choice = readLine().trim
      choice match {
        case "1" =>
          println("\nCurrent state:")
          println(gameLogic.drawField())
        case "2" =>
          var valid = false
          while (!valid) {
            println("\nWhich card would you like to turn over? (s = Scout, v = Viper):")
            val input = readLine().trim.toLowerCase
            val result = gameLogic.turnOverCard(input)

            println(result)
            if (!result.startsWith("Invalid input")) {
              valid = true
            }
          }
        case "3" =>
            println("\nResetting the game...")
            gameLogic.resetGame()
            println("Game has been reset.")
        case "4" =>
          println("\nDrawing the playing field...")
          gameLogic.playingfield.drawField()
        case "5" =>
          println("\nExiting the game. Goodbye!")
          continue = false
        case _ =>
          println("\nInvalid choice. Please try again.")
      }
    }
  }
}