package de.htwg.se.starrealms.view

import de.htwg.util.Observer
import de.htwg.se.starrealms.controller.CommandProcessor

class ConsoleView(processor: CommandProcessor) extends Observer {

  private var inPlayPhase = false

  def render(): Unit = {
    println("\n\n")
    println(processor.processCommand("show")) // Gibt PlayerDeck, Hand usw. aus
    if (!inPlayPhase) {
      println("Enter 's' to start your turn")
      println("Enter 't' to replenish the trade row")
      println("Enter 'r' to reset the game")
      println("Enter 'x' to exit the game\n\n")
    } else {
      println("Its your turn!")
      println("Enter 'p <number>' to play a card from your hand")
      println("Enter 'b <number>' to buy a card from the trade row")
      println("Enter 'e' to end your turn")
      println("Enter 'z' to undo the last action")
      println("Enter 'y' to redo the last undone action")
      println("Enter 'x' to exit the game")
    }
  }

  def processInput(input: String): Boolean = {
    if (!inPlayPhase) {
      input match {
        case "x" =>
          println("\n\nExiting the game... #ConsoleView")
          false
        case "s" =>
          println(processor.processCommand("s"))
          inPlayPhase = true
          render
          true
        case "z" =>
          println(processor.processCommand("z"))
          render
          true
        case "y" =>
          println(processor.processCommand("y"))
          render
          true
        case _ =>
          println("Invalid command. Please try again.")
          true
      }
    } else {
      val tokens = input.trim.toLowerCase.split("\\s+")
      tokens match {
        case Array("x") =>
          println("\n\nExiting the game... #ConsoleView")
          false
        case Array("e") =>
          println(processor.processCommand("e"))
          inPlayPhase = false
          render
          true
        case Array("z") =>
          println(processor.processCommand("z"))
          render
          true
        case Array("y") =>
          println(processor.processCommand("y"))
          render
          true
        case Array("p", num) if num.forall(_.isDigit) =>
          println(processor.processCommand(s"p $num"))
          true
        case Array("b", num) if num.forall(_.isDigit) =>
          println(processor.processCommand(s"b $num"))
          true
        case Array(num) if num.forall(_.isDigit) =>
          println(processor.processCommand(s"p $num"))
          true
        case _ =>
          println("Invalid command. Please try again.")
          true
      }
    }
  }
  override def update: Unit = {
    render
  }
}