package de.htwg.se.starrealms.view

import de.htwg.util.Observer
import de.htwg.se.starrealms.controller._
import de.htwg.se.starrealms.view.Renderer

class ConsoleView (controller: Controller) extends Observer {
  def update: Unit = render()

  // Connect view to model
  controller.addObserver(this)
  def render(): Unit =
    //println("Rendering game state... #ConsoleView") // Placeholder for actual rendering logic
    println(controller.getGameState)

  def processInputLine(input: String): Boolean = {
    input.toLowerCase match {
      case "s" =>
        controller.drawScout()
        true // Schleife fortsetzen
      case "v" =>
        controller.drawViper()
        true // Schleife fortsetzen
      case "r" =>
        controller.resetGame()
        true // Schleife fortsetzener.processInput(input)
        true
      case "x" =>
        println("\n\nExiting the game. #ConsoleView")
        false // Signal to exit the loop
      case _ =>
        println("\n\nInvalid input. Please enter 's', 'v', 'reset', or 'exit'. #ConsoleView\n\n")
        true

    }
      /* println(s"Processing input: $input")
      input match {
          case "s" => println("s")
          case "v" => println("v")
          case "reset" => println("reset")
          case "exit" => println("Exiting the game.")
          case _ => println(s"Unknown command: $input")
      } */
    }
  }



/* class ConsoleView(controller: Controller) extends Observer {
  // register as obsverver of controller
  controller.addObserver(this)

  //render game state whenever notified
  override def update(): Unit = render()

  def render(): Unit = {
    println(controller.getGameState)
}

def processInputLine(input: String): Unit = {
    input match {
      case "s" => println("s")
      case "v" => println("v")
      case "reset" => println("reset")
      case "exit" => println("Exiting the game.")
      case _ => println(s"Unknown command: $input")
    }
  }
} */