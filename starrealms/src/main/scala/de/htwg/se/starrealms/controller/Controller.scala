package de.htwg.se.starrealms.controller

import de.htwg.se.starrealms.model
import de.htwg.util.Observable

class Controller (gameLogic: model.GameLogic) extends Observable {
    def processInput(input: String): Unit = {
        input match {
            case "s" => gameLogic.turnOverCard("s")
            case "v" => gameLogic.turnOverCard("v")
            case "reset" => gameLogic.resetGame()
            case "exit" => println("Exiting the game.")
            case _ => println(s"Unknown command: $input")
        }
        notifyObservers()
    }

    def getGameState: String = {
        gameLogic.drawField()
    }
}

