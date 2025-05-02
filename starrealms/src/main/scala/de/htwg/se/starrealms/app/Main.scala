package de.htwg.se.starrealms.app

import de.htwg.se.starrealms.controller._
import de.htwg.se.starrealms.model._
import de.htwg.se.starrealms.view.ConsoleView

object Main extends App {
  // Initialize the model, controller, and view
  val gameLogic = new GameLogic(new PlayingField())
  val deck = new DefaultDeck("DefaultDeck", new CardType("Default"), List())
  val controller = new Controller(gameLogic, deck)
  val view = new ConsoleView(controller)

  // Connect view to model
  gameLogic.addObserver(view) 

  
  // Application loop
  var continue = true
  while (continue) {
    view.render() //current game state
    println("Enter command\n\ts: draw Scout\n\tv: draw Viper\n\treset: reset game\n\texit: quit): #main")
    val input = scala.io.StdIn.readLine()
    continue = view.processInputLine(input)
  }

  println("Game exited. Goodbye! #main")
}

/* 
object Main extends App {
  // initialize the game
  val gameLogic = new GameLogic(new PlayingField())
  val controller = new Controller(gameLogic)
  val view = new ConsoleView(controller)

  // Connect view to model
  gameLogic.addObserver(view)

  //start app loop
  def run(): Unit = {
    view.render()
    print("Enter command (s, v, reset, exit): ")
    scala.io.StdIn.readLine() match {
      case "exit" => println("Exiting the game. Byyyyeee!")
      case input =>
        view.processInputLine(input)
        run() //recursive call to continue loop
    }
  }


  //start game
  run()
  
} */

/* object Main {
  def main(args: Array[String]): Unit = {
    val gameLogic = new GameLogic(new PlayingField()) // Create a new instance of GameLogic with a PlayingField
    val tui = new TUI(gameLogic) // Create a new TUI instance with the game logic

    tui.run() // Start the TUI
  }
} */
