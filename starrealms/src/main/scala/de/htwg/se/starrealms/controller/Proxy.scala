package de.htwg.se.starrealms.controller

import de.htwg.se.starrealms.model._

class DrawStrategyProxy extends DrawStrategy {
  private lazy val realStrategy: DrawStrategy = new DefaultDrawStrategy()

  override def draw(deck: Deck, count: Int): List[Card] = {
    println(s"[Proxy] Preparing to draw $count card(s).")
    val result = realStrategy.draw(deck, count)
    println(s"[Proxy] Completed drawing. Cards drawn: ${result.map(_.cardName).mkString(", ")}")
    result
  }
}

//obtain either real or proxy strategy
object DrawStrategyFactory {
  def getStrategy(useProxy: Boolean = true): DrawStrategy = {
    if (useProxy) new DrawStrategyProxy()
    else new DefaultDrawStrategy()
  }
}

class GameStateProxy(private val gameState: GameState) extends GameStateReadOnly {
  override def getCurrentPlayer: Player = gameState.getCurrentPlayer
  override def getOpponent: Player = gameState.getOpponent
  override def getPlayerDeck(player: Player): Deck = gameState.getPlayerDeck(player)
  override def getHand(player: Player): List[Card] = gameState.getHand(player)
  override def getDiscardPile(player: Player): List[Card] = gameState.getDiscardPile(player)
  override def getDiscardPiles: Map[Player, List[Card]] = gameState.getDiscardPiles
  override def getLastDiscardedHand(player: Player): List[Card] = gameState.getLastDiscardedHand(player)
  override def getTradeDeck: Deck = gameState.getTradeDeck
  override def getTradeRow: List[Card] = gameState.getTradeRow
  override def getExplorerPile: Deck = gameState.getExplorerPile
  override def checkGameOver(): Option[String] = gameState.checkGameOver()
  override def getDeckState: String = gameState.getDeckState
}