package de.htwg.se.starrealms.model.GameStateComponent
import de.htwg.se.starrealms.model.GameCore.CardInterface

case class GameSnapshot(
  currentPlayer: PlayerSnapshot,
  opponent: PlayerSnapshot,
  tradeRow: List[CardInterface],
  tradeDeck: List[CardInterface],
  explorerCount: Int,
  tradeDeckCount: Int
)

case class PlayerSnapshot(
  name: String,
  health: Int,
  hand: List[CardInterface],
  discardPile: List[CardInterface],
  playerDeck: List[CardInterface]
)