package de.htwg.se.starrealms.controller.ControllerComponent.structure


import de.htwg.util.Command
import de.htwg.se.starrealms.model.CardComponent.Card
import de.htwg.se.starrealms.controller.GameMediatorComponent.GameMediator

class ResetGameCommand(mediator: GameMediator) extends Command {
  override def doMove: Unit = mediator.getGameLogic.resetGame; override def undoMove: Unit = mediator.getGameLogic.undoResetGame }
