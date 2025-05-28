package de.htwg.se.starrealms.model

import de.htwg.util._

class GameState(
  decksByRole: Map[String, Deck],
  val player1: Player,
  val player2: Player
) extends Observable {
  private var currentPlayer: Player = player1
  private var opponent: Player = player2

  // Jeder Spieler hat eigenes Deck, Hand, Discard
  private var playerDecks: Map[Player, Deck] = Map()
  private var hands: Map[Player, List[Card]] = Map(player1 -> List(), player2 -> List())
  private var discardPiles: Map[Player, List[Card]] = Map(player1 -> List(), player2 -> List())

  // Gemeinsame Bereiche
  private var tradeRow: List[Card] = List()
  private var tradeDeck: Deck = new Deck()
  private var explorerPile = new Deck()

  initializeDecks(decksByRole)

  private def initializeDecks(decks: Map[String, Deck]): Unit = {
    // Personal Deck vorbereiten (für beide Spieler)
    val allPersonal = decks.getOrElse("Personal Deck", new Deck()).getCards
    val expandedPersonal = allPersonal.flatMap { case (card, qty) => List.fill(qty)(card) }.toList
    val scouts = expandedPersonal.filter(_.cardName.trim.equalsIgnoreCase("Scout")).take(8)
    val vipers = expandedPersonal.filter(_.cardName.trim.equalsIgnoreCase("Viper")).take(2)
    val playerCards = scala.util.Random.shuffle(scouts ++ vipers)

    // Jeder Spieler bekommt ein eigenes Deck
    playerDecks = Map(
      player1 -> new Deck(),
      player2 -> new Deck()
    )
    playerDecks(player1).setName("Personal Deck 1")
    playerDecks(player2).setName("Personal Deck 2")
    playerDecks(player1).setCardStack(scala.util.Random.shuffle(playerCards))
    playerDecks(player2).setCardStack(scala.util.Random.shuffle(playerCards))

    // Trade Deck vorbereiten
    val allTrade = decks.getOrElse("Trade Deck", new Deck()).getCards
    val expandedTrade = allTrade.flatMap { case (card, qty) => List.fill(qty)(card) }.toList
    val shuffledTrade = scala.util.Random.shuffle(expandedTrade)
    tradeDeck = new Deck()
    tradeDeck.setName("Trade Deck")
    tradeDeck.setCardStack(shuffledTrade)

    explorerPile = decks.getOrElse("Explorer Pile", new Deck())

    // Hände und Discard leeren
    hands = Map(player1 -> List(), player2 -> List())
    discardPiles = Map(player1 -> List(), player2 -> List())

    notifyObservers()
  }

  def getCurrentPlayer: Player = currentPlayer
  def getOpponent: Player = opponent

  // Zugriff auf Deck, Hand, Discard pro Spieler
  def getPlayerDeck(player: Player): Deck = playerDecks(player)
  def getHand(player: Player): List[Card] = hands(player)
  def getDiscardPile(player: Player): List[Card] = discardPiles(player)

  def getTradeDeck: Deck = tradeDeck
  def getTradeRow: List[Card] = tradeRow
  def getExplorerPile: Deck = explorerPile

  // Karten ziehen für aktuellen Spieler
  def drawCards(count: Int): List[Card] = {
    val deck = playerDecks(currentPlayer)
    val drawn = (1 to count).flatMap(_ => deck.drawCard()).toList
    hands = hands.updated(currentPlayer, hands(currentPlayer) ++ drawn)
    notifyObservers()
    drawn
  }

  // Karte aus Hand spielen (vom aktuellen Spieler)
  def playCard(card: Card): Unit = {
    if (hands(currentPlayer).contains(card)) {
      hands = hands.updated(currentPlayer, hands(currentPlayer).filterNot(_ == card))
      discardPiles = discardPiles.updated(currentPlayer, card :: discardPiles(currentPlayer))
      notifyObservers()
    }
  }

  // Karte kaufen (vom aktuellen Spieler)
  def buyCard(card: Card): Unit = {
    if (tradeRow.contains(card)) {
      tradeRow = tradeRow.filterNot(_ == card)
      discardPiles = discardPiles.updated(currentPlayer, card :: discardPiles(currentPlayer))
      notifyObservers()
    }
  }

  // Trade Row auffüllen (gemeinsam)
  def replenishTradeRow(): Unit = {
    while (tradeRow.size < 5 && tradeDeck.getCards.nonEmpty) {
      tradeDeck.drawCard().foreach(card => tradeRow = tradeRow :+ card)
    }
    notifyObservers()
  }

  // Karte zurück ins Deck des aktuellen Spielers (für Undo)
  def returnCardToPlayerDeck(card: Card): Unit = {
    playerDecks(currentPlayer).addCard(card)
    notifyObservers()
  }

  // Karte zurück auf die Hand des aktuellen Spielers (für Undo)
  def returnCardToHand(card: Card): Unit = {
    hands = hands.updated(currentPlayer, card :: hands(currentPlayer))
    notifyObservers()
  }

  // Karte zurück in die Trade Row (für Undo)
  def returnCardToTradeRow(card: Card): Unit = {
    tradeRow = card :: tradeRow
    notifyObservers()
  }

  // Trade Row Änderung rückgängig machen (für Undo)
  def undoReplenish(card: Card): Unit = {
    tradeRow = tradeRow.filterNot(_ == card)
    tradeDeck.addCard(card)
    notifyObservers()
  }

  // Einen Zug rückgängig machen (für Undo)
  def undoEndTurn(): Unit = {
  }

  // Spiel zurücksetzen
  def resetGame(): Unit = {
    initializeDecks(decksByRole)
    notifyObservers()
  }

  def undoResetGame(): Unit = {
    // Optional: Vorherigen Zustand wiederherstellen, falls du das möchtest
  }

  // Eine Karte ziehen (vom aktuellen Spieler)
  def drawCard(): Option[Card] = {
    val deck = playerDecks(currentPlayer)
    val cardOpt = deck.drawCard()
    cardOpt.foreach { card =>
      hands = hands.updated(currentPlayer, hands(currentPlayer) :+ card)
    }
    notifyObservers()
    cardOpt
  }

  // Zug beenden: Hand auf Ablagestapel, neue Karten ziehen, Spieler wechseln
  def endTurn(): Unit = {
    discardPiles = discardPiles.updated(currentPlayer, hands(currentPlayer) ++ discardPiles(currentPlayer))
    hands = hands.updated(currentPlayer, List())
    val tmp = currentPlayer
    currentPlayer = opponent
    opponent = tmp
    drawCards(5)
    notifyObservers()
  }

  // Schaden an Gegner
  def dealDamageToOpponent(amount: Int): Unit = {
    opponent.takeDamage(amount)
    notifyObservers()
  }

  // Beispiel für DeckState-Ausgabe
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

    s"Active Player: $currentPlayer\nOpponent: $opponent\n" +
    "Hand:\n" +
      hands(currentPlayer).zipWithIndex.map { case (card, idx) => s"${idx + 1}: ${cardLine(card)}" }.mkString("\n") + "\n" +
    "Discard Pile:\n" +
      discardPiles(currentPlayer).map(cardLine).mkString("\n") + "\n" +
    "TradeRow:\n" +
      tradeRow.map(cardLine).mkString("\n") + "\n"
  }
}