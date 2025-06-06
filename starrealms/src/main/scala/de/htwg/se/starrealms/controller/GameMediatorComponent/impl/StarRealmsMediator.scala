package de.htwg.se.starrealms.controller.GameMediatorComponent.impl

import de.htwg.se.starrealms.model.GameStateComponent.interface.GameStateInterface
import de.htwg.se.starrealms.controller.GameLogicComponent.interface.GameLogicInterface
import de.htwg.se.starrealms.model.PlayerComponent.interface.PlayerInterface
import de.htwg.se.starrealms.controller.GameMediatorComponent.interface.GameMediator

class StarRealmsMediator(
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
}