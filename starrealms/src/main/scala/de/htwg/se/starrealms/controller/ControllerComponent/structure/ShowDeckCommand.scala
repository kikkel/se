package de.htwg.se.starrealms.controller.ControllerComponent.structure


import de.htwg.util.Command
import de.htwg.se.starrealms.model.CardComponent.Card
import de.htwg.se.starrealms.controller.GameMediatorComponent.GameMediator

class ShowDeckCommand(mediator: GameMediator) extends Command {
  override def doMove: Unit = println(mediator.getGameState.getDeckState); override def undoMove: Unit = {} }
