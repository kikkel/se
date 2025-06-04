package de.htwg.se.starrealms.app

import de.htwg.se.starrealms.controller.ControllerComponent.{Controller, GameLogic, GameStateProxy, GameStateReadOnly}
import de.htwg.se.starrealms.model.{GameState, LoadCards, Player}
import de.htwg.se.starrealms.view.{ConsoleView, GraphicUI, CommandProcessorAdapter}

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
    val gameLogic = new GameLogic(gameState)
    val controller = new Controller(gameLogic)
    val commandAdapter = new CommandProcessorAdapter(controller)
    val proxy: GameStateReadOnly = new GameStateProxy(gameState)
    val view = new ConsoleView(commandAdapter, proxy, gameLogic)
    val gui = new GraphicUI(commandAdapter, proxy, () => running = false)

    // TUI in separatem Thread starten
    new Thread(() => {
      while (running) {
        println(s"\n\n${view.render()}\n\nYour command:\n\n")
        running = view.processInput(scala.io.StdIn.readLine())
      }
      println("\n\nGame exited. Goodbye! #main\n\n")
      System.exit(0)
    }).start()


    gui.show()

    controller.gameLogic.gameState.addObserver(gui)
    controller.gameLogic.gameState.addObserver(view)

  }
}