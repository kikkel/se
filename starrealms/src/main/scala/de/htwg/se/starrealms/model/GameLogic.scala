package de.htwg.se.starrealms.model

import scala.collection.mutable.ListBuffer
import de.htwg.se.starrealms.view
import de.htwg.util.Observer

class GameLogic {
  private var deck = new Deck()
  private val observers: ListBuffer[Observer] = ListBuffer()
  //private var discardPile: List[DefaultCard] = List()


  //DeckBuilder------------------------------------------
  loadCardsFromFile()

  val director = new Director()
  val deckBuilder = new DeckBuilder()

  director.constructDefaultDeck(deckBuilder)
  val defaultDeck = deckBuilder.getProduct()
  println(defaultDeck.render())

  val tradeDeckCards = List()
  director.constructTradeDeck(deckBuilder, "Core Set")
  val tradeDeck = deckBuilder.getProduct()
  println(tradeDeck)
  //-----------------------------------------------------


/*   def optionsMenu(): String = {
	val deckState = deck.getDeckState
	s"\n\nDeck:\n$deckState\n\n  #gameLogic\n\n"

  } */

  def turnOverCard(userInput: String): String = {
    userInput.toLowerCase match {
      case "s" =>
        deck.drawCard() match {
          case Some(card) =>
            //exitdeck.removeCard(card)
            //discardPile = discardPile :+ card
            s"Turned over Scout: $card  #gameLogic"


          case None => "No Scout cards left in the deck.  #gameLogic"
    }
      case "v" =>
        deck.drawCard() match {
          case Some(card) =>
            s"Turned over Viper: $card  #gameLogic"

          case None => "No Viper cards left in the deck.  #gameLogic"
        }
      case _ => "Invalid input. Please enter 's' for Scout or 'v' for Viper.  #gameLogic"
    }

  }

  def resetGame(): Unit = {
    //discardPile = List()
    deck.resetDeck()
  }

  def exitGame(): Boolean = {
    println("Exiting the game... #gameLogic")
    false // Signal to exit the loop
  }

}

