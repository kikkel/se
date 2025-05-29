package de.htwg.se.starrealms.view

import scala.swing.Reactor
import de.htwg.se.starrealms.controller.{CommandProcessor, UpdateEvent}
import scala.swing.Publisher

class ConsoleView(processor: CommandProcessor with Publisher) extends Reactor {

  private var inPlayPhase = false

  listenTo(processor)

  reactions += {
    case _: UpdateEvent => println(render())
  }

  def render(): String = {
    val sb = new StringBuilder
    sb.append("\n\n")
    // Spielerinfos ausgeben
    sb.append("Players: ")
    sb.append(processor.processCommand("show players")).append("\n")
    sb.append("Current Player: ")
    sb.append(processor.processCommand("show current")).append("\n\n")
    sb.append("Hand: ").append(processor.processCommand("show hand")).append("\n")
    sb.append("Trade Row: ").append(processor.processCommand("show trade")).append("\n")
    sb.append("Discard: ").append(processor.processCommand("show discard")).append("\n")
    if (!inPlayPhase) {
      sb.append("Enter 's' to start your turn\n")
      sb.append("Enter 't' to replenish the trade row\n")
      sb.append("Enter 'x' to exit the game\n\n")
    } else {
      sb.append("Its your turn!\n")
      sb.append("Enter 'p <number>' to play a card from your hand\n")
      sb.append("Enter 'b <number>' to buy a card from the trade row\n")
      sb.append("Enter 'e' to end your turn\n")
      sb.append("Enter 'z' to undo the last action\n")
      sb.append("Enter 'y' to redo the last undone action\n")
      sb.append("Enter 'x' to exit the game\n")
    }
    sb.toString()
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
          true
        case "z" =>
          println(processor.processCommand("z"))
          true
        case "y" =>
          println(processor.processCommand("y"))
          true
        case "t" =>
          println(processor.processCommand("t"))
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
          true
        case Array("z") =>
          println(processor.processCommand("z"))
          true
        case Array("y") =>
          println(processor.processCommand("y"))
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
}