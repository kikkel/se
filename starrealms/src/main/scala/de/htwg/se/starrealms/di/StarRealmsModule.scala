package de.htwg.se.starrealms.di

import com.google.inject.{AbstractModule, Provider, Singleton}
import net.codingwell.scalaguice.ScalaModule

import de.htwg.util.CommandInterface
import de.htwg.se.starrealms.view._
import de.htwg.se.starrealms.controller.{GameLogicComponent, GameMediatorComponent, ControllerComponent}
import de.htwg.se.starrealms.controller.GameLogicComponent._
import de.htwg.se.starrealms.controller.GameMediatorComponent._
import de.htwg.se.starrealms.controller.ControllerComponent._
import de.htwg.se.starrealms.model.{GameCore, PlayerComponent, GameStateComponent}
import de.htwg.se.starrealms.model.GameCore._
import de.htwg.se.starrealms.model.GameStateComponent._
import de.htwg.se.starrealms.model.PlayerComponent._
import com.google.inject.TypeLiteral

class StarRealmsModule extends AbstractModule with ScalaModule {
  override def configure(): Unit = {

    bind(classOf[GraphicUI]).toProvider(classOf[GraphicUIProvider])
    bind(classOf[ConsoleView])

    bind(classOf[CommandAdapter]).to(classOf[CommandProcessorAdapter])

    bind(classOf[ControllerInterface]).to(classOf[ControllerComponent.impl.Controller])
    bind(classOf[GameLogicInterface]).to(classOf[GameLogicComponent.impl.GameLogic])
    bind(classOf[GameMediator]).to(classOf[GameMediatorComponent.impl.StarRealmsMediator])

    bind(classOf[AbilityInterface]).to(classOf[GameCore.impl.Ability])

    bind(classOf[DeckInterface]).to(classOf[GameCore.impl.Deck])
    bind(classOf[DeckDirectorInterface]).to(classOf[GameCore.impl.DeckDirector])
    bind(classOf[Builder]).to(classOf[GameCore.impl.DeckBuilder])

    // GameState und GameStateReadOnly als Singleton über denselben Provider


    // Zentrale GameState-Instanz
    bind(classOf[GameStateInterface]).toProvider(classOf[GameStateProvider]).in(classOf[Singleton])

    // Interfaces zeigen auf dieselbe Instanz
    bind(classOf[GameStateInterface]).to(classOf[GameStateInterface])
    bind(classOf[GameStateReadOnly]).to(classOf[GameStateInterface])



    // Spieler
    bind(classOf[PlayerInterface]).toInstance(new PlayerComponent.impl.Player(name = "Player 1", health = 100))
    bind(new TypeLiteral[List[PlayerInterface]]{}).toProvider(classOf[PlayersProvider])

    // BuilderFactory
    bind(new TypeLiteral[Function0[Builder]]{}).toProvider(classOf[BuilderFactoryProvider])

    // Actions
    bind(new TypeLiteral[List[ActionInterface]]{}).toProvider(classOf[ActionsProvider])

    // Decks nach Rolle
    bind(new TypeLiteral[Map[String, DeckInterface]]{}).toProvider(classOf[DecksByRoleProvider])

    // Renderer
    bind(new TypeLiteral[RDecorator[CardInterface]]{}).to(classOf[CardRDecorator])
    bind(new TypeLiteral[Renderer[CardInterface]]{}).to(classOf[CardRDecorator])
  }
}