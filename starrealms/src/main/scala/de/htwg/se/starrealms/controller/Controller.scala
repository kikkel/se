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

        def drawScout(): Unit = {
            val card = deck.drawCard()
            card match {
                case Some(c) if c.name == "Scout" =>
                println(s"Scout card drawn: $c")
                case Some(_) =>
                println("The drawn card is not a Scout card.")
                case None =>
                println("No Scout cards left in the deck.")
            }
        }

        def drawViper(): Unit = {
            val card = deck.drawCard()
            card match {
                case Some(c) if c.name == "Viper" =>
                println(s"Viper card drawn: $c")
                case Some(_) =>
                println("The drawn card is not a Viper card.")
                case None =>
                println("No Viper cards left in the deck.")
            }
        }

        override def resetGame(): Unit = {
            deck.resetDeck()
            println("Game has been reset.")
        }

        override def getGameState: String = {
            s"Deck:\n${deck.getDeckState}\n"
        }

       def processInput(input: String): String = {
        val command: Command = input.toLowerCase match {
            case "s" => new DrawCardCommand(this, "Scout")
            case "v" => new DrawCardCommand(this, "Viper")
            case "r" => new ResetGameCommand(this)
            //case "deck" => new ShowDeckCommand(this, "Deck:")
            case _ => new InvalidCommand(input)
        }
        val result = command.execute()
        notifyObservers()
        result
        }
}


