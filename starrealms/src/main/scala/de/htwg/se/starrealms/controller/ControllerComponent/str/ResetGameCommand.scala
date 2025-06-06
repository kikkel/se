package de.htwg.se.starrealms.controller.ControllerComponent.str

import de.htwg.util.Command
import de.htwg.se.starrealms.model.CardComponent.interface.Card
import de.htwg.se.starrealms.controller.GameMediatorComponent.interface.GameMediator

class ResetGameCommand(mediator: GameMediator) extends Command {
  override def doMove: Unit = mediator.getGameLogic.resetGame; override def undoMove: Unit = mediator.getGameLogic.undoResetGame }
