package de.htwg.se.starrealms.model.GameStateComponent.impl

import de.htwg.util._
import de.htwg.se.starrealms.model.PlayerComponent.interface.PlayerInterface
import de.htwg.se.starrealms.model.DeckComponent.interface._
import de.htwg.se.starrealms.model.CardComponent.interface.Card
import de.htwg.se.starrealms.model.GameStateComponent.interface.GameStateInterface

import de.htwg.se.starrealms.model.CardComponent.impl.FactionCard


class GameState(
  val decksByRole: Map[String, DeckInterface],
  val player1: PlayerInterface,
  val player2: PlayerInterface,
  builderFactory: => Builder,
  director: DeckDirectorInterface
) extends Observable with GameStateInterface {
  private var currentPlayer: PlayerInterface = player1
  private var opponent: PlayerInterface = player2

  private var playerDecks: Map[PlayerInterface, DeckInterface] = Map()
  private var hands: Map[PlayerInterface, List[Card]] = Map(player1 -> List(), player2 -> List())
  private var discardPiles: Map[PlayerInterface, List[Card]] = Map(player1 -> List(), player2 -> List())
  private var lastDiscardedHands: Map[PlayerInterface, List[Card]] = Map(player1 -> List(), player2 -> List())

  private var tradeRow: List[Card] = List()
  private var tradeDeck: DeckInterface = director.constructEmptyDeck("Trade Deck", builderFactory)
  private var explorerPile: DeckInterface = director.constructEmptyDeck("Explorer Pile", builderFactory)

  initializeDecks(decksByRole)

  override def getDecksByRole = decksByRole

  override def initializeDecks(decks: Map[String, DeckInterface]): Unit = {
    val allPersonal = decks.getOrElse("Personal Deck", director.constructEmptyDeck("Personal Deck", builderFactory)).getCards
    val expandedPersonal = allPersonal.flatMap { case (card, qty) => List.fill(qty)(card) }.toList
    val scouts = expandedPersonal.filter(_.cardName.trim.equalsIgnoreCase("Scout")).take(8)
    val vipers = expandedPersonal.filter(_.cardName.trim.equalsIgnoreCase("Viper")).take(2)
    val playerCards = scala.util.Random.shuffle(scouts ++ vipers)

    playerDecks = Map(
      player1 -> director.constructCustomDeck("Personal Deck 1", builderFactory, scala.util.Random.shuffle(playerCards)),
      player2 -> director.constructCustomDeck("Personal Deck 2", builderFactory, scala.util.Random.shuffle(playerCards))
    )
    playerDecks(player1).setName("Personal Deck 1")
    playerDecks(player2).setName("Personal Deck 2")
    playerDecks(player1).setCardStack(scala.util.Random.shuffle(playerCards))
    playerDecks(player2).setCardStack(scala.util.Random.shuffle(playerCards))

    val allTrade = decks.getOrElse("Trade Deck", director.constructEmptyDeck("Trade Deck", builderFactory)).getCards
    val expandedTrade = allTrade.flatMap { case (card, qty) => List.fill(qty)(card) }.toList
    val shuffledTrade = scala.util.Random.shuffle(expandedTrade)
    tradeDeck = director.constructEmptyDeck("Trade Deck", builderFactory)
    tradeDeck.setName("Trade Deck")
    tradeDeck.setCardStack(shuffledTrade)

    explorerPile = decks.getOrElse("Explorer Pile", director.constructEmptyDeck("Explorer Pile", builderFactory))

    hands = Map(player1 -> List(), player2 -> List())
    discardPiles = Map(player1 -> List(), player2 -> List())
    lastDiscardedHands = Map(player1 -> List(), player2 -> List())

    notifyObservers()
  }

  override def getCurrentPlayer: PlayerInterface = currentPlayer
  override def getOpponent: PlayerInterface = opponent

  override def getPlayerDeck(player: PlayerInterface): DeckInterface = playerDecks(player)
  override def getHand(player: PlayerInterface): List[Card] = hands(player)
  override def getDiscardPile(player: PlayerInterface): List[Card] = discardPiles(player)
  override def getDiscardPiles: Map[PlayerInterface, List[Card]] = discardPiles
  override def getLastDiscardedHand(player: PlayerInterface): List[Card] = lastDiscardedHands(player)

  override def getTradeDeck: DeckInterface = tradeDeck
  override def getTradeRow: List[Card] = tradeRow
  override def getExplorerPile: DeckInterface = explorerPile

  override def setCurrentPlayer(player: PlayerInterface): Unit = {
    currentPlayer = player
  }

  override def setOpponent(player: PlayerInterface): Unit = {
    opponent = player
  }

  override def swapPlayers: Unit = {
    val oldCurrent = currentPlayer
    currentPlayer = opponent
    opponent = oldCurrent
    notifyObservers()
  }

  override def setPlayerDeck(player: PlayerInterface, deck: DeckInterface): Unit = {
    playerDecks = playerDecks.updated(player, deck)
    notifyObservers()
  }

  override def setHand(player: PlayerInterface, hand: List[Card]): Unit = {
    hands = hands.updated(player, hand)
    notifyObservers()
  }

  override def setDiscardPile(player: PlayerInterface, discard: List[Card]): Unit = {
    discardPiles = discardPiles.updated(player, discard)
    notifyObservers()
  }
  override def setLastDiscardedHand(player: PlayerInterface, hand: List[Card]): Unit = {
    lastDiscardedHands = lastDiscardedHands.updated(player, hand)
    notifyObservers()
  }

  override def setTradeRow(row: List[Card]): Unit = {
    tradeRow = row
    notifyObservers()
  }

  override def setTradeDeck(deck: DeckInterface): Unit = {
    tradeDeck = deck
    notifyObservers()
  }

  override def setExplorerPile(deck: DeckInterface): Unit = {
    explorerPile = deck
    notifyObservers()
  }

  override def notifyStateChange(): Unit = {
    notifyObservers()
  }

  override def getDeckState: String = {
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

  override def checkGameOver: Option[String] = {
    if (currentPlayer.getHealth <= 0) Some(s"${opponent.getName} won!")
    else if (opponent.getHealth <= 0) Some(s"${currentPlayer.getName} won!")
    else None
  }
}