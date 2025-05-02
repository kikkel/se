package de.htwg.se.starrealms.app

import de.htwg.se.starrealms.controller._
import de.htwg.se.starrealms.model._
import de.htwg.se.starrealms.view.ConsoleView

object Main extends App {
  // Initialize the model, controller, and view
  val gameLogic = new GameLogic
  val deck = new DefaultDeck("DefaultDeck", new CardType("Default"), List())
  val controller = new Controller(gameLogic, deck)
  val view = new ConsoleView(controller)




  // Application loop
  var continue = true
  while (continue) {
    view.render() //current game state
    println("Options\n\ts: draw Scout\n\tv: draw Viper\n\treset: reset game\n\texit: quit): #main")
    val input = scala.io.StdIn.readLine()
    continue = view.processInputLine(input)
  }

  println("\n\nGame exited. Goodbye! #main\n\n")
}

