package de.htwg.util

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