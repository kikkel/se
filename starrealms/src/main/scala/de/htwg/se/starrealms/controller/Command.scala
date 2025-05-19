package de.htwg.se.starrealms.controller

import de.htwg.se.starrealms.model._
import de.htwg.util.Observable
import scala.util.Failure

trait Command { 
  def doMove: Unit
  def undoMove: Unit
  def redoMove: Unit
}

trait CommandProcessor { def processCommand(input: String): String } //Strategy

class CommandHandler(controller: Controller) extends CommandProcessor {
  override def processCommand(input: String): String = {
    input match {
      case "s" =>
        controller.drawCard()
        "Drew a Scout"
      case "v" =>
        controller.drawCard()
        "Drew a Viper"
      case "r" =>
        controller.resetGame()
        "Game reset"
      case "x" =>
        "Exiting game"
      case _ =>
        "Invalid command"
    }
  }
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
      case Nil => Failure(new NoSuchElementException("No moves to undo #Command"))
      case head::stack => 
        head.undoMove
        undoStack = stack
        redoStack = head :: redoStack
    }
  } 

  def redoMove = {
    redoStack match {
      case Nil => Failure(new NoSuchElementException("No moves to redo #Command"))
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

  override def undoMove: Unit = {
    drawnCards.foreach(controller.gameState.returnCardToDeck)
    drawnCards = Nil
  }

  override def redoMove: Unit = { doMove }
}

class ReplenishTradeRowCommand(controller: Controller, count: Int) extends Command {
  override def doMove: Unit = controller.gameState.replenishTradeRow(count)
  private var drawnCards: List[Card] = Nil

  override def undoMove: Unit = { drawnCards.foreach(controller.gameState.returnCardToDeck); drawnCards = Nil }

  override def redoMove: Unit = doMove
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