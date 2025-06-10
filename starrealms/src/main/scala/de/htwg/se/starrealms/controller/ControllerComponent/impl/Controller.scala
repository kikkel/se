package de.htwg.se.starrealms.controller.ControllerComponent.impl

import de.htwg.util.{Observable, UndoManager}
import de.htwg.se.starrealms.controller.GameMediatorComponent.GameMediator

import de.htwg.se.starrealms.controller.ControllerComponent.impl._
import de.htwg.se.starrealms.controller.ControllerComponent.structure._

import de.htwg.se.starrealms.model.GameCore.{Card, DeckInterface}
import de.htwg.se. starrealms.model.GameStateComponent.GameStateInterface
import de.htwg.se. starrealms.model.PlayerComponent.PlayerInterface

import de.htwg.se.starrealms.controller.ControllerComponent.ControllerInterface
import de.htwg.se.starrealms.controller.GameLogicComponent.GameLogicInterface
import de.htwg.se.starrealms.controller.GameMediatorComponent.GameMediator
import scalafx.scene.input.KeyCode.Undo
//import de.htwg.se.starrealms.controller.ControllerComponent._

class Controller(val mediator: GameMediator) extends Observable with ControllerInterface {
  def gameState: GameStateInterface = mediator.getGameState
  def gameLogic: GameLogicInterface = mediator.getGameLogic
  val undoManager: UndoManager = new UndoManager()

  override def drawCards(count: Int): Unit = { val command = new DrawCardsCommand(mediator, count); undoManager.doMove(command); notifyObservers() }
  override def replenishTradeRow: Unit = { val command = new ReplenishTradeRowCommand(mediator); undoManager.doMove(command); notifyObservers() }
  override def drawCard: Unit = { val command = new DrawCardCommand(mediator); undoManager.doMove(command); notifyObservers() }
  override def playCard(card: Card): Unit = { val command = new PlayCardCommand(mediator, card); undoManager.doMove(command); notifyObservers() }
  override def buyCard(card: Card): Unit = { val command = new BuyCardCommand(mediator, card); undoManager.doMove(command); notifyObservers() }
  override def endTurn: Unit = { gameLogic.endTurn; notifyObservers() }
  override def resetGame: Unit = { val command = new ResetGameCommand(mediator); undoManager.doMove(command); notifyObservers() }
  override def undo: Unit = { undoManager.undoMove; notifyObservers() }
  override def redo: Unit = { undoManager.redoMove; notifyObservers() }

  override def getCurrentPlayer: PlayerInterface = gameState.getCurrentPlayer
  override def getOpponent: PlayerInterface = gameState.getOpponent

  // Zugriff auf Deck, Hand, Discard für aktuellen Spieler
  override def getPlayerDeck: DeckInterface = gameState.getPlayerDeck(getCurrentPlayer)
  override def getHand: List[Card] = gameState.getHand(getCurrentPlayer)
  override def getDiscardPile: List[Card] = gameState.getDiscardPile(getCurrentPlayer)

  override def getTradeDeck: DeckInterface = gameState.getTradeDeck
  override def getTradeRow: List[Card] = gameState.getTradeRow
  override def getExplorerPile: DeckInterface = gameState.getExplorerPile

  override def getState: String = gameState.getDeckState
  override def getUndoManager: UndoManager = undoManager
}