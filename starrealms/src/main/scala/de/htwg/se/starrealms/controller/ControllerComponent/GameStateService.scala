package de.htwg.se.starrealms.controller.ControllerComponent

import de.htwg.util._
import de.htwg.se.starrealms.model.PlayerComponent._
import de.htwg.se.starrealms.model.SetUpComponent.{Deck, Card, FactionCard}
import de.htwg.se.starrealms.controller.ControllerComponent._

class GameState(
  val decksByRole: Map[String, Deck],
  val player1: Player,
  val player2: Player
) extends Observable {
  private var currentPlayer: Player = player1
  private var opponent: Player = player2

  private var playerDecks: Map[Player, Deck] = Map()
  private var hands: Map[Player, List[Card]] = Map(player1 -> List(), player2 -> List())
  private var discardPiles: Map[Player, List[Card]] = Map(player1 -> List(), player2 -> List())
  private var lastDiscardedHands: Map[Player, List[Card]] = Map(player1 -> List(), player2 -> List())

  private var tradeRow: List[Card] = List()
  private var tradeDeck: Deck = new Deck()
  private var explorerPile = new Deck()

  initializeDecks(decksByRole)

  def initializeDecks(decks: Map[String, Deck]): Unit = {
    val allPersonal = decks.getOrElse("Personal Deck", new Deck()).getCards
    val expandedPersonal = allPersonal.flatMap { case (card, qty) => List.fill(qty)(card) }.toList
    val scouts = expandedPersonal.filter(_.cardName.trim.equalsIgnoreCase("Scout")).take(8)
    val vipers = expandedPersonal.filter(_.cardName.trim.equalsIgnoreCase("Viper")).take(2)
    val playerCards = scala.util.Random.shuffle(scouts ++ vipers)

    playerDecks = Map(
      player1 -> new Deck(),
      player2 -> new Deck()
    )
    playerDecks(player1).setName("Personal Deck 1")
    playerDecks(player2).setName("Personal Deck 2")
    playerDecks(player1).setCardStack(scala.util.Random.shuffle(playerCards))
    playerDecks(player2).setCardStack(scala.util.Random.shuffle(playerCards))

    val allTrade = decks.getOrElse("Trade Deck", new Deck()).getCards
    val expandedTrade = allTrade.flatMap { case (card, qty) => List.fill(qty)(card) }.toList
    val shuffledTrade = scala.util.Random.shuffle(expandedTrade)
    tradeDeck = new Deck()
    tradeDeck.setName("Trade Deck")
    tradeDeck.setCardStack(shuffledTrade)

    explorerPile = decks.getOrElse("Explorer Pile", new Deck())

    hands = Map(player1 -> List(), player2 -> List())
    discardPiles = Map(player1 -> List(), player2 -> List())
    lastDiscardedHands = Map(player1 -> List(), player2 -> List())

    notifyObservers()
  }

  def getCurrentPlayer: Player = currentPlayer
  def getOpponent: Player = opponent

  def getPlayerDeck(player: Player): Deck = playerDecks(player)
  def getHand(player: Player): List[Card] = hands(player)
  def getDiscardPile(player: Player): List[Card] = discardPiles(player)
  def getDiscardPiles: Map[Player, List[Card]] = discardPiles
  def getLastDiscardedHand(player: Player): List[Card] = lastDiscardedHands(player)

  def getTradeDeck: Deck = tradeDeck
  def getTradeRow: List[Card] = tradeRow
  def getExplorerPile: Deck = explorerPile

  def setCurrentPlayer(player: Player): Unit = {
    currentPlayer = player
  }

  def setOpponent(player: Player): Unit = {
    opponent = player
  }

  def swapPlayers(): Unit = {
    val oldCurrent = currentPlayer
    currentPlayer = opponent
    opponent = oldCurrent
    notifyObservers()
  }

  def setPlayerDeck(player: Player, deck: Deck): Unit = {
    playerDecks = playerDecks.updated(player, deck)
    notifyObservers()
  }

  def setHand(player: Player, hand: List[Card]): Unit = {
    hands = hands.updated(player, hand)
    notifyObservers()
  }

  def setDiscardPile(player: Player, discard: List[Card]): Unit = {
    discardPiles = discardPiles.updated(player, discard)
    notifyObservers()
  }
  def setLastDiscardedHand(player: Player, hand: List[Card]): Unit = {
    lastDiscardedHands = lastDiscardedHands.updated(player, hand)
    notifyObservers()
  }

  def setTradeRow(row: List[Card]): Unit = {
    tradeRow = row
    notifyObservers()
  }

  def setTradeDeck(deck: Deck): Unit = {
    tradeDeck = deck
    notifyObservers()
  }

  def setExplorerPile(deck: Deck): Unit = {
    explorerPile = deck
    notifyObservers()
  }

  def notifyStateChange(): Unit = {
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

    s"Active Player: $currentPlayer\nOpponent: $opponent\n" +
    "Hand:\n" +
      hands(currentPlayer).zipWithIndex.map { case (card, idx) => s"${idx + 1}: ${cardLine(card)}" }.mkString("\n") + "\n\n" +
    "Discard Pile:\n" +
      discardPiles(currentPlayer).map(cardLine).mkString("\n") + "\n\n" +
    "TradeRow:\n" +
      tradeRow.map(cardLine).mkString("\n") + "\n\n"
  }

  def checkGameOver(): Option[String] = {
    if (player1.health <= 0) Some(s"${player2.name} won!")
    else if (player2.health <= 0) Some(s"${player1.name} won!")
    else None
  }
}