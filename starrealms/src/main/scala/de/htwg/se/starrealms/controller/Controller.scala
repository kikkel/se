package de.htwg.se.starrealms.controller

import de.htwg.se.starrealms.model._
import de.htwg.util.Observable
import de.htwg.se.starrealms.controller

class Controller(gameLogic: GameLogic) extends Observable {
  val gameState: GameState = new GameState()
  val undoManager: UndoManager = new UndoManager()

  def drawCard(): Unit = { val command = new DrawCardCommand(this); undoManager.doMove(command) }
  def playCard(card: Card): Unit = { val command = new PlayCardCommand(this, card); undoManager.doMove(command) }
  def buyCard(card: Card): Unit = { val command = new BuyCardCommand(this, card); undoManager.doMove(command) }
  def endTurn(): Unit = { val command = new EndTurnCommand(this); undoManager.doMove(command) }
  def resetGame(): Unit = { val command = new ResetGameCommand(this); undoManager.doMove(command) }
  def undo(): Unit = { undoManager.undoMove }
  def redo(): Unit = { undoManager.redoMove }

  //WHERE DOES NOTIFYOBSERVERS() GO?!

  def getState: Unit = gameState.getDeckState
  def processCommand(input: String): String = {
    input match {
      case "s" => drawCard(); "Drew a Scout"
      case "v" => drawCard(); "Drew a Viper"
      case "r" => resetGame(); "Game reset"
      case "x" => "Exiting game"
      case _   => "Invalid command"
    }
  }
}

