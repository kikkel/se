package de.htwg.se.starrealms.controller.ControllerComponent.str

import de.htwg.util.Command
import de.htwg.se.starrealms.model.CardComponent.interface.Card
import de.htwg.se.starrealms.controller.GameMediatorComponent.interface.GameMediator

class ShowDeckCommand(mediator: GameMediator) extends Command {
  override def doMove: Unit = println(mediator.getGameState.getDeckState); override def undoMove: Unit = {} }
