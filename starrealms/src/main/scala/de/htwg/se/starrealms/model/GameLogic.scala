package de.htwg.se.starrealms.model

import scala.collection.mutable.ListBuffer
import de.htwg.se.starrealms.view
import de.htwg.util.Observer

class GameLogic (val playingfield: PlayingField) {
  private var deck = new DefaultDeck("DefaultDeck", new CardType("Default"), List())
  private var field: List[String] = List()
  private val observers: ListBuffer[Observer] = ListBuffer()
  //private var discardPile: List[DefaultCard] = List()

  def addObserver(observer: Observer): Unit = observers += observer
  def removeObserver(observer: Observer): Unit = observers -= observer
  def notifyObservers(): Unit = observers.foreach(_.update)

  def drawField(): String = {
	val deckState = deck.getDeckState
	//val fieldState = if (field.nonEmpty) field.mkString(", ") else "Empty"
 // val discardPileState = if (discardPile.nonEmpty) discardPile.mkString(" \n") else "Empty"
	s"\n\nDeck:\n$deckState\n\n  #gameLogic\n\n"
  //s"Deck: $deckState\nField: $fieldState  #gameLogic"
  }

  def turnOverCard(userInput: String): String = {
    userInput.toLowerCase match {
      case "s" =>
        deck.drawCard() match {
          case Some(card) =>
            //field = field :+ card.toString()
            //exitdeck.removeCard(card)
            //discardPile = discardPile :+ card
            //notifyObservers() //state change
            s"Turned over Scout: $card  #gameLogic"


          case None => "No Scout cards left in the deck.  #gameLogic"
    }
      case "v" =>
        deck.drawCard() match {
          case Some(card) =>
            s"Turned over Viper: $card  #gameLogic"            
            //notifyObservers() //state change

          case None => "No Viper cards left in the deck.  #gameLogic"
        }
      case _ => "Invalid input. Please enter 's' for Scout or 'v' for Viper.  #gameLogic"
    }

  }

  def resetGame(): Unit = {
    //field = List()
    //discardPile = List()
    deck.resetDeck()
    notifyObservers() //notify all observers of its reset
  }

  def exitGame(): Boolean = {
    println("Exiting the game... #gameLogic")
    false // Signal to exit the loop
  }

}

