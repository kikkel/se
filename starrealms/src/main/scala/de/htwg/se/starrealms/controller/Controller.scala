package de.htwg.se.starrealms.controller

import de.htwg.se.starrealms.model._
import de.htwg.util.Observable

trait GameLogicController {
    val gameLogic: GameLogic
    def getGameState: String = gameLogic.optionsMenu()
    def resetGame(): Unit = gameLogic.resetGame()
}

trait DeckController {
    val deck: DefaultDeck
    def drawCard(cardType: String): String = {
        deck.drawCard() match {
            case Some(card) => s"Drew card: $card"
            case None => s"No $cardType cards left in the deck."
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
        val command: Command = input.toLowerCase match {
            case "s" => new DrawCardCommand(this, "Scout")
            case "v" => new DrawCardCommand(this, "Viper")
            case "reset" => new ResetGameCommand(this)
            case "deck" => new ShowDeckCommand(this)
            case _ => new InvalidCommand(input)
        }
        val result = command.execute()
        notifyObservers()
        result
        }
}

