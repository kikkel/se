package de.htwg.se.starrealms.controller.ControllerComponent.str

import de.htwg.util.Command
import de.htwg.se.starrealms.model.CardComponent.interface.Card

class ResetGameCommand(controller: Controller) extends Command {
  override def doMove: Unit = controller.gameLogic.resetGame(); override def undoMove: Unit = controller.gameLogic.undoResetGame() }
