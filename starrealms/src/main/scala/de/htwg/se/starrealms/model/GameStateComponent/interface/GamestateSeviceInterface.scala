package de.htwg.se.starrealms.model.GameStateComponent.interface

import de.htwg.se.starrealms.model.PlayerComponent.interface.PlayerInterface
import de.htwg.se.starrealms.model.CardComponent.interface.Card
import de.htwg.se.starrealms.model.DeckComponent.interface.DeckInterface
import de.htwg.util.Observable
import scala.util.Try



trait GameStateReadOnly { //Service Interface
  def getCurrentPlayer: PlayerInterface
  def getOpponent: PlayerInterface
  def getPlayerDeck(player: PlayerInterface): DeckInterface
  def getHand(player: PlayerInterface): List[Card]
  def getDiscardPile(player: PlayerInterface): List[Card]
  def getDiscardPiles: Map[PlayerInterface, List[Card]]
  def getLastDiscardedHand(player: PlayerInterface): List[Card]
  def getTradeDeck: DeckInterface
  def getTradeRow: List[Card]
  def getExplorerPile: DeckInterface
  def checkGameOver(): Option[String]
  def getDeckState: String
}

trait GameStateInterface extends Observable {
  def getCurrentPlayer: PlayerInterface
  def getOpponent: PlayerInterface
  def getPlayerDeck(player: PlayerInterface): DeckInterface
  def getHand(player: PlayerInterface): List[Card]
  def getDiscardPile(player: PlayerInterface): List[Card]
  def getDiscardPiles: Map[PlayerInterface, List[Card]]
  def getLastDiscardedHand(player: PlayerInterface): List[Card]
  def getTradeDeck: DeckInterface
  def getTradeRow: List[Card]
  def getExplorerPile: DeckInterface
  def getDeckState: String
  def getDecksByRole: Map[String, DeckInterface]

  def setCurrentPlayer(player: PlayerInterface): Unit
  def setOpponent(player: PlayerInterface): Unit
  def swapPlayers: Unit
  def setPlayerDeck(player: PlayerInterface, deck: DeckInterface): Unit
  def setHand(player: PlayerInterface, hand: List[Card]): Unit
  def setDiscardPile(player: PlayerInterface, discard: List[Card]): Unit
  def setLastDiscardedHand(player: PlayerInterface, hand: List[Card]): Unit
  def setTradeDeck(deck: DeckInterface): Unit
  def setTradeRow(row: List[Card]): Unit
  def setExplorerPile(deck: DeckInterface): Unit
  def setDeckState: String

  def initializeDecks(deck: Map[String, DeckInterface]): Unit
  def notifyStateChange(): Unit
  def checkGameOver: Option[String]
}
