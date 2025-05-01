package de.htwg.se.starrealms.app

import de.htwg.se.starrealms.controller.Controller
import de.htwg.se.starrealms.model.{GameLogic, PlayingField}
import de.htwg.se.starrealms.view.ConsoleView

object Main extends App {
  // Initialize the model, controller, and view
  val gameLogic = new GameLogic(new PlayingField())
  val controller = new Controller(gameLogic)
  val view = new ConsoleView(controller)

  // Application loop
  var continue = true
  while (continue) {
    // Render the current state of the game
    view.render()

    // Prompt the user for input
    println("Enter command (s: draw Scout, v: draw Viper, reset: reset game, exit: quit):")
    val input = scala.io.StdIn.readLine()

    // Process the input and decide whether to continue
    continue = view.processInputLine(input)
  }

  println("Game exited. Goodbye!")
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
