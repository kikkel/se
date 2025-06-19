package de.htwg.se.starrealms.model.GameStateComponent
import de.htwg.se.starrealms.model.GameCore.CardInterface

import com.google.inject.Inject

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

trait SnapshotFactory {
  def createSnapshot: GameSnapshot
}

class SnapshotFactoryImpl @Inject() (
  gameState: GameStateInterface
) extends SnapshotFactory {
  override def createSnapshot: GameSnapshot = {
    gameState.getSnapshot
  }
}
  