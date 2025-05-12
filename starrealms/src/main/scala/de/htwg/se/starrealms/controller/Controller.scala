package de.htwg.se.starrealms.controller

import de.htwg.se.starrealms.model._
import de.htwg.util.Observable

class Controller(gameLogic: GameLogic) extends Observable {
  def processCommand(cmd: String): String = {
    val command = cmd match {
      case "s" => new DrawCardCommand(this, "Scout")
      case "v" => new DrawCardCommand(this, "Viper")
      case "r" => new ResetGameCommand(this)
      case _   => new InvalidCommand(cmd)
    }
    val result = command.execute()
    notifyObservers()
    result
}
def getState: String = gameLogic.getDeckState
}