package de.htwg.se.starrealms.controller.ControllerComponent

import de.htwg.util.Command
import de.htwg.se.starrealms.model.Card

class InvalidCommand(input: String) extends Command {
  override def doMove: Unit = println(s"Invalid command: $input"); override def undoMove: Unit = {} }

