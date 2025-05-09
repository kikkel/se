package de.htwg.se.starrealms.app

import de.htwg.se.starrealms.controller._
import de.htwg.se.starrealms.model._
import de.htwg.se.starrealms.view.ConsoleView

object Main extends App {
  // Initialize the model, controller, and view
  val gameLogic = new GameLogic
  val deck = new DefaultDeck("DefaultDeck", "Default", List())
  val controller = new Controller(gameLogic, deck)
  val view = new ConsoleView(controller)


  // Application loop
  def run(inputProvider: () => String): Unit = {
    var continue = true
    while (continue) {
      view.render()
      println("Options:\n\t's' draw Scout\n\t'v' draw Viper\n\t'r' reset game\n\t'x' quit game\n\t #main")
      val input = inputProvider()
      continue = view.processInputLine(input)
    }
    println("\n\nGame exited. Goodbye! #main\n\n")
  }

  // Use StdIn for real input
  run(() => scala.io.StdIn.readLine())

}

