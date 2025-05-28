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

    // TUI in separatem Thread starten
    new Thread(() => {
      val view = new ConsoleView(commandHandler)
      while (running) {
        println(s"\n\n${view.render()}\n\nYour command:\n\n")
        running = view.processInput(scala.io.StdIn.readLine())
      }
      println("\n\nGame exited. Goodbye! #main\n\n")
    }).start()

    val gui = new GraphicUI(controller, () => running = false)
    gui.show()
  }
}