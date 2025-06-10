package de.htwg.se.starrealms.view

import de.htwg.util.{Observer, Observable}
import de.htwg.se.starrealms.model.GameStateComponent.GameStateReadOnly
import de.htwg.se.starrealms.model.CardComponent.Card


/*
Use readOnlyState sparingly, and only for:
	•	read-only visual rendering
	•	shared UI components (TUI + GUI) that need direct game context
example of how to use readOnlyState in ConsoleView:

val hand = readOnlyState.getHand(readOnlyState.getCurrentPlayer)
hand.foreach(card => println(card.cardName))
 */


class ConsoleView(processor: CommandAdapter, readOnlyState: GameStateReadOnly, gameLogic: Observable) extends Observer {
  gameLogic.addObserver(this)
  private var inPlayPhase = false

  private val baseRenderer = new CardRenderer()
  private val cardRenderer: Renderer[Card] = new LoggingDecorator(baseRenderer)

  def render(): String = {
  val sb = new StringBuilder
  sb.append("\n\n")
  sb.append(s"${processor.handleInput("show players")}\n")
  sb.append(s"${processor.handleInput("show health")}\n")
  if (!inPlayPhase) {
    sb.append("Enter 't' to start game\n")
    sb.append("Enter 's' to start your turn\n")
    sb.append("Enter 'r' to reset the game\n")
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
          println(processor.handleInput("s"))
          inPlayPhase = true
          true
        case "z" =>
          println(processor.handleInput("z"))
          true
        case "y" =>
          println(processor.handleInput("y"))
          true
        case "t" =>
          println(processor.handleInput("t"))
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
          println(processor.handleInput("e"))
          inPlayPhase = false
          true
        case Array("z") =>
          println(processor.handleInput("z"))
          true
        case Array("y") =>
          println(processor.handleInput("y"))
          true
        case Array("p", num) if num.forall(_.isDigit) =>
          println(processor.handleInput(s"p $num"))
          true
        case Array("b", num) if num.forall(_.isDigit) =>
          println(processor.handleInput(s"b $num"))
          true
        case Array(num) if num.forall(_.isDigit) =>
          println(processor.handleInput(s"p $num"))
          true
        case _ =>
          println("Invalid command. Please try again.")
          true
      }
    }
  }
  override def update: Unit = {
    val hand = readOnlyState.getHand(readOnlyState.getCurrentPlayer)
    hand.foreach(card => println(cardRenderer.render(card))) 
    println(processor.getState)
  }
}