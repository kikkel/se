package de.htwg.se.starrealms.controller.ControllerComponent

import de.htwg.se.starrealms.model.GameCore.{CardInterface, DeckInterface}
import de.htwg.se.starrealms.model.PlayerComponent.PlayerInterface
import de.htwg.util.UndoManager

trait ControllerInterface {
    def startTurn(count: Int): Unit
    def replenishTradeRow: Unit
    def drawCard: Unit
    def playCard(card: CardInterface): Unit
    def buyCard(card: CardInterface): Unit
    def endTurn: Unit
    def resetGame: Unit
    def undo: Unit
    def redo: Unit

    def getCurrentPlayer: PlayerInterface
    def getOpponent: PlayerInterface

    def getPlayerDeck: DeckInterface
    def getHand: List[CardInterface]
    def getDiscardPile: List[CardInterface]

    def getTradeDeck: DeckInterface
    def getTradeRow: List[CardInterface]
    def getExplorerPile: DeckInterface

    def getState: String
    def getUndoManager: UndoManager
}