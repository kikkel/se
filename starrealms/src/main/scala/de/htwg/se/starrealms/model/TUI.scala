package de.htwg.se.starrealms.model


import scala.io.StdIn.readLine

class TUI(gameLogic: GameLogic, inputSource: () => String = scala.io.StdIn.readLine) {
  def run(): String = {
    var continue = true
    val output = new StringBuilder

    output.append("Welcome to Star Realms - Textual UI!")
    
    while (continue) {
      output.append("\nMenu:\n")
      output.append("1. View current state\n")
      output.append("2. Turn over a card\n")
      output.append("3. Reset game\n")
      output.append("4. Draw playing field\n")
      output.append("5. Exit\n")
      output.append("Enter your choice: ")

      val choice = inputSource()
      choice match {
        case "1" =>
          output.append("\nCurrent state:\n")
          output.append(gameLogic.drawField()).append("\n")
        case "2" =>
          var valid = false
          while (!valid) {
            output.append("\nWhich card would you like to turn over? (s = Scout, v = Viper):\n")
            val input = readLine().trim.toLowerCase
            val result = gameLogic.turnOverCard(input)

            output.append(result).append("\n")
            if (!result.startsWith("Invalid input")) {
              valid = true
            }
          }
        case "3" =>
            output.append("\nResetting the game...\n")
            gameLogic.resetGame()
            output.append("Game has been reset.\n")
        case "4" =>
          output.append("\nDrawing the playing field...\n")
          output.append(gameLogic.playingfield.drawField()).append("\n")
        case "5" =>
          output.append("\nExiting the game. Goodbye!\n")
          continue = false
        case _ =>
          output.append("\nInvalid choice. Please try again.\n")
      }
    }
    output.toString()
  }
}