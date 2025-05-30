package de.htwg.se.starrealms.controller
import de.htwg.se.starrealms.model._
import scala.util.Try

trait DrawStrategy { def draw(deck: Deck, count: Int): List[Card] } //Service Interface!!
trait Command { def doMove: Unit; def undoMove: Unit; def redoMove: Unit = doMove }
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