package de.htwg.se.starrealms.controller.ControllerComponent

import de.htwg.se.starrealms.model.GameCore.Card
import de.htwg.se.starrealms.model.PlayerComponent.PlayerInterface
import de.htwg.se.starrealms.model.DeckComponent.DeckInterface
import de.htwg.util.UndoManager

trait ControllerInterface {
    def drawCards(count: Int): Unit
    def replenishTradeRow: Unit
    def drawCard: Unit
    def playCard(card: Card): Unit
    def buyCard(card: Card): Unit
    def endTurn: Unit
    def resetGame: Unit
    def undo: Unit
    def redo: Unit

    def getCurrentPlayer: PlayerInterface
    def getOpponent: PlayerInterface

    def getPlayerDeck: DeckInterface
    def getHand: List[Card]
    def getDiscardPile: List[Card]

    def getTradeDeck: DeckInterface
    def getTradeRow: List[Card]
    def getExplorerPile: DeckInterface

    def getState: String
    def getUndoManager: UndoManager
}