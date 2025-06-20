package de.htwg.se.starrealms.controller.GameMediatorComponent.impl

import de.htwg.se.starrealms.model.GameStateComponent.GameStateInterface
import de.htwg.se.starrealms.controller.GameLogicComponent.GameLogicInterface
import de.htwg.se.starrealms.model.PlayerComponent.PlayerInterface
import de.htwg.se.starrealms.controller.GameMediatorComponent.GameMediator

import com.google.inject.Inject

class StarRealmsMediator @Inject() (
    val gameState: GameStateInterface,
    val gameLogic: GameLogicInterface,
    val players: List[PlayerInterface]
    ) extends GameMediator {

    override def notify(sender: Any, event: String): Unit = event match {
        case "endTurn" =>
            //gameLogic.saveState()
            gameState.swapPlayers
            //gameLogic.startTurn()

        case "playerSwitch" =>
            val newPlayer = players.find(_ != gameState.getCurrentPlayer).get
            gameState.setOpponent(gameState.getCurrentPlayer)
            gameState.setCurrentPlayer(newPlayer)

        case _ =>
            println(s"Unhandled event: $event")
    }
    override def getGameLogic: GameLogicInterface = gameLogic
    override def getGameState: GameStateInterface = gameState
    override def getCurrentPlayer: PlayerInterface = gameState.getCurrentPlayer

}