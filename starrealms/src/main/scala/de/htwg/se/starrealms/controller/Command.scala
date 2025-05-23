package de.htwg.se.starrealms.controller

import de.htwg.se.starrealms.model._
import de.htwg.util.Observable


trait Command {
  def doMove: Unit
  def undoMove: Unit
  def redoMove: Unit = doMove
}

trait CommandProcessor { def processCommand(input: String): String } //Strategy

class UndoManager {
  private var undoStack: List[Command] = Nil
  private var redoStack: List[Command] = Nil

  def getUndoStack: List[Command] = undoStack
  def getRedoStack: List[Command] = redoStack

  def doMove(command: Command) = {
    undoStack = command :: undoStack
    redoStack = Nil
    command.doMove
  }

  def undoMove = {
    undoStack match {
      case Nil => "No moves to undo #Command"
      case head::stack =>
        head.undoMove
        undoStack = stack
        redoStack = head :: redoStack
    }
  }

  def redoMove = {
    redoStack match {
      case Nil => "No moves to redo #Command"
      case head::stack =>
        head.redoMove
        redoStack = stack
        undoStack = head :: undoStack
    }
  }
}

class DrawCardsCommand(controller: Controller, count: Int) extends Command {
  private var drawnCards: List[Card] = Nil
  override def doMove: Unit = { drawnCards = controller.gameState.drawCards(count) }
  override def undoMove: Unit = { drawnCards.foreach(controller.gameState.returnCardToPlayerDeck); drawnCards = Nil }
}

class ReplenishTradeRowCommand(controller: Controller) extends Command {
  private var rep: List[Card] = Nil
  def setRep(cards: List[Card]): Unit = { rep = cards }
  override def doMove: Unit = controller.gameState.replenishTradeRow()
  override def undoMove: Unit = { rep.foreach(controller.gameState.undoReplenish); rep = Nil }
}
class DrawCardCommand(controller: Controller) extends Command {
  private var drawnCard: Option[Card] = None
  override def doMove: Unit = { drawnCard = controller.gameState.drawCard() }
  override def undoMove: Unit = { drawnCard.foreach(controller.gameState.returnCardToPlayerDeck); drawnCard = None }
}

class PlayCardCommand(controller: Controller, card: Card) extends Command {
  override def doMove: Unit = controller.gameState.playCard(card); override def undoMove: Unit = controller.gameState.returnCardToHand(card) }

class BuyCardCommand(controller: Controller, card: Card) extends Command {
  override def doMove: Unit = controller.gameState.buyCard(card); override def undoMove: Unit = controller.gameState.returnCardToTradeRow(card) }

class EndTurnCommand(controller: Controller) extends Command {
  override def doMove: Unit = controller.gameState.endTurn(); override def undoMove: Unit = controller.gameState.undoEndTurn() }


class ResetGameCommand(controller: Controller) extends Command {
  override def doMove: Unit = controller.gameState.resetGame(); override def undoMove: Unit = controller.gameState.undoResetGame() }

class ShowDeckCommand(controller: Controller) extends Command {
  override def doMove: Unit = println(controller.gameState.getDeckState); override def undoMove: Unit = {} }

class InvalidCommand(input: String) extends Command {
  override def doMove: Unit = println(s"Invalid command: $input"); override def undoMove: Unit = {} }

class CommandHandler(controller: Controller) extends CommandProcessor {
  override def processCommand(input: String): String = {
    val tokens = input.trim.toLowerCase.split("\\s+")
    tokens match {
      case Array("p", num) if num.forall(_.isDigit) =>
        val idx = num.toInt - 1
        val hand = controller.gameState.getHand
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
        case "e" => controller.undoManager.doMove(new EndTurnCommand(controller))
          "Turn ended.\n\n"
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
}