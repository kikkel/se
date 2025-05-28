package de.htwg.se.starrealms.controller

import de.htwg.se.starrealms.model._
import de.htwg.util.Observable

class Controller(val gameState: GameState) extends Observable {
  val undoManager: UndoManager = new UndoManager()

  println(gameState.getDeckState)

  def drawCards(count: Int): Unit = { val command = new DrawCardsCommand(this, count); undoManager.doMove(command); notifyObservers() }
  def replenishTradeRow(): Unit = { val command = new ReplenishTradeRowCommand(this); undoManager.doMove(command); notifyObservers() }
  def drawCard(): Unit = { val command = new DrawCardCommand(this); undoManager.doMove(command); notifyObservers() }
  def playCard(card: Card): Unit = { val command = new PlayCardCommand(this, card); undoManager.doMove(command); notifyObservers() }
  def buyCard(card: Card): Unit = { val command = new BuyCardCommand(this, card); undoManager.doMove(command); notifyObservers() }
  def endTurn(): Unit = { val command = new EndTurnCommand(this); undoManager.doMove(command); notifyObservers() }
  def resetGame(): Unit = { val command = new ResetGameCommand(this); undoManager.doMove(command); notifyObservers() }
  def undo(): Unit = { undoManager.undoMove; notifyObservers() }
  def redo(): Unit = { undoManager.redoMove; notifyObservers() }

  def getState: String = gameState.getDeckState
}