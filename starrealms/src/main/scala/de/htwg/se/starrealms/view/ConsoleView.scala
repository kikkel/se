package de.htwg.se.starrealms.view

import de.htwg.util.Observer
import de.htwg.se.starrealms.controller._
import de.htwg.se.starrealms.view.Renderer

class ConsoleView (controller: Controller, output: Unit) extends Observer {
  controller.addObserver(this) // Connect view to model
  
  def render(): Unit =
    //println("Rendering game state... #ConsoleView") // Placeholder for actual rendering logic
    println(controller.getState)

  def processInput(input: String): Boolean = {
    input match {
      case "x" =>
        println("\n\nExiting the game... #ConsoleView")
        false 
      case _ =>
        println(controller.processCommand(input))
        true

    }
  }
  override def update: Unit = render()
}