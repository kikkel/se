package de.htwg.se.starrealms.controller.ControllerComponent

import de.htwg.util.Command
import de.htwg.se.starrealms.model.Card

class ShowDeckCommand(controller: Controller) extends Command {
  override def doMove: Unit = println(controller.gameState.getDeckState); override def undoMove: Unit = {} }
