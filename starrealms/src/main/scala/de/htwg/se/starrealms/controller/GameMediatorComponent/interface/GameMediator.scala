package de.htwg.se.starrealms.controller.GameMediatorComponent.interface

import de.htwg.se.starrealms.controller.GameLogicComponent.interface.GameLogicInterface
import de.htwg.se.starrealms.model.GameStateComponent.interface.GameStateInterface
import de.htwg.se.starrealms.model.PlayerComponent.interface.PlayerInterface
trait GameMediator {
    def notify(sender: Any, event: String): Unit
    def getGameLogic: GameLogicInterface
    def getGameState: GameStateInterface
    def getCurrentPlayer: PlayerInterface
}