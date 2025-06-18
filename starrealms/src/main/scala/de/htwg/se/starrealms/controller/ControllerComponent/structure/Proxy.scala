package de.htwg.se.starrealms.controller.ControllerComponent.structure


import de.htwg.se.starrealms.model.GameCore.{CardInterface, DeckInterface, DrawStrategy}
import de.htwg.se.starrealms.model.GameCore.structure.DefaultDrawStrategy

import de.htwg.se.starrealms.model.GameStateComponent.{GameStateInterface, GameStateReadOnly, GameSnapshot}
import de.htwg.se.starrealms.model.PlayerComponent.PlayerInterface

class DrawStrategyProxy extends DrawStrategy {
  private lazy val realStrategy: DrawStrategy = new DefaultDrawStrategy()

  override def draw(deck: DeckInterface, count: Int): List[CardInterface] = {
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

class GameStateProxy(private val gameState: GameStateInterface) extends GameStateReadOnly {
  override def getCurrentPlayer: PlayerInterface = gameState.getCurrentPlayer
  override def getOpponent: PlayerInterface = gameState.getOpponent
  override def getPlayerDeck(player: PlayerInterface): DeckInterface = gameState.getPlayerDeck(gameState.getCurrentPlayer)
  override def getHand(player: PlayerInterface): List[CardInterface] = gameState.getHand(player)
  override def getDiscardPile(player: PlayerInterface): List[CardInterface] = gameState.getDiscardPile(player)
  override def getDiscardPiles: Map[PlayerInterface, List[CardInterface]] = gameState.getDiscardPiles
  override def getLastDiscardedHand(player: PlayerInterface): List[CardInterface] = gameState.getLastDiscardedHand(player)
  override def getTradeDeck: DeckInterface = gameState.getTradeDeck
  override def getTradeRow: List[CardInterface] = gameState.getTradeRow
  override def getExplorerPile: DeckInterface = gameState.getExplorerPile
  override def checkGameOver: Option[String] = gameState.checkGameOver
  override def getSnapshot: GameSnapshot = gameState.getSnapshot
}