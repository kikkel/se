package de.htwg.se.starrealms.view

import de.htwg.se.starrealms.controller.ControllerComponent._
import de.htwg.se.starrealms.model.CardComponent.interface.Card


trait CommandAdapter { def handleInput(input: String): String; def getState: String } //Adapter

class CommandProcessorAdapter(controller: Controller) extends CommandAdapter {
  override def handleInput(input: String): String = {
    val tokens = input.trim.toLowerCase.split("\\s+")
    tokens match {
      case Array("show", "health") =>
        s"${controller.gameState.getCurrentPlayer.getName}: ${controller.gameState.getCurrentPlayer.getHealth} HP, " +
        s"${controller.gameState.getOpponent.getName}: ${controller.gameState.getOpponent.getHealth} HP"
      case Array("p", num) if num.forall(_.isDigit) =>
        val idx = num.toInt - 1
        val hand = controller.gameState.getHand(controller.gameState.getCurrentPlayer)
        if (idx >= 0 && idx < hand.size) {
          controller.undoManager.doMove(new PlayCardCommand(controller, hand(idx))); s"Played card: ${hand(idx).cardName}\n\n"
        } else { "Invalid card index.\n\n" }
      case Array("b", num) if num.forall(_.isDigit) =>
        val idx = num.toInt - 1
        val tradeRow = controller.gameState.getTradeRow
        if (idx >= 0 && idx < tradeRow.size) {
          controller.undoManager.doMove(new BuyCardCommand(controller, tradeRow(idx))); s"Bought card: ${tradeRow(idx).cardName}\n\n"
        } else { "Invalid card index." }
      case Array("p") => "Enter the number of the card you want to play (e.g. 'p 2').\n\n"
      case Array("b") => "Enter the number of the card you want to buy (e.g. 'b 3').\n\n"
      case Array(cmd) => cmd match {
        case "s" => controller.undoManager.doMove(new DrawCardsCommand(controller, 5))
          "Turn started.\n\n"
        case "t" => controller.undoManager.doMove(new ReplenishTradeRowCommand(controller))
          "Trade row replenished.\n\n"
        case "e" =>
          controller.undoManager.doMove(new EndTurnCommand(controller))
          val result = controller.gameState.checkGameOver.getOrElse("Turn ended.\n\n")
          result
        case "r" => controller.undoManager.doMove(new ResetGameCommand(controller))
          "Game reset.\n\n"
        case "z" => controller.undoManager.undoMove; "Undo performed.\n\n"
        case "y" => controller.undoManager.redoMove; "Redo performed.\n\n"
        case "show" => controller.getState
        case _ => "Unknown command.\n\n"
      }
      case _ => "Unknown command.\n\n"
    }
  }

  override def getState: String = controller.getState
}

