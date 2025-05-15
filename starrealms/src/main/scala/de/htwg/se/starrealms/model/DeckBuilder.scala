package de.htwg.se.starrealms.model

import de.htwg.se.starrealms.model._
import scala.util.Random



class Deck {
    private var name: String = ""
    private var cards: List[Card] = List()

    def setName(name: String): Unit = this.name = name
    def setCards(cards: List[Card]): Unit = this.cards = cards

    def getName: String = name
    def getCards: List[Card] = cards

    def addCard(card: Card): Unit = cards = cards :+ card
    def removeCard(card: Card): Unit = cards = cards.filterNot(_ == card)
    def shuffle(): Unit = cards = Random.shuffle(cards)
    def drawCard(): Option[Card] = {
        cards match {
            case Nil => None
            case head :: tail =>
                cards = tail
                Some(head)
        }
    }
    def resetDeck(): Unit = cards = List()
    def render(): String = {
        val cardDescriptions = cards.map(_.render()).mkString(", ")
        s"Deck: $name, Cards: [$cardDescriptions]"
    }
    
}


/* class Manual { } */


trait Builder {
    def reset(): Unit
    def setName(name: String): Unit
    def setCards(cards: List[Card]): Unit

    def addCard(card: Card): Unit
    def addCards(cards: List[Card]): Unit
    def getProduct(): Deck

}


class DeckBuilder extends Builder {
    private var deck: Deck = new Deck()

    override def reset(): Unit = { deck = new Deck() }
    override def setName(name: String): Unit = { deck.setName(name) } 
    override def setCards(cards: List[Card]): Unit = { deck.setCards(cards) }

    override def addCards(cards: List[Card]): Unit = { cards.foreach(deck.addCard) }
    override def addCard(card: Card): Unit = { deck.addCard(card) }
    //override def addSet(set: List[Card]) { set.foreach(deck.addCard) }

    override def getProduct(): Deck = {
        val product = deck
        reset()
        product
    }

}

class Director {
    def constructTradeDeck(builder: Builder, setName: String, cards: List[Card]): Unit = {
        builder.reset()
        builder.setName(s"Trade Deck - $setName")
        builder.setCards(cards)

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


