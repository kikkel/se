package de.htwg.se.starrealms.di

import com.google.inject.AbstractModule
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
import scalafx.scene.input.KeyCode.R
import com.google.inject.Provider
import com.google.inject.TypeLiteral
import scalafx.scene.input.KeyCode.G

class StarRealmsModule extends AbstractModule with ScalaModule{
  override def configure(): Unit = {

    bind(classOf[GraphicUI]).toProvider(classOf[GraphicUIProvider])
    bind(classOf[ConsoleView])

    //bind(classOf[CommandInterface]).to(classOf[ControllerComponent.structure.Command])
    bind(classOf[CommandAdapter]).to(classOf[CommandProcessorAdapter])
    //bind(classOf[Renderer[?]]).to(classOf[RDecorator[?]])

    bind(classOf[ControllerInterface]).to(classOf[ControllerComponent.impl.Controller])
    bind(classOf[GameLogicInterface]).to(classOf[GameLogicComponent.impl.GameLogic])
    bind(classOf[GameMediator]).to(classOf[GameMediatorComponent.impl.StarRealmsMediator])


    bind(classOf[AbilityInterface]).to(classOf[GameCore.impl.Ability])

    //Keine Bindings für abstrakte Klassen nötig??
    //bind(classOf[CardInterface]).to(classOf[GameCore.impl.Card])
    //bind(classOf[CardTypeInterface]).to(classOf[GameCore.impl.CardType])
    //bind(classOf[FactionInterface]).to(classOf[GameCore.impl.Faction])
    //bind(classOf[EditionInterface]).to(classOf[GameCore.impl.Edition])

    bind(classOf[DeckInterface]).to(classOf[GameCore.impl.Deck])
    bind(classOf[DeckDirectorInterface]).to(classOf[GameCore.impl.DeckDirector])
    bind(classOf[Builder]).to(classOf[GameCore.impl.DeckBuilder])

    bind(classOf[GameStateInterface]).to(classOf[GameStateComponent.impl.GameState])
    bind(classOf[GameStateReadOnly]).to(classOf[ControllerComponent.structure.GameStateProxy])

    bind(classOf[PlayerInterface]).toProvider(classOf[PlayerProvider])
    bind(new TypeLiteral[List[PlayerInterface]]{}).toProvider(classOf[PlayersProvider])
    bind(new TypeLiteral[Function0[Builder]]{}).toProvider(classOf[BuilderFactoryProvider])
    bind(new TypeLiteral[List[ActionInterface]]{}).toProvider(classOf[ActionsProvider])
    bind(new TypeLiteral[Map[String, DeckInterface]]{}).toProvider(classOf[DecksByRoleProvider])
    bind(new TypeLiteral[RDecorator[CardInterface]]{}).to(classOf[CardRDecorator])
    bind(new TypeLiteral[Renderer[CardInterface]]{}).to(classOf[CardRDecorator])


  }
}