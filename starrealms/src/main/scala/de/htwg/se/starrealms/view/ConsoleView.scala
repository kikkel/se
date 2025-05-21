package de.htwg.se.starrealms.view

import de.htwg.util.Observer
import de.htwg.se.starrealms.controller.CommandProcessor

class ConsoleView(processor: CommandProcessor) extends Observer {

  private var inPlayPhase = false

  def render(): Unit = {
    println("\n\n")
    println("Welcome to Star Realms!" + "\n")
    println(processor.processCommand("show")) // Gibt PlayerDeck, Hand usw. aus
    if (!inPlayPhase) {
      println("Enter 's' to start your turn")
      println("Enter 't' to replenish the trade row")
      println("Enter 'd' to draw a card")
      println("Enter 'r' to reset the game")
      println("Enter 'x' to exit the game")
    } else {
      println("Du bist im Play-Modus. Gib die Nummer der Karte an, die du ausspielen willst (z.B. 1), oder 'e' für End Turn.")
    }
    print("Your command: ")
  }

  def processInput(input: String): Boolean = {
    if (!inPlayPhase) {
      input match {
        case "x" =>
          println("\n\nExiting the game... #ConsoleView")
          false
        case "d" =>
          println(processor.processCommand("draw"))
          inPlayPhase = true
          render()
          true
        case "show" =>
          println(processor.processCommand("show"))
          true
        case _ =>
          println(processor.processCommand(input))
          true
      }
    } else {
      input match {
        case "e" =>
          println(processor.processCommand("end"))
          inPlayPhase = false
          true
        case num if num.forall(_.isDigit) =>
          println(processor.processCommand(s"play $num"))
          true
        case _ =>
          println("Ungültiger Befehl im Play-Modus.")
          true
      }
    }
  }

  override def update: Unit = render()
}