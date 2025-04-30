package de.htwg.se.starrealms.view

import de.htwg.se.starrealms.controller.Controller
import de.htwg.util.Observer

class ConsoleView (controller: Controller) extends Observer {
  def update: Unit = render()
  def render(): Unit = println("Rendering game state...") // Placeholder for actual rendering logic
    println(controller.getGameState)
  
  def processInputLine(input: String): Unit = {
      println(s"Processing input: $input")
      input match {
          case "s" => println("s")
          case "v" => println("v")
          case "reset" => println("reset")
          case "exit" => println("Exiting the game.")
          case _ => println(s"Unknown command: $input")
      }
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