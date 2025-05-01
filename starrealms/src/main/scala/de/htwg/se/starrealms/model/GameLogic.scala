package de.htwg.se.starrealms.model

import scala.collection.mutable.ListBuffer
import de.htwg.se.starrealms.view
import de.htwg.se.starrealms.util.Observer

class GameLogic (val playingfield: PlayingField) {
  private var deck = new DefaultDeck()
  private var field: List[String] = List()
  private val observers: ListBuffer[Observer] = ListBuffer()

  def addObserver(observer: Observer): Unit = observers += observer
  def removeObserver(observer: Observer): Unit = observers -= observer
  def notifyObservers(): Unit = observers.foreach(_.update)

  def drawField(): String = {
	val deckState = deck.getDeckState
	val fieldState = if (field.nonEmpty) field.mkString(", ") else "Empty"
	s"Deck: $deckState\nField: $fieldState"
  }

  def turnOverCard(userInput: String): String = {
    userInput.toLowerCase match {
      case "s" =>
        deck.drawCard("Scout") match {
          case Some(card) =>
            field = field :+ card
            notifyObservers()
            s"Turned over Scout: $card"
          case None => "No Scout cards left in the deck."
    }
      case "v" =>
        deck.drawCard("Viper") match {
          case Some(card) =>
            field = field :+ card
            notifyObservers()
            s"Turned over Viper: $card"
          case None => "No Viper cards left in the deck."
        }
      case _ => "Invalid input. Please enter 's' for Scout or 'v' for Viper."
    }

  }

  def resetGame(): Unit = {
    field = List()
    notifyObservers()
  }

  def exitGame(): Boolean = {
    true
  }

}

