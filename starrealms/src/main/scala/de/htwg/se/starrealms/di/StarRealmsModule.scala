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

class StarRealmsModule extends AbstractModule with ScalaModule{
  override def configure(): Unit = {

    bind(classOf[CommandInterface]).to(classOf[ControllerComponent.structure.Command])
    bind(classOf[CommandAdapter]).to(classOf[CommandProcessorAdapter])
    bind(classOf[Renderer[CardInterface]]).to(classOf[CardRenderer])
    bind(classOf[Renderer[?]]).to(classOf[RDecorator[?]])

    bind(classOf[ControllerInterface]).to(classOf[ControllerComponent.impl.Controller])
    bind(classOf[GameLogicInterface]).to(classOf[GameLogicComponent.impl.GameLogic])
    bind(classOf[GameMediator]).to(classOf[GameMediatorComponent.impl.StarRealmsMediator])


    bind(classOf[AbilityInterface]).to(classOf[GameCore.impl.Ability])
    bind(classOf[ActionInterface]).to(classOf[GameCore.impl.Action])

    bind(classOf[CardInterface]).to(classOf[GameCore.impl.Card])
    bind(classOf[CardTypeInterface]).to(classOf[GameCore.impl.CardType])
    bind(classOf[FactionInterface]).to(classOf[GameCore.impl.Faction])
    bind(classOf[EditionInterface]).to(classOf[GameCore.impl.Edition])

    bind(classOf[DeckInterface]).to(classOf[GameCore.impl.Deck])
    bind(classOf[DeckDirectorInterface]).to(classOf[GameCore.impl.DeckDirector])
    bind(classOf[Builder]).to(classOf[GameCore.impl.DeckBuilder])

    bind(classOf[GameStateInterface]).to(classOf[GameStateComponent.impl.GameState])
    bind(classOf[GameStateReadOnly]).to(classOf[ControllerComponent.structure.GameStateProxy])

    bind(classOf[PlayerComponent.PlayerInterface]).to(classOf[PlayerComponent.impl.Player])


  }
}