package de.htwg.se.starrealms.view

import de.htwg.se.starrealms.controller.ControllerComponent._
import de.htwg.se.starrealms.controller.ControllerComponent.structure._
import de.htwg.se.starrealms.model.GameCore.CardInterface
import de.htwg.se.starrealms.controller.GameMediatorComponent.GameMediator
import de.htwg.se.starrealms.controller.ControllerComponent.ControllerInterface


trait CommandAdapter { def handleInput(input: String): String; def getState: String } //Adapter

class CommandProcessorAdapter (mediator: GameMediator, controller: ControllerInterface) extends CommandAdapter {
  val state = mediator.getGameState

  override def handleInput(input: String): String = {
    println(s"INPUT RECEIVED: '$input'") // DEBUG
    val tokens = input.trim.toLowerCase.split("\\s+")
    tokens match {
      case Array("p", num) if num.forall(_.isDigit) =>
        val idx = num.toInt - 1
        val hand = state.getHand(state.getCurrentPlayer)
        if (idx >= 0 && idx < hand.size) {
          controller.getUndoManager.doMove(new structure.PlayCardCommand(mediator, hand(idx))); return s"Played card: ${hand(idx).cardName}\n\n"
        } else { return "Invalid card index.\n\n" }
      case Array("b", num) if num.forall(_.isDigit) =>
        val idx = num.toInt - 1
        val tradeRow = state.getTradeRow
        if (idx >= 0 && idx < tradeRow.size) {
          controller.getUndoManager.doMove(new BuyCardCommand(mediator, tradeRow(idx))); return s"Bought card: ${tradeRow(idx).cardName}\n\n"
        } else { return "Invalid card index." }
      case Array("p") => return "Enter the number of the card you want to play (e.g. 'p 2').\n\n"
      case Array("b") => return "Enter the number of the card you want to buy (e.g. 'b 3').\n\n"
      case Array(cmd) => cmd match {
        case "s" => controller.getUndoManager.doMove(new StartTurnCommand(mediator, 5))
          return "Turn started.\n\n"
        case "t" => controller.getUndoManager.doMove(new ReplenishTradeRowCommand(mediator))
          return "Trade row replenished.\n\n"
        case "e" =>
          controller.getUndoManager.doMove(new EndTurnCommand(mediator))
          val result = state.checkGameOver.getOrElse("Turn ended.\n\n")
          return result
        case "r" => controller.getUndoManager.doMove(new ResetGameCommand(mediator))
          return "Game reset.\n\n"
        case "z" => controller.getUndoManager.undoMove; return "Undo performed.\n\n"
        case "y" => controller.getUndoManager.redoMove; "return Redo performed.\n\n"
        case "show" => controller.getState
        case _ => return "Unknown command.  #Adapter: case cmd\n\n"
      }
      case _ => return "Unknown command.  #Adapter default\n\n"
    }
  }

  override def getState: String = new SnapshotRenderer().render(mediator.getGameState.getSnapshot)
}

