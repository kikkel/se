package de.htwg.se.starrealms.app

import de.htwg.se.starrealms.controller.Controller
import de.htwg.se.starrealms.controller.CommandHandler
import de.htwg.se.starrealms.model._
import de.htwg.se.starrealms.view.{ConsoleView, GraphicUI}

import scalafx.application.JFXApp3
import scalafx.scene.Scene

object GameApp extends JFXApp3 {
  @volatile var running = true

  override def start(): Unit = {
    val decksByRole = LoadCards.loadFromResource(LoadCards.getCsvPath, "Core Set")
    if (decksByRole.isEmpty) {
      println("No decks found. Exiting the game.")
      return
    }
    println(s"\n\nDeck loaded: ${decksByRole.keys.mkString(", ")}\n\n")

    val player1 = Player("Player 1")
    val player2 = Player("Player 2")

    val gameState = new GameState(decksByRole, player1, player2)
    val controller = new Controller(gameState)
    val commandHandler = new CommandHandler(controller)

    val tui = new ConsoleView(commandHandler)
    val gui = new GraphicUI(commandHandler, () => sys.exit())

    // Beide UIs laufen parallel
    new Thread(() => tui.run()).start()
    gui.run()
  }
}