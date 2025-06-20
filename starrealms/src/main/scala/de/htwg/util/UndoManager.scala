package de.htwg.util

class UndoManager {
  private var undoStack: List[CommandInterface] = Nil
  private var redoStack: List[CommandInterface] = Nil

  def getUndoStack: List[CommandInterface] = undoStack
  def getRedoStack: List[CommandInterface] = redoStack

  def doMove(command: CommandInterface) = {
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