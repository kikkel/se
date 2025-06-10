package de.htwg.se.starrealms.controller.GameMediatorComponent

import de.htwg.se.starrealms.controller.GameLogicComponent.GameLogicInterface
import de.htwg.se.starrealms.model.GameStateComponent.GameStateInterface
import de.htwg.se.starrealms.model.PlayerComponent.PlayerInterface
trait GameMediator {
    def notify(sender: Any, event: String): Unit
    def getGameLogic: GameLogicInterface
    def getGameState: GameStateInterface
    def getCurrentPlayer: PlayerInterface
}