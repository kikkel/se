package de.htwg.se.starrealms.app

import de.htwg.se.starrealms.controller._
import de.htwg.se.starrealms.model._
import de.htwg.se.starrealms.view.ConsoleView

class GameApp(inputProvider: () => String, output: String => Unit = println) {
  def run(): Unit = {
    val decksByRole = LoadCards.loadFromResource(LoadCards.getCsvPath, "Core Set")
    if (decksByRole.isEmpty) {
      output("No decks found. Exiting the game.")
      return
    }
    println(s"\n\nDeck loaded: ${decksByRole.keys.mkString(", ")}\n\n")

    val gameState = new GameState(decksByRole)
    val controller = new Controller(gameState)
    val commandHandler = new CommandHandler(controller)
    val view = new ConsoleView(commandHandler)

    var running = true
    while (running) {
      output(s"\n\n${view.render()}\n\nYour command:\n\n")
      running = view.processInput(inputProvider())
    }

    output("\n\nGame exited. Goodbye! #main\n\n")
  }
}


