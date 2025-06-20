package de.htwg.se.starrealms.di

import com.google.inject.{Inject, Provider, Singleton}
import de.htwg.se.starrealms.model.PlayerComponent.impl.Player
import de.htwg.se.starrealms.model.PlayerComponent.PlayerInterface
import de.htwg.se.starrealms.model.GameCore.Builder
import de.htwg.se.starrealms.model.GameCore.ActionInterface
import de.htwg.se.starrealms.model.GameCore.impl.Action
import de.htwg.se.starrealms.model.GameCore.DeckInterface
import de.htwg.se.starrealms.model.GameCore.impl.Deck

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
class DecksByRoleProvider extends Provider[Map[String, DeckInterface]] {
  override def get(): Map[String, DeckInterface] = Map(
    "Personal Deck" -> new Deck,
    "Trade Deck" -> new Deck,
    "Explorer Pile" -> new Deck
  )
}