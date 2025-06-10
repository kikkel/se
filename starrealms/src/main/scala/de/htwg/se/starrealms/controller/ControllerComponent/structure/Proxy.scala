package de.htwg.se.starrealms.controller.ControllerComponent.structure


import de.htwg.se.starrealms.model.GameCore.Card
import de.htwg.se.starrealms.model.DeckComponent._
import de.htwg.se.starrealms.model.DeckComponent.structure.DefaultDrawStrategy
import de.htwg.se.starrealms.model.GameStateComponent._
import de.htwg.se.starrealms.model.PlayerComponent.PlayerInterface

class DrawStrategyProxy extends DrawStrategy {
  private lazy val realStrategy: DrawStrategy = new DefaultDrawStrategy()

  override def draw(deck: DeckInterface, count: Int): List[Card] = {
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
  override def getPlayerDeck(player: PlayerInterface): DeckInterface = gameState.getPlayerDeck(player)
  override def getHand(player: PlayerInterface): List[Card] = gameState.getHand(player)
  override def getDiscardPile(player: PlayerInterface): List[Card] = gameState.getDiscardPile(player)
  override def getDiscardPiles: Map[PlayerInterface, List[Card]] = gameState.getDiscardPiles
  override def getLastDiscardedHand(player: PlayerInterface): List[Card] = gameState.getLastDiscardedHand(player)
  override def getTradeDeck: DeckInterface = gameState.getTradeDeck
  override def getTradeRow: List[Card] = gameState.getTradeRow
  override def getExplorerPile: DeckInterface = gameState.getExplorerPile
  override def checkGameOver(): Option[String] = gameState.checkGameOver
  override def getDeckState: String = gameState.getDeckState
}