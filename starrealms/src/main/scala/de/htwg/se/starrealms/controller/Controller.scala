package de.htwg.se.starrealms.controller

import de.htwg.se.starrealms.model._
import de.htwg.se.starrealms.util.Observable

trait GameLogicController {
    val gameLogic: GameLogic
    def getGameState: String = gameLogic.drawField()
    def resetGame(): Unit = gameLogic.resetGame()
}

trait DeckController {
    val deck: DefaultDeck
    def drawCard(cardType: String): String = {
        deck.drawCard(cardType) match {
            case Some(card) => s"Drew card: $card"
            case None => s"No $cardType cards left in the deck. #DeckController"
        }
    }
    def getDeckState: String = deck.getDeckState
    def resetDeck(): Unit = deck.resetDeck()
}


class Controller (val gameLogic: GameLogic, val deck: DefaultDeck) 
    extends Observable 
    with GameLogicController 
    with DeckController {
    
    //input processing
    def processInput(input: String): String = {
        val result = input.toLowerCase match {
            case "s" => drawCard("Scout")
            case "v" => drawCard("Viper")
            case "reset" => 
                resetGame()
                resetDeck()
                "Game and deck have been reset. #Controller"
/*             case "exit" => 
                println("Exiting the game.")
                return false //break loop */
            case "deck" => getDeckState
            case _ => 
                val message = s"Unknown command: $input #Controller"
                println(message)
                message //Meaningful String
        }
        notifyObservers()
        result
    }
}

