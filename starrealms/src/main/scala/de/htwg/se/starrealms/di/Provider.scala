package de.htwg.se.starrealms.di

import com.google.inject.{Inject, Provider, Singleton}
import de.htwg.se.starrealms.model.PlayerComponent.impl.Player
import de.htwg.se.starrealms.model.PlayerComponent.PlayerInterface
import de.htwg.se.starrealms.model.GameCore.Builder
import de.htwg.se.starrealms.model.GameCore.ActionInterface
import de.htwg.se.starrealms.model.GameCore.DeckInterface
import de.htwg.se.starrealms.model.GameCore.impl.Deck
import de.htwg.se.starrealms.model.GameCore.impl.{LoadCards, CardCSVLoader, DeckDirector}
import de.htwg.se.starrealms.model.GameCore.{Builder, DeckDirectorInterface, DeckInterface}
import de.htwg.se.starrealms.view.GraphicUI
import de.htwg.se.starrealms.view.CommandAdapter
import de.htwg.se.starrealms.model.GameStateComponent.GameStateReadOnly
import scala.jdk.CollectionConverters._
import de.htwg.se.starrealms.model.GameStateComponent.impl.GameState

@Singleton
class GraphicUIProvider @Inject() (
  processor: CommandAdapter,
  readOnlyState: GameStateReadOnly
) extends Provider[GraphicUI] {

  override def get(): GraphicUI = new GraphicUI(
    processor,
    readOnlyState,
    () => System.exit(0)
  )
}


@Singleton
class PlayersProvider extends Provider[List[PlayerInterface]] {
  override def get(): List[PlayerInterface] = List(
    Player("Player 1", 100),
    Player("Player 2", 100)
  )
}

@Singleton
class BuilderFactoryProvider @Inject() (builder: Builder) extends Provider[() => Builder] {
  override def get(): () => Builder = () => builder
}

@Singleton
class ActionsProvider extends Provider[List[ActionInterface]] {
  override def get(): List[ActionInterface] = List() // oder mit Standard-Actions füllen
}

@Singleton
class DecksByRoleProvider @Inject() (
  builder: Builder,
  director: DeckDirectorInterface
) extends Provider[Map[String, DeckInterface]] {
  override def get(): Map[String, DeckInterface] = {
    val ki_filePath: String = sys.env.getOrElse("CARDS_CSV_PATH", "/Users/koeseazra/SE-uebungen/se/starrealms/src/main/resources/PlayableSets.csv")
    val csvLoader = new CardCSVLoader(ki_filePath)
    val loadCards = new LoadCards(builder, director, csvLoader)
    loadCards.load("Core Set")
  }
}

/* @Singleton
class PlayerProvider @Inject() (name: String, health: Int) extends Provider[PlayerInterface] {
  override def get(): PlayerInterface = {
    Player(name, health)
  }
} */

@Singleton
class GameStateProvider @Inject() (
  decksByRole: Map[String, DeckInterface],
  players: List[PlayerInterface],
  builderFactory: () => Builder,
  director: DeckDirectorInterface
) extends Provider[GameState] {

  // cache variable
  private lazy val instance: GameState = new GameState(
    decksByRole = decksByRole,
    players = players,
    builderFactory = builderFactory(),
    director = director
  )

  override def get(): GameState = instance
}