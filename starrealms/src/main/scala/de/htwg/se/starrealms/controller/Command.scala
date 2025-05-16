package de.htwg.se.starrealms.controller

import de.htwg.se.starrealms.model._
import de.htwg.util.Observable

trait Command { 
  def doMove: Unit
  def undoMove: Unit
  def redoMove: Unit
  def execute: String 
}


class SetCommand(row: Int, col: Int, value: Int, controller: Controller) extends Command {
  override def doMove: Unit = controller.grid = controller.grid.set(row, col, value)
  override def undoMove: Unit = controller.grid = controller.grid.set(row, col, 0)
  override def redoMove: Unit = controller.grid = controller.grid.set(row, col, value)
  override def execute: String = { doMove; s"Set value at ($row, $col) to $value" }
}

class UndoManager {
  private var undoStack: List[Command] = Nil
  private var redoStack: List[Command] = Nil
  def doMove(command: Command) = {
    undoStack = command :: undoStack
    command.doMove
  }
  def undoMove = {
    undoStack match {
      case Nil => "up tp date"
      case head::stack => {
        head.undoMove
        undoStack = stack
        redoStack = head :: redoStack
      }
    }
  } 
}

class DrawCardCommand(gameLogic: GameLogic, cardType: String) extends Command {
  override def execute(): String = gameLogic.drawCard() match {
    case Some(c) if c.cardName == cardType => s"Drew $cardType: ${c.render()}"
    case Some(_) => s"Wrong card drawn. #DrawCardCommand"
    case None => s"No $cardType cards left. #DrawCardCommand" 
  }
}

class ResetGameCommand(gameLogic: GameLogic) extends Command {
  override def execute(): String = {
    gameLogic.resetGame()
    "Game and deck have been reset."
  }
}

class ShowDeckCommand(controller: Controller) extends Command {
  override def execute(): String = {
    controller.getState
    "Deck shown."
  }
}

class InvalidCommand(input: String) extends Command {
  override def execute(): String = s"Invalid command: $input"
}