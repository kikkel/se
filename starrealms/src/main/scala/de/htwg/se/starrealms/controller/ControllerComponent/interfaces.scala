package de.htwg.se.starrealms.controller.ControllerComponent
import de.htwg.se.starrealms.model.PlayerComponent._
import de.htwg.se.starrealms.model.SetUpComponent.{Card, Deck}
import scala.util.Try


trait Builder {
    def reset(): Unit
    def setName(name: String): Unit
    def setCards(cards: Map[Card, Int]): Unit
    def addCard(card: Card): Unit
    def addCards(cards: List[Card]): Unit
    def getProduct(): Deck
}
trait GameStateReadOnly { //Read-Only Interface related to proxy ;)
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
