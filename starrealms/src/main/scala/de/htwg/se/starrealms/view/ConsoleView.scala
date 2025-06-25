package de.htwg.se.starrealms.view

import de.htwg.util.{Observer, Observable}
import de.htwg.se.starrealms.model.GameStateComponent.GameStateReadOnly
import de.htwg.se.starrealms.model.GameCore.CardInterface
import de.htwg.se.starrealms.controller.GameLogicComponent.GameLogicInterface

import com.google.inject.Inject

/*
Use readOnlyState sparingly, and only for:
	•	read-only visual rendering
	•	shared UI components (TUI + GUI) that need direct game context
example of how to use readOnlyState in ConsoleView:

val hand = readOnlyState.getHand(readOnlyState.getCurrentPlayer)
hand.foreach(card => println(card.cardName))
 */


class ConsoleView @Inject() (processor: CommandAdapter, readOnlyState: GameStateReadOnly, gameLogic: GameLogicInterface) extends Observer {
  gameLogic.addObserver(this)
  private var inPlayPhase = false

  private val baseRenderer = new CardRenderer()
  private val cardRenderer: Renderer[CardInterface] = new LoggingDecorator(baseRenderer)

  def render(): String = {
    val sb = new StringBuilder
    sb.append("\n\n")
    val current = readOnlyState.getCurrentPlayer
    val opponent = readOnlyState.getOpponent
    sb.append(s"Current Player: ${current.getName} (Health: ${current.getHealth})\n")
    sb.append(s"Opponent: ${opponent.getName} (Health: ${opponent.getHealth})\n\n")
    sb.append(s"Your Hand:\n")
    val hand = readOnlyState.getHand(current)
    if (hand.isEmpty) sb.append("  (empty)\n")
    else hand.zipWithIndex.foreach { case (card, idx) =>
      sb.append(s"  [$idx] ${card.cardName}\n")
    }
    sb.append(s"\nYour Deck: ${readOnlyState.getPlayerDeck(current).getCardStack.size} cards\n")
    sb.append(s"Your Discard Pile: ${readOnlyState.getDiscardPile(current).size} cards\n")
    sb.append(s"\nTrade Row:\n")
    val tradeRow = readOnlyState.getTradeRow
    if (tradeRow.isEmpty) sb.append("  (empty)\n")
    else tradeRow.zipWithIndex.foreach { case (card, idx) =>
      sb.append(s"  [$idx] ${card.cardName}\n")
    }
    sb.append(s"\nTrade Deck: ${readOnlyState.getTradeDeck.getCardStack.size} cards\n")
    sb.append(s"Explorer Pile: ${readOnlyState.getExplorerPile.getCardStack.size} cards\n\n")

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
      input.trim.toLowerCase match {
        case "x" =>
          println("\n\nExiting the game... #ConsoleView")
          false
        case "s" =>
          println(processor.handleInput("s"))
          inPlayPhase = true
          println(render())
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
    println(render())
    val hand = readOnlyState.getHand(readOnlyState.getCurrentPlayer)
    hand.foreach(card => println(cardRenderer.render(card)))
    println(processor.getState)
  }
}