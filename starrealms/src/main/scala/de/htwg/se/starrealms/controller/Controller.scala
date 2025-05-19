package de.htwg.se.starrealms.controller

import de.htwg.se.starrealms.model._
import de.htwg.util.Observable

class Controller(gameLogic: GameLogic) extends Observable with CommandProcessor {
  val gameState: GameState = new GameState()
  val undoManager: UndoManager = new UndoManager()

  def drawCards(count: Int): Unit = { val command = new DrawCardsCommand(this, count); undoManager.doMove(command); notifyObservers() }
  def replenishTradeRow(count: Int): Unit = { val command = new ReplenishTradeRowCommand(this, count); undoManager.doMove(command); notifyObservers() }
  def drawCard(): Unit = { val command = new DrawCardCommand(this); undoManager.doMove(command); notifyObservers() }
  def playCard(card: Card): Unit = { val command = new PlayCardCommand(this, card); undoManager.doMove(command); notifyObservers() }
  def buyCard(card: Card): Unit = { val command = new BuyCardCommand(this, card); undoManager.doMove(command); notifyObservers() }
  def endTurn(): Unit = { val command = new EndTurnCommand(this); undoManager.doMove(command); notifyObservers() }
  def resetGame(): Unit = { val command = new ResetGameCommand(this); undoManager.doMove(command); notifyObservers() }
  def undo(): Unit = { undoManager.undoMove; notifyObservers() }
  def redo(): Unit = { undoManager.redoMove; notifyObservers() }

  override def processCommand(input: String): String = {
    input.toLowerCase match {
      case "start turn" => drawCards(5); "Turn started."
      case "replenish" => replenishTradeRow(count); "Trade row replenished."
      case "draw" => drawCard(); "Card drawn."
      case "play" => "Specify a card to play."
      case "buy" => "Specify a card to buy."
      case "end" => endTurn(); "Turn ended."
      case "reset" => resetGame(); "Game reset."
      case "undo" => undo(); "Undo performed."
      case "redo" => redo(); "Redo performed."
      case _ => "Unknown command."
    }
  }

  def getState: Unit = gameState.getDeckState

}

