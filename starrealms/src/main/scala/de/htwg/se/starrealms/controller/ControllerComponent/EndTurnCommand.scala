package de.htwg.se.starrealms.controller.ControllerComponent

import de.htwg.util.Command
import de.htwg.se.starrealms.model.CardComponent.interface.Card

class EndTurnCommand(controller: Controller) extends Command {
  override def doMove: Unit = controller.gameLogic.endTurn(); override def undoMove: Unit = controller.gameLogic.undoEndTurn() }

