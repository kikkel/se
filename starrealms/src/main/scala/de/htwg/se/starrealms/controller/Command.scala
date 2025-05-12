package de.htwg.se.starrealms.controller

import de.htwg.se.starrealms.model._
import de.htwg.util.Observable

trait Command {
  def execute(): String
}

class DrawCardCommand(controller: Controller, cardType: String) extends Command {
  override def execute(): String = controller.drawCard(cardType)
}

class ResetGameCommand(controller: Controller) extends Command {
  override def execute(): String = {
    controller.resetGame()
    controller.resetDeck()
    "Game and deck have been reset."
  }
}

class ShowDeckCommand(controller: Controller) extends Command {
  //override def execute(): String = controller.getDeckState
}

class InvalidCommand(input: String) extends Command {
  override def execute(): String = s"Unknown command: $input"
}