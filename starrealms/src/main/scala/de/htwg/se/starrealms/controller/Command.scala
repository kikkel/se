package de.htwg.se.starrealms.controller

import de.htwg.se.starrealms.model._
import de.htwg.util.Observable

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
  override def doMove: Unit = { drawnCards = controller.gameLogic.drawCards(count) }
  override def undoMove: Unit = { drawnCards.foreach(controller.gameLogic.returnCardToPlayerDeck); drawnCards = Nil }
}

class ReplenishTradeRowCommand(controller: Controller) extends Command {
  private var rep: List[Card] = Nil
  def setRep(cards: List[Card]): Unit = { rep = cards }
  override def doMove: Unit = controller.gameLogic.replenishTradeRow()
  override def undoMove: Unit = { rep.foreach(controller.gameLogic.undoReplenish); rep = Nil }
}
class DrawCardCommand(controller: Controller) extends Command {
  private var drawnCard: Option[Card] = None
  override def doMove: Unit = { drawnCard = controller.gameLogic.drawCard() }
  override def undoMove: Unit = { drawnCard.foreach(controller.gameLogic.returnCardToPlayerDeck); drawnCard = None }
}

class PlayCardCommand(controller: Controller, card: Card) extends Command {
  override def doMove: Unit = controller.gameLogic.playCard(card); override def undoMove: Unit = controller.gameLogic.returnCardToHand(card) }

class BuyCardCommand(controller: Controller, card: Card) extends Command {
  override def doMove: Unit = controller.gameLogic.buyCard(card); override def undoMove: Unit = controller.gameLogic.returnCardToTradeRow(card) }

class EndTurnCommand(controller: Controller) extends Command {
  override def doMove: Unit = controller.gameLogic.endTurn(); override def undoMove: Unit = controller.gameLogic.undoEndTurn() }


class ResetGameCommand(controller: Controller) extends Command {
  override def doMove: Unit = controller.gameLogic.resetGame(); override def undoMove: Unit = controller.gameLogic.undoResetGame() }

class ShowDeckCommand(controller: Controller) extends Command {
  override def doMove: Unit = println(controller.gameState.getDeckState); override def undoMove: Unit = {} }

class InvalidCommand(input: String) extends Command {
  override def doMove: Unit = println(s"Invalid command: $input"); override def undoMove: Unit = {} }

