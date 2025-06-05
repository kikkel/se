package de.htwg.se.starrealms.model.DeckComponent.impl

import de.htwg.se.starrealms.model.CardComponent.interface.Card
import de.htwg.se.starrealms.model.DeckComponent.interface._
import scala.util.Random
import org.scalactic.Fail
import scala.util.Try


class Deck extends DeckInterface {
    private var name: String = ""
    private var cards: Map[Card, Int] = Map()
    private var cardStack: List[Card] = List() // <- Reihenfolge der Karten

    def setName(name: String): Unit = this.name = name
    def setCards(newCards: Map[Card, Int]): Unit = {
        cards = newCards
        cardStack = cards.flatMap { case (card, qty) => List.fill(qty)(card) }.toList
    }

    def setCardStack(newStack: List[Card]): Unit = {
        cardStack = newStack
        cards = cardStack.groupBy(identity).view.mapValues(_.size).toMap
    }

    def getName: String = name
    def getCards: Map[Card, Int] = cardStack.groupBy(identity).view.mapValues(_.size).toMap
    def getExpandedCards: List[Card] = cardStack

    def addCard(card: Card): Unit = {
        cards = cards.updated(card, cards.getOrElse(card, 0) + 1)
        cardStack = cardStack :+ card
    }
    def addCards(cardsToAdd: List[Card]): Unit = { cardsToAdd.foreach(addCard) }

    def removeCard(card: Card): Unit = {
        cards.get(card) match {
            case Some(qty) if qty > 1 => cards = cards.updated(card, qty - 1)
            case Some(_) => cards = cards - card
            case None => println(s"Card $card not found in deck.")
        }
        val idx = cardStack.indexOf(card)
        if (idx >= 0) cardStack = cardStack.patch(idx, Nil, 1)
    }

    def shuffle(): Unit = {
        cardStack = scala.util.Random.shuffle(cardStack)
        cards = cardStack.groupBy(identity).view.mapValues(_.size).toMap
    }

    def drawCard(): Option[Card] = {
        cardStack match {
            case head :: tail =>
                cardStack = tail
                cards = cardStack.groupBy(identity).view.mapValues(_.size).toMap
                Some(head)
            case Nil =>
                println("No cards left in the deck.")
                None
        }
    }
    def resetDeck(): Unit = {
        cards = Map()
        cardStack = List()
    }
    def render(): String = {
        val cardDescriptions = getExpandedCards.map(_.render()).mkString("\n")
        s"Deck:\n$name\nCards:\n[$cardDescriptions]"
    }
}


// Unlike other creational patterns, builder lets you construct
// products that don't follow the common interface.
/* class DeckManualBuilder implements Builder is
    private field manual:Manual

    constructor DeckManualBuilder() is
        this.reset()

    method reset() is
        this.manual = new Manual()

    method setSeats(...) is
        // Document Deck seat features.

    method setEngine(...) is
        // Add engine instructions.

    method setTripComputer(...) is
        // Add trip computer instructions.

    method setGPS(...) is
        // Add GPS instructions.

    method getProduct():Manual is
        // Return the manual and reset the builder.

 */


