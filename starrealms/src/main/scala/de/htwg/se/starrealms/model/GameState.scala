package de.htwg.se.starrealms.model

import de.htwg.util._

class GameState(decksByRole: Map[String, Deck]) extends Observable {
  private var deck: List[Card] = List()
  private var hand: List[Card] = List()
  private var tradeRow: List[Card] = List()
  private var discardPile: List[Card] = List()
  private var playerDeck: Deck = new Deck()
  private var tradeDeck: Deck = new Deck()
  private var explorerPile = new Deck()

  initializeDecks(decksByRole)

  private def initializeDecks(decks: Map[String, Deck]): Unit = { 
    playerDeck = decks.getOrElse("Personal Deck", new Deck()); 
    playerDeck.shuffle() 
    
    tradeDeck = decks.getOrElse("Trade Deck", new Deck())
    tradeDeck.shuffle()

    explorerPile = decks.getOrElse("Explorer Pile", new Deck())

    notifyObservers()
  }

  def removeCardFrom(cards: List[Card], card: Card): List[Card] = {
    val (before, after) = cards.span(_ != card)
    before ++ after.drop(1)
  }

  def getPlayerDeck: Deck = playerDeck
  def getTradeDeck: Deck = tradeDeck
  def getExplorerPile: Deck = explorerPile
  def getDecks: Map[String, Deck] = {
    Map(
      "Personal Deck" -> playerDeck,
      "Trade Deck" -> tradeDeck,
      "Explorer Pile" -> explorerPile
    )
  }
  def getHand: List[Card] = hand
  def getTradeRow: List[Card] = tradeRow
  def getDiscardPile: List[Card] = discardPile

  def setDeck(newDeck: List[Card]): Unit = { deck = newDeck; notifyObservers() }
  def setHand(newHand: List[Card]): Unit = { hand = newHand; notifyObservers() }
  def setTradeRow(newTradeRow: List[Card]): Unit = { tradeRow = newTradeRow; notifyObservers() }
  def resetDiscardPile(): Unit = { discardPile = List(); notifyObservers() }


  def drawCards(count: Int): List[Card] = {
    val drawnCards = (1 to count).flatMap(_ => playerDeck.drawCard()).toList
    hand = hand ++ drawnCards
    notifyObservers()
    drawnCards
  }
  def replenishTradeRow(): Unit = {
    while (tradeRow.size < 5 && tradeDeck.getCards.nonEmpty) {
      tradeDeck.drawCard().foreach(card => tradeRow = tradeRow :+ card)
    }
    notifyObservers()
  }
  def undoReplenish(card: Card): Unit = {
    tradeRow = tradeRow.filterNot(_ => tradeDeck.getCards.contains(card))
    notifyObservers()
  }
  def drawCard(): Option[Card] = {
    val card = playerDeck.drawCard()
    card.foreach(c => hand = c :: hand)
    notifyObservers()
    card
  }
  def returnCardToPlayerDeck(card: Card): Unit = {
    playerDeck.addCard(card)
    hand = hand.filterNot(_ == card)
    discardPile = discardPile.filterNot(_ == card)
    notifyObservers()
  }
  def playCard(card: Card): Unit = {
    hand = removeCardFrom(hand, card)
    discardPile = card :: discardPile
    notifyObservers()
  }
  def returnCardToHand(card: Card): Unit = {
    discardPile = removeCardFrom(discardPile, card)
    hand = card :: hand
    notifyObservers()
  }
  def buyCard(card: Card): Unit = {
    if (tradeRow.contains(card)) {
      tradeRow = tradeRow.filterNot(_ == card)
      discardPile = card :: discardPile
      replenishTradeRow()
      notifyObservers()
    }
  }
  def returnCardToTradeRow(card: Card): Unit = {
    tradeRow = card :: tradeRow
    discardPile = discardPile.filterNot(_ == card)
    notifyObservers()
  }
  def endTurn(): Unit = {
    discardPile = hand ++ discardPile
    hand = List()
    drawCards(5)
    notifyObservers()
  }
  def undoEndTurn(): Unit = {
    hand = discardPile.take(5)
    discardPile = discardPile.drop(5)
    notifyObservers()
  }
  def resetGame(): Unit = {
    initializeDecks(decksByRole)
    hand = List()
    discardPile = List()
    tradeRow = List()
    drawCards(5)
    replenishTradeRow()
    notifyObservers()
  }

  def undoResetGame(): Unit = {
    // Logic to undo reset game
    notifyObservers()
  }

def getDeckState: String = {
  def cardLine(card: Card): String = {
    val name = card.cardName
    val faction = card.faction.factionName
    val typ = card.cardType.map(_.cardType).getOrElse("Unknown")
    val cost = card match {
      case c: FactionCard => c.cost.toString
      case _ => "-"
    }
    val ability = card.primaryAbility.map(_.actions.map(_.description).mkString(", ")).getOrElse("-")
    s"$name | $faction | $typ | Cost: $cost | Ability: $ability"
  }

  "PlayerDeck:\n" +
    playerDeck.getExpandedCards.map(cardLine).mkString("\n") + "\n" +
  "Hand:\n" +
    hand.zipWithIndex.map { case (card, idx) => s"${idx + 1}: ${cardLine(card)}" }.mkString("\n") + "\n" +
  "Discard Pile:\n" +
    discardPile.map(cardLine).mkString("\n") + "\n" +
  "TradeRow:\n" +
    tradeRow.map(cardLine).mkString("\n") + "\n" +
  "TradeDeck:\n" +
    tradeDeck.getExpandedCards.map(cardLine).mkString("\n") + "\n"
  }
}