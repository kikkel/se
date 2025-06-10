package de.htwg.se.starrealms.view

import de.htwg.se.starrealms.controller.ControllerComponent._
import de.htwg.se.starrealms.controller.ControllerComponent.structure._
import de.htwg.se.starrealms.model.CardComponent.Card
import de.htwg.se.starrealms.controller.GameMediatorComponent.GameMediator
import de.htwg.se.starrealms.controller.ControllerComponent.ControllerInterface


trait CommandAdapter { def handleInput(input: String): String; def getState: String } //Adapter

class CommandProcessorAdapter(mediator: GameMediator, controller: ControllerInterface) extends CommandAdapter {
  override def handleInput(input: String): String = {
    val tokens = input.trim.toLowerCase.split("\\s+")
    tokens match {
      case Array("show", "health") =>
        s"${mediator.getGameState.getCurrentPlayer.getName}: ${mediator.getGameState.getCurrentPlayer.getHealth} HP, " +
        s"${mediator.getGameState.getOpponent.getName}: ${mediator.getGameState.getOpponent.getHealth} HP"
      case Array("p", num) if num.forall(_.isDigit) =>
        val idx = num.toInt - 1
        val hand = mediator.getGameState.getHand(mediator.getGameState.getCurrentPlayer)
        if (idx >= 0 && idx < hand.size) {
          controller.getUndoManager.doMove(new structure.PlayCardCommand(mediator, hand(idx))); s"Played card: ${hand(idx).cardName}\n\n"
        } else { "Invalid card index.\n\n" }
      case Array("b", num) if num.forall(_.isDigit) =>
        val idx = num.toInt - 1
        val tradeRow = mediator.getGameState.getTradeRow
        if (idx >= 0 && idx < tradeRow.size) {
          controller.getUndoManager.doMove(new BuyCardCommand(mediator, tradeRow(idx))); s"Bought card: ${tradeRow(idx).cardName}\n\n"
        } else { "Invalid card index." }
      case Array("p") => "Enter the number of the card you want to play (e.g. 'p 2').\n\n"
      case Array("b") => "Enter the number of the card you want to buy (e.g. 'b 3').\n\n"
      case Array(cmd) => cmd match {
        case "s" => controller.getUndoManager.doMove(new DrawCardsCommand(mediator, 5))
          "Turn started.\n\n"
        case "t" => controller.getUndoManager.doMove(new ReplenishTradeRowCommand(mediator))
          "Trade row replenished.\n\n"
        case "e" =>
          controller.getUndoManager.doMove(new EndTurnCommand(mediator))
          val result = mediator.getGameState.checkGameOver.getOrElse("Turn ended.\n\n")
          result
        case "r" => controller.getUndoManager.doMove(new ResetGameCommand(mediator))
          "Game reset.\n\n"
        case "z" => controller.getUndoManager.undoMove; "Undo performed.\n\n"
        case "y" => controller.getUndoManager.redoMove; "Redo performed.\n\n"
        case "show" => controller.getState
        case _ => "Unknown command.\n\n"
      }
      case _ => "Unknown command.\n\n"
    }
  }

  override def getState: String = controller.getState
}

