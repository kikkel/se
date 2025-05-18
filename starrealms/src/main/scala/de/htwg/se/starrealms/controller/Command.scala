package de.htwg.se.starrealms.controller

import de.htwg.se.starrealms.model._
import de.htwg.util.Observable

trait Command { 
  def doMove: Unit
  def undoMove: Unit
  def redoMove: Unit
}

class UndoManager {
  private var undoStack: List[Command] = Nil
  private var redoStack: List[Command] = Nil

  def doMove(command: Command) = {
    undoStack = command :: undoStack
    redoStack = Nil
    command.doMove
  }

  def undoMove = {
    undoStack match {
      case Nil => println("up tp date")
      case head::stack => 
        head.undoMove
        undoStack = stack
        redoStack = head :: redoStack
    }
  } 

  def redoMove = {
    redoStack match {
      case Nil => println("up to date")
      case head::stack => 
        head.redoMove
        redoStack = stack
        undoStack = head :: undoStack
    }
  }
}

class DrawCardCommand(controller: Controller) extends Command {
  private var drawnCard: Option[Card] = None

  override def doMove: Unit = { drawnCard = controller.gameState.drawCard() }

  override def undoMove: Unit = {
    drawnCard.foreach(controller.gameState.returnCardToDeck)
    drawnCard = None
  }

  override def redoMove: Unit = { doMove }
}

class PlayCardCommand(controller: Controller, card: Card) extends Command {
  override def doMove: Unit = controller.gameState.playCard(card)

  override def undoMove: Unit = controller.gameState.returnCardToHand(card)

  override def redoMove: Unit = doMove

}

class BuyCardCommand(controller: Controller, card: Card) extends Command {
  override def doMove: Unit = controller.gameState.buyCard(card)

  override def undoMove: Unit = controller.gameState.returnCardToTradeDeck(card)

  override def redoMove: Unit = doMove
}

class EndTurnCommand(controller: Controller) extends Command {
  override def doMove: Unit = controller.gameState.endTurn()

  override def undoMove: Unit = controller.gameState.undoEndTurn()

  override def redoMove: Unit = doMove

}


class ResetGameCommand(controller: Controller) extends Command {
  override def doMove: Unit = controller.gameState.resetGame()

  override def undoMove: Unit = controller.gameState.undoResetGame()

  override def redoMove: Unit = doMove
}

class ShowDeckCommand(controller: Controller) extends Command {
  override def doMove: Unit = println(controller.gameState.getDeckState)

  override def undoMove: Unit = {}

  override def redoMove: Unit = doMove
}

class InvalidCommand(input: String) extends Command {
  override def doMove: Unit = println(s"Invalid command: $input")

  override def undoMove: Unit = {}

  override def redoMove: Unit = doMove
}