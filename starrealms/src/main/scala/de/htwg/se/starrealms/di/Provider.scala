package de.htwg.se.starrealms.di

import com.google.inject.{Inject, Provider, Singleton}
import de.htwg.se.starrealms.model.PlayerComponent.impl.Player
import de.htwg.se.starrealms.model.PlayerComponent.PlayerInterface
import de.htwg.se.starrealms.model.GameCore.Builder
import de.htwg.se.starrealms.model.GameCore.ActionInterface
import de.htwg.se.starrealms.model.GameCore.DeckInterface
import de.htwg.se.starrealms.model.GameCore.impl.Deck
import de.htwg.se.starrealms.model.GameCore.DeckDirectorInterface
import de.htwg.se.starrealms.model.FileIOComponent.FileIOInterface
import de.htwg.se.starrealms.model.FileIOComponent.FileIOJsonimpl.FileIOJson
import de.htwg.se.starrealms.model.FileIOComponent.FileIOXMLimpl.FileIOXML

import de.htwg.se.starrealms.view.GraphicUI
import de.htwg.se.starrealms.view.CommandAdapter
import de.htwg.se.starrealms.model.GameStateComponent.GameStateReadOnly





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
  director: DeckDirectorInterface,
  config: StarRealmsConfig
) extends Provider[Map[String, DeckInterface]] {
  override def get(): Map[String, DeckInterface] = {
    val loader = new de.htwg.se.starrealms.model.GameCore.impl.CardCSVLoader(config.cardCsvPath)
    val loadCards = new de.htwg.se.starrealms.model.GameCore.impl.LoadCards(builder, director, loader)
    val decks = loadCards.load("Core Set")
    println("DEBUG: DecksByRoleProvider loaded decks: " + decks.map { case (k, v) => s"$k -> ${v.getCardStack.size}" })
    decks
  }
}


/* @Singleton
class PlayerProvider @Inject() (name: String, health: Int) extends Provider[PlayerInterface] {
  override def get(): PlayerInterface = {
    Player(name, health)
  }
} */