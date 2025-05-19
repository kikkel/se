package de.htwg.se.starrealms.view

import de.htwg.util.Observer
import de.htwg.se.starrealms.controller.CommandProcessor

class ConsoleView(processor: CommandProcessor) extends Observer {
  
  def render(): Unit = {
    println("\n\n")
    println("Welcome to Star Realms!")
    println("Enter 's' to draw a Scout")
    println("Enter 'v' to draw a Viper")
    println("Enter 'r' to reset the game")
    println("Enter 'x' to exit the game")
    print("Your command: ")
  }

  def processInput(input: String): Boolean = {
    input match {
      case "x" =>
        println("\n\nExiting the game... #ConsoleView")
        false 
      case _ =>
        println(processor.processCommand(input))
        true

    }
  }
  override def update: Unit = render()
}