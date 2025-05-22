package de.htwg.se.starrealms.model

import de.htwg.util._

class GameState extends Observable {
  val coreSet: Set = Set("Core Set")
  val unaligned: Faction = Faction("Unaligned")
  val blob: Faction = Faction("Blob")
  val federation: Faction = Faction("Trade Federation")

  val scout: Card = new DefaultCard(
    set = coreSet,
    cardName = "Scout",
    primaryAbility = Some(new Ability(List(SimpleAction("Gain 1 Trade")))),
    faction = unaligned,
    cardType = scala.util.Success(new Ship()),
    qty = 1,
    role = "Personal Deck"
  )
  val viper: Card = new DefaultCard(
    set = coreSet,
    cardName = "Viper",
    primaryAbility = Some(new Ability(List(SimpleAction("Gain 1 Combat")))),
    faction = unaligned,
    cardType = scala.util.Success(new Ship()),
    qty = 1,
    role = "Personal Deck"
  )

  val playerDeck: Deck = {
    val deck = new Deck()
    deck.setName("Player Deck")
    deck.setCards(scala.util.Random.shuffle(List.fill(8)(scout) ++ List.fill(2)(viper)))
    deck
  }

  val tradeDeck: Deck = {
    val deck = new Deck()
    deck.setName("Trade Deck")
    val base1 = new FactionCard(
      set = coreSet,
      cardName = "Blob Wheel",
      cost = 1,
      primaryAbility = Some(new Ability(List(SimpleAction("Gain 1 Trade")))),
      allyAbility = None,
      scrapAbility = None,
      faction = blob,
      cardType = scala.util.Success(new Base("5", false)),
      qty = 1,
      role = "Trade Deck"
    )
    val ship1 = new FactionCard(
      set = coreSet,
      cardName = "Federation Shuttle",
      cost = 1,
      primaryAbility = Some(new Ability(List(SimpleAction("Gain 2 Trade")))),
      allyAbility = None,
      scrapAbility = None,
      faction = federation,
      cardType = scala.util.Success(new Ship()),
      qty = 1,
      role = "Trade Deck"
    )
    
    val base2 = new FactionCard(
      set = coreSet,
      cardName = "Blob World",
      cost = 3,
      primaryAbility = Some(new Ability(List(SimpleAction("Gain 3 Trade")))),
      allyAbility = None,
      scrapAbility = None,
      faction = blob,
      cardType = scala.util.Success(new Base("7", false)),
      qty = 1,
      role = "Trade Deck"
    )
    val ship2 = new FactionCard(
      set = coreSet,
      cardName = "Imperial Frigate",
      cost = 2,
      primaryAbility = Some(new Ability(List(SimpleAction("Gain 4 Combat")))),
      allyAbility = None,
      scrapAbility = None,
      faction = federation,
      cardType = scala.util.Success(new Ship()),
      qty = 1,
      role = "Trade Deck"
    )
    val ship3 = new FactionCard(
      set = coreSet,
      cardName = "Blob Fighter",
      cost = 1,
      primaryAbility = Some(new Ability(List(SimpleAction("Gain 3 Combat")))),
      allyAbility = None,
      scrapAbility = None,
      faction = blob,
      cardType = scala.util.Success(new Ship()),
      qty = 1,
      role = "Trade Deck"
    )
    deck.setCards(List(base1, ship1, base2, ship2, ship3))
    deck.shuffle()
    deck
  }

  private var hand: List[Card] = List()
  private var discardPile: List[Card] = List()
  private var tradeRow: List[Card] = List()
  def removeCard(cards: List[Card], card: Card): List[Card] = {
    val (before, after) = cards.span(_ != card)
    before ++ after.drop(1) 
  }

  def initTradeRow(): Unit = {
    tradeRow = (1 to 5).flatMap(_ => tradeDeck.drawCard()).toList
    notifyObservers()
  }

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

  def drawCard(): Option[Card] = {
    val card = playerDeck.drawCard()
    card.foreach(c => hand = c :: hand)
    notifyObservers()
    card
  }

  def playCard(card: Card): Unit = {
    hand = removeCard(hand, card)
    discardPile = card :: discardPile
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

  def returnCardToPlayerDeck(card: Card): Unit = {
    playerDeck.addCard(card)
    hand = hand.filterNot(_ == card)
    discardPile = discardPile.filterNot(_ == card)
    notifyObservers()
  }
  def returnCardToHand(card: Card): Unit = {
    discardPile = removeCard(discardPile, card)
    hand = card :: hand
    notifyObservers()
  }
  def undoReplenish(card: Card): Unit = {
    tradeRow = tradeRow.filterNot(_ => tradeDeck.getCards.contains(card))
    notifyObservers()
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
    playerDeck.setCards(scala.util.Random.shuffle(List.fill(8)(scout) ++ List.fill(2)(viper)))
    playerDeck.shuffle()
    tradeDeck.setCards(List(

      new FactionCard(
        set = coreSet,
        cardName = "Blob Wheel",
        cost = 1,
        primaryAbility = Some(new Ability(List(SimpleAction("Gain 1 Trade")))),
        allyAbility = None,
        scrapAbility = None,
        faction = blob,
        cardType = scala.util.Success(new Base("5", false)),
        qty = 1,
        role = "Trade Deck"
      ),
      new FactionCard(
        set = coreSet,
        cardName = "Federation Shuttle",
        cost = 1,
        primaryAbility = Some(new Ability(List(SimpleAction("Gain 2 Trade")))),
        allyAbility = None,
        scrapAbility = None,
        faction = federation,
        cardType = scala.util.Success(new Ship()),
        qty = 1,
        role = "Trade Deck"
      )
    ))
    tradeDeck.shuffle()
    hand = List()
    discardPile = List()
    tradeRow = List()
    initTradeRow()
    notifyObservers()
  }
  def undoResetGame(): Unit = {
    notifyObservers()
  }

  def getHand: List[Card] = hand
  def getTradeRow: List[Card] = tradeRow

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

    "PlayerDeck:\n" + playerDeck.getCards.map(cardLine).mkString("\n") + "\n\n\n" +
    "Hand:\n" + hand.zipWithIndex.map { case (card, idx) => s"${idx + 1}: ${cardLine(card)}" }.mkString("\n") + "\n\n\n" +
    "Discard Pile:\n" + discardPile.map(cardLine).mkString("\n") + "\n\n\n" +
    "TradeRow:\n" + tradeRow.map(cardLine).mkString("\n") + "\n\n\n" +
    "TradeDeck:\n" + tradeDeck.getCards.map(cardLine).mkString("\n") + "\n\n\n"
  }

}