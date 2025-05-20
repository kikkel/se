package de.htwg.se.starrealms.model

import de.htwg.util._

class GameState extends Observable {
  private var deck: List[Card] = List()
  private var hand: List[Card] = List()
  private var tradeRow: List[Card] = List()
  private var discardPile: List[Card] = List()

  def drawCards(count: Int): List[Card] = {
    val drawnCards = deck.take(count)
    deck = deck.drop(count)
    hand = drawnCards ++ hand
    notifyObservers()
    drawnCards
  }
  def replenishTradeRow(count: Int): Unit = {
    val newCards = deck.take(count)
    deck = deck.drop(5)
    tradeRow = newCards ++ tradeRow
    notifyObservers()
  }
  def drawCard(): Option[Card] = {
    deck match {
      case Nil => None
      case card :: rest =>
        deck = rest
        hand = card :: hand
        notifyObservers()
        Some(card)
    }
  }
  def returnCardToDeck(card: Card): Unit = {
    deck = card :: deck
    hand = hand.filterNot(_ == card)
    notifyObservers()
  }
  def playCard(card: Card): Unit = {
    hand = hand.filterNot(_ == card)
    discardPile = card :: discardPile
    notifyObservers()
  }
  def returnCardToHand(card: Card): Unit = {
    hand = card :: hand
    discardPile = discardPile.filterNot(_ == card)
    notifyObservers()
  }
  def buyCard(card: Card): Unit = {
    discardPile = card :: discardPile
    notifyObservers()
  }
  def returnCardToTradeDeck(card: Card): Unit = {
    discardPile = card :: discardPile
    notifyObservers()
  }
  def endTurn(): Unit = {
    discardPile = hand ++ discardPile
    hand = List()
    notifyObservers()
  }
  def undoEndTurn(): Unit = {
    hand = discardPile.take(5)
    discardPile = discardPile.drop(5)
    notifyObservers()
  }
  def resetGame(): Unit = {
    deck = List()
    hand = List()
    discardPile = List()
    notifyObservers()
  }
  def undoResetGame(): Unit = {
    // Logic to undo reset game
    notifyObservers()
  }
  def getDeckState: Unit = {
    println("Deck: " + deck.mkString(", "))
    println("Hand: " + hand.mkString(", "))
    println("Discard Pile: " + discardPile.mkString(", "))
  }
}