package de.htwg.se.starrealms.controller.ControllerComponent.impl

import de.htwg.util.{Observable, UndoManager}
import de.htwg.se.starrealms.controller.GameMediatorComponent.interface.GameMediator

import de.htwg.se.starrealms.model.CardComponent.interface.Card
import de.htwg.se. starrealms.model.GameStateComponent.interface.GameStateInterface
import de.htwg.se. starrealms.model.PlayerComponent.interface.PlayerInterface
import de.htwg.se.starrealms.model.DeckComponent.interface.DeckInterface

import de.htwg.se.starrealms.controller.ControllerComponent.interface.ControllerInterface
import de.htwg.se.starrealms.controller.GameLogicComponent.interface.GameLogicInterface
import de.htwg.se.starrealms.controller.GameMediatorComponent.interface.GameMediator
//import de.htwg.se.starrealms.controller.ControllerComponent._

class Controller(val mediator: GameMediator) extends Observable with ControllerInterface {
  val gameState: GameStateInterface = gameLogic.gameState
  val undoManager: UndoManager = new UndoManager()

  override def drawCards(count: Int): Unit = { val command = new DrawCardsCommand(this, count); undoManager.doMove(command); notifyObservers() }
  override def replenishTradeRow: Unit = { val command = new ReplenishTradeRowCommand(this); undoManager.doMove(command); notifyObservers() }
  override def drawCard: Unit = { val command = new DrawCardCommand(this); undoManager.doMove(command); notifyObservers() }
  override def playCard(card: Card): Unit = { val command = new PlayCardCommand(this, card); undoManager.doMove(command); notifyObservers() }
  override def buyCard(card: Card): Unit = { val command = new BuyCardCommand(this, card); undoManager.doMove(command); notifyObservers() }
  override def endTurn: Unit = { gameLogic.endTurn; notifyObservers() }
  override def resetGame: Unit = { val command = new ResetGameCommand(this); undoManager.doMove(command); notifyObservers() }
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
}