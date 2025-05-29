package de.htwg.se.starrealms.model

import scala.collection.mutable.ListBuffer
import de.htwg.util.Observable
import de.htwg.se.starrealms.model._


//--------------------------------------------------------------------Strategy
trait DrawStrategy { def draw(deck: Deck, count: Int): List[Card] }

class StartTurnStrategy extends DrawStrategy {
  override def draw(deck: Deck, count: Int): List[Card] = {
    (1 to count).flatMap(_ => deck.drawCard()).toList
  }
}
class TradeRowReplenishStrategy extends DrawStrategy {
  override def draw(deck: Deck, count: Int): List[Card] = {
    val cards = (1 to count).flatMap(_ => deck.drawCard()).toList
    println(s"Trade Row: ${cards.map(_.render()).mkString(", ")}")
    cards
  }
}

//--------------------------------------------------------------------GameLogic
class GameLogic(val gameState: GameState) extends Observable {
  // Existing logic for strategies, if needed
  private val replenishStrategy = new TradeRowReplenishStrategy()
  private val startTurnStrategy = new StartTurnStrategy()

  def drawCard(): Option[Card] = {
    val deck = gameState.getPlayerDeck(gameState.getCurrentPlayer)
    val cardOpt = deck.drawCard()
    cardOpt.foreach { card =>
      val newHand = gameState.getHand(gameState.getCurrentPlayer) :+ card
      gameState.setHand(gameState.getCurrentPlayer, newHand)
    }
    cardOpt
  }
  def returnCardToPlayerDeck(card: Card): Unit = {
    gameState.getPlayerDeck(gameState.getCurrentPlayer).addCard(card)
  }

  def drawCards(count: Int): List[Card] = {
    val deck = gameState.getPlayerDeck(gameState.getCurrentPlayer)
    val drawn = (1 to count).flatMap(_ => deck.drawCard()).toList
    val updatedHand = gameState.getHand(gameState.getCurrentPlayer) ++ drawn
    gameState.setHand(gameState.getCurrentPlayer, updatedHand)
    drawn
  }

  def playCard(card: Card): Unit = {
    val hand = gameState.getHand(gameState.getCurrentPlayer)
    if (hand.contains(card)) {
      val (before, after) = hand.span(_ != card)
      val newHand = before ++ after.drop(1)
      val newDiscard = card :: gameState.getDiscardPile(gameState.getCurrentPlayer)
      gameState.setHand(gameState.getCurrentPlayer, newHand)
      gameState.setDiscardPile(gameState.getCurrentPlayer, newDiscard)
    }
  }

  def returnCardToHand(card: Card): Unit = {
    val player = gameState.getCurrentPlayer
    val discard = gameState.getDiscardPile(player)
    val (before, after) = discard.span(_ != card)
    val newDiscard = before ++ after.drop(1)
    val newHand = card :: gameState.getHand(player)
    gameState.setDiscardPile(player, newDiscard)
    gameState.setHand(player, newHand)
  }


  def buyCard(card: Card): Unit = {
    val tradeRow = gameState.getTradeRow
    if (tradeRow.contains(card)) {
      val (before, after) = tradeRow.span(_ != card)
      val updatedTradeRow = before ++ after.drop(1)
      val newDiscard = card :: gameState.getDiscardPile(gameState.getCurrentPlayer)
      gameState.setTradeRow(updatedTradeRow)
      gameState.setDiscardPile(gameState.getCurrentPlayer, newDiscard)
    }
  }
  def returnCardToTradeRow(card: Card): Unit = {
    val player = gameState.getCurrentPlayer
    val discard = gameState.getDiscardPile(player)
    val (before, after) = discard.span(_ != card)
    val newDiscard = before ++ after.drop(1)
    val newTradeRow = card :: gameState.getTradeRow
    gameState.setDiscardPile(player, newDiscard)
    gameState.setTradeRow(newTradeRow)
  }

  def replenishTradeRow(): Unit = {
    val deck = gameState.getTradeDeck
    var tradeRow = gameState.getTradeRow
    while (tradeRow.size < 5 && deck.getCards.nonEmpty) {
      deck.drawCard().foreach { card => tradeRow = tradeRow :+ card }
    }
    gameState.setTradeRow(tradeRow)
  }

  def undoReplenish(card: Card): Unit = {
    val tradeRow = gameState.getTradeRow
    val (before, after) = tradeRow.span(_ != card)
    val newTradeRow = before ++ after.drop(1)
    gameState.setTradeRow(newTradeRow)
  }

  def endTurn(): Unit = {
    val current = gameState.getCurrentPlayer
    val opp = gameState.getOpponent
    val discard = gameState.getDiscardPile(current)
    val updatedDiscard = discard ++ gameState.getHand(current)
    gameState.setDiscardPile(current, updatedDiscard)
    gameState.setHand(current, List())
    gameState.setCurrentPlayer(opp)
    gameState.setOpponent(current)
    drawCards(5)
  }
  def undoEndTurn(): Unit = {
    val current = gameState.getCurrentPlayer
    val opp = gameState.getOpponent
    val discard = gameState.getDiscardPile(opp)
    val updatedDiscard = discard ++ gameState.getHand(opp)
    gameState.setDiscardPile(opp, updatedDiscard)
    gameState.setHand(opp, List())
    gameState.setCurrentPlayer(current)
    gameState.setOpponent(opp)
    drawCards(5)
  }

  def dealDamageToOpponent(amount: Int): Unit = {
    gameState.getOpponent.takeDamage(amount)
  }

  def resetGame(): Unit = {
    gameState.initializeDecks(gameState.decksByRole) 
    drawCards(5)
    replenishTradeRow()
    gameState.notifyStateChange()
  }
  def undoResetGame(): Unit = {
    //
    gameState.notifyStateChange() 
  }
}
