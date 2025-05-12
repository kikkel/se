package de.htwg.se.starrealms.controller

import de.htwg.se.starrealms.model._
import de.htwg.util.Observable

trait Command { def execute(): String }

class DrawCardCommand(gameLogic: GameLogic, cardType: String) extends Command {
  override def execute(): String = gameLogic.drawCard() match {
    case Some(c) if c.cardName == cardType => s"Drew $cardType: ${c.render()}"
    case Some(_) => s"Wrong catd drawn. #DrawCardCommand"
    case None => s"No $cardType cards left. #DrawCardCommand" 
  }
}

class ResetGameCommand(gameLogic: GameLogic) extends Command {
  override def execute(): String = {
    gameLogic.resetGame()
    "Game and deck have been reset."
  }
}

class ShowDeckCommand(controller: Controller) extends Command {
  override def execute(): String = {
    controller.getState
    "Deck shown."
  }
}

class InvalidCommand(input: String) extends Command {
  override def execute(): String = s"Invalid command: $input"
}