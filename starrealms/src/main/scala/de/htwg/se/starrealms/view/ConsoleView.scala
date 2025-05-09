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
      case "x" =>
        println("\n\nExiting the game. #ConsoleView")
        false // Signal to exit the loop
      case _ =>
        println("\n\nInvalid input. Please enter 's', 'v', 'reset', or 'exit'. #ConsoleView\n\n")
        true

    }

    }
  }



