package de.htwg.se.starrealms.di

/* import de.htwg.util.CommandInterface
import de.htwg.se.starrealms.view._
import de.htwg.se.starrealms.controller.{GameLogicComponent, GameMediatorComponent, ControllerComponent}
import de.htwg.se.starrealms.controller.GameLogicComponent._
import de.htwg.se.starrealms.controller.GameMediatorComponent._
import de.htwg.se.starrealms.controller.ControllerComponent._
import de.htwg.se.starrealms.model.{GameCore, PlayerComponent, GameStateComponent}
import de.htwg.se.starrealms.model.GameCore._
import de.htwg.se.starrealms.model.GameStateComponent._
import de.htwg.se.starrealms.model.PlayerComponent._
import scalafx.scene.input.KeyCode.R */

object StarRealmsModule {

  //given CommandInterface = new ControllerComponent.structure.Command(using )
  given adapter: CommandAdapter = new CommandProcessorAdapter(using mediator, controller)
  //given renderer: Renderer[?] = new RDecorator[T]

  given controller: ControllerInterface = new ControllerComponent.impl.Controller(using mediator)
  given gameLogic: GameLogicInterface = new GameLogicComponent.impl.GameLogic(using gameState)
  given mediator: GameMediator = new GameMediatorComponent.impl.StarRealmsMediator(using gameState, gameLogic, players)


  //given ability: AbilityInterface = new GameCore.impl.Ability(using actions)
  //given action: ActionInterface = new GameCore.impl.Action()

  //given card: CardInterface = new GameCore.impl.Card(using )
  //given cardType: CardTypeInterface = new GameCore.impl.CardType(using )
  //given faction: FactionInterface = new GameCore.impl.Faction(using )
  //given editioin: EditionInterface = new GameCore.impl.Edition(using )

  //given deck: DeckInterface = new GameCore.impl.Deck(using )
  given director: DeckDirectorInterface = new GameCore.impl.DeckDirector()
  given builder: Builder = new impl.DeckBuilder(using product)

  given gameState: GameStateInterface = new GameStateComponent.impl.GameState(using decksByRole, player1, player2, builderFactory, director)
  given proxy: GameStateReadOnly = new ControllerComponent.structure.GameStateProxy(using gameState)

  //given player: PlayerComponent.PlayerInterface = new PlayerComponent.impl.Player(using )
  given players: List[PlayerComponent.PlayerInterface] = List(
    PlayerComponent.impl.Player("Player 1", 3),
    PlayerComponent.impl.Player("Player 2", 3)
  )

  // Example decksByRole (replace this with real card loading logic)
  given decksByRole: Map[String, DeckInterface] = Map(
    "Personal Deck" -> new impl.Deck(),
    "Trade Deck" -> new impl.Deck(),
    "Explorer Pile" -> new impl.Deck()
  )


}