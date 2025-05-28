package de.htwg.se.starrealms.app

import de.htwg.se.starrealms.controller.Controller
import de.htwg.se.starrealms.controller.CommandHandler
import de.htwg.se.starrealms.model._
import de.htwg.se.starrealms.view.{ConsoleView, GraphicUI}

object GameApp {
  @volatile var running = true

  def main(args: Array[String]): Unit = {
    val decksByRole = LoadCards.loadFromResource(LoadCards.getCsvPath, "Core Set")
    if (decksByRole.isEmpty) {
      println("No decks found. Exiting the game.")
      return
    }
    println(s"\n\nDeck loaded: ${decksByRole.keys.mkString(", ")}\n\n")

    val gameState = new GameState(decksByRole)
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

    // GUI im Hauptthread starten
    val gui = new GraphicUI(controller, () => running = false)
    gui.visible = true

    // Hauptthread warten lassen, solange das Fenster offen ist und running true ist
    while (gui.visible && running) {
      Thread.sleep(500)
    }
    // Fenster schließen, wenn running auf false gesetzt wurde (z.B. durch "x")
    gui.close()
  }
}