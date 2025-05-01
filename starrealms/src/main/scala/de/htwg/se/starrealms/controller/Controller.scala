package de.htwg.se.starrealms.controller

import de.htwg.se.starrealms.model._
import de.htwg.se.starrealms.util.Observable

class Controller (gameLogic: GameLogic) extends Observable {
    def processInput(input: String): String = {
        val result = input.toLowerCase match {
            case "s" => gameLogic.turnOverCard("s")
            case "v" => gameLogic.turnOverCard("v")
            case "reset" => 
                gameLogic.resetGame()
                "Game has been reset."
/*             case "exit" => 
                println("Exiting the game.")
                return false //break loop */
            case _ => 
                val message = s"Unknown command: $input"
                println(message)
                message //Meaningful String
        }
        notifyObservers()
        result
    }

    def getGameState: String = {
        gameLogic.drawField()
    }
}

class DefaultDeckController(val deck: DefaultDeck) {
    //draw card and return message for view
    def drawCard(cardType: String): String = {
        deck.drawCard(cardType) match {
            case Some(card) => s"Drew card: $card"
            case None => s"No $cardType cards left in the deck."
        }
    }

    //get current deck state
    def getDeckState: String = deck.getDeckState

    //reset to default state
    def resetDeck(): String = {
        deck.resetDeck()
        "Deck reset to default state."
    }
}