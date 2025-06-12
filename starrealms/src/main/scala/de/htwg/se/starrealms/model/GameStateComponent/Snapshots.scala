package de.htwg.se.starrealms.model.GameStateComponent
import de.htwg.se.starrealms.model.GameCore.Card

case class GameSnapshot(
  currentPlayer: PlayerSnapshot,
  opponent: PlayerSnapshot,
  tradeRow: List[Card],
  tradeDeck: List[Card],
  explorerCount: Int,
  tradeDeckCount: Int
)

case class PlayerSnapshot(
  name: String,
  health: Int,
  hand: List[Card],
  discardPile: List[Card],
  playerDeck: List[Card]
)