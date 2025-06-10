package de.htwg.se.starrealms.controller.ControllerComponent.structure


import de.htwg.util.Command
import de.htwg.se.starrealms.model.GameCore.Card
class InvalidCommand(input: String) extends Command {
  override def doMove: Unit = println(s"Invalid command: $input"); override def undoMove: Unit = {} }

