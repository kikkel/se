package de.htwg.se.starrealms.controller.ControllerComponent.structure


import de.htwg.util.Command
import de.htwg.se.starrealms.model.GameCore.Card
import de.htwg.se.starrealms.controller.GameMediatorComponent.GameMediator

class EndTurnCommand(mediator: GameMediator) extends Command {
  override def doMove: Unit = mediator.getGameLogic.endTurn; override def undoMove: Unit = mediator.getGameLogic.undoEndTurn }

