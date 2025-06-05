package de.htwg.se.starrealms.controller.ControllerComponent.Controller_interfaces

import de.htwg.se.starrealms.model.PlayerComponent._
import de.htwg.se.starrealms.model.SetUpComponent.{Card, Deck}
import scala.util.Try


trait GameStateReadOnly { //Service Interface
  def getCurrentPlayer: Player
  def getOpponent: Player
  def getPlayerDeck(player: Player): Deck
  def getHand(player: Player): List[Card]
  def getDiscardPile(player: Player): List[Card]
  def getDiscardPiles: Map[Player, List[Card]]
  def getLastDiscardedHand(player: Player): List[Card]
  def getTradeDeck: Deck
  def getTradeRow: List[Card]
  def getExplorerPile: Deck
  def checkGameOver(): Option[String]
  def getDeckState: String
}
