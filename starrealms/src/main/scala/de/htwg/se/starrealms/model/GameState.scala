package de.htwg.se.starrealms.model

import de.htwg.util._

class GameState extends Observable {
  private var deck: List[Card] = List()
  private var hand: List[Card] = List()
  private var tradeRow: List[Card] = List()
  private var discardPile: List[Card] = List()
  private var playerDeck: Deck = new Deck()
  private var tradeDeck: Deck = new Deck()

  def removeCardFrom(cards: List[Card], card: Card): List[Card] = {
    val (before, after) = cards.span(_ != card)
    before ++ after.drop(1) 
  }

  def loadDecks(setName: String): Unit = {
    val decksByRole = LoadCards.loadFromResource(LoadCards.getCsvPath, setName)

    decksByRole.get("TradeDeck").foreach { deck => tradeDeck = deck }
    decksByRole.get("PlayerDeck").foreach { deck => playerDeck = deck; this.deck = deck.getCards }
    notifyObservers()
  }
  
  def getDeck: List[Card] = deck
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
    playerDeck.resetDeck()
    tradeDeck.resetDeck()
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

    "PlayerDeck:\n" + playerDeck.getCards.map(cardLine).mkString("\n") + "\n" +
    "Hand:\n" + hand.zipWithIndex.map { case (card, idx) => s"${idx + 1}: ${cardLine(card)}" }.mkString("\n") + "\n" +
    "Discard Pile:\n" + discardPile.map(cardLine).mkString("\n") + "\n" +
    "TradeRow:\n" + tradeRow.map(cardLine).mkString("\n") + "\n" +
    "TradeDeck:\n" + tradeDeck.getCards.map(cardLine).mkString("\n") + "\n"
  }
}