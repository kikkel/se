package de.htwg.se.starrealms.model.GameStateComponent

import de.htwg.se.starrealms.model.PlayerComponent.PlayerInterface
import de.htwg.se.starrealms.model.GameCore.{CardInterface, DeckInterface}
import de.htwg.util.Observable
import scala.util.Try
import scalafx.scene.input.KeyCode.G



trait GameStateReadOnly { //Service Interface
  def getCurrentPlayer: PlayerInterface
  def getOpponent: PlayerInterface
  def getPlayerDeck(player: PlayerInterface): DeckInterface
  def getHand(player: PlayerInterface): List[CardInterface]
  def getDiscardPile(player: PlayerInterface): List[CardInterface]
  def getDiscardPiles: Map[PlayerInterface, List[CardInterface]]
  def getLastDiscardedHand(player: PlayerInterface): List[CardInterface]
  def getTradeDeck: DeckInterface
  def getTradeRow: List[CardInterface]
  def getExplorerPile: DeckInterface
  def checkGameOver: Option[String]
  def getSnapshot: GameSnapshot
}

trait GameStateInterface extends Observable {
  def getCurrentPlayer: PlayerInterface
  def getOpponent: PlayerInterface
  def getPlayerDeck(player: PlayerInterface): DeckInterface
  def getHand(player: PlayerInterface): List[CardInterface]
  def getDiscardPile(player: PlayerInterface): List[CardInterface]
  def getDiscardPiles: Map[PlayerInterface, List[CardInterface]]
  def getLastDiscardedHand(player: PlayerInterface): List[CardInterface]
  def getTradeDeck: DeckInterface
  def getTradeRow: List[CardInterface]
  def getExplorerPile: DeckInterface
  def getSnapshot: GameSnapshot
  def getDecksByRole: Map[String, DeckInterface]

  def setCurrentPlayer(player: PlayerInterface): Unit
  def setOpponent(player: PlayerInterface): Unit
  def swapPlayers: Unit
  def setPlayerDeck(player: PlayerInterface, deck: DeckInterface): Unit
  def setHand(player: PlayerInterface, hand: List[CardInterface]): Unit
  def setDiscardPile(player: PlayerInterface, discard: List[CardInterface]): Unit
  def setLastDiscardedHand(player: PlayerInterface, hand: List[CardInterface]): Unit
  def setTradeDeck(deck: DeckInterface): Unit
  def setTradeRow(row: List[CardInterface]): Unit
  def setExplorerPile(deck: DeckInterface): Unit

  def initializeDecks(deck: Map[String, DeckInterface]): Unit
  def notifyStateChange(): Unit
  def checkGameOver: Option[String]
}
