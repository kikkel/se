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
    override def render(): String = {
        val cardsString = cards.map(_.render()).mkString("\n")
        s"Deck Name: $name\nCards:\n$cardsString"
    }
}


/* class Manual { } */


trait Builder {
    def reset(): Unit
    def setName(name: String): Unit
    def setCards(cards: List[Card]): Unit

    def addCard(card: Card): Unit
    def getProduct(): Deck

}


class DeckBuilder extends Builder {
    private var deck: Deck = new Deck()

    override def reset() { deck = new Deck() }
    override def setName(name: String) { deck.setName(name) } 
    override def setCards(cards: List[Card]) { deck.setCards(cards) }

    override def addCard(cards: List[Card]) { cards.foreach(deck.addCard) }
    //override def addSet(set: List[Card]) { set.foreach(deck.addCard) }

    override def getProduct(): Deck = {
        val product = deck
        reset()
        product
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

class Director {
    def constructDefaultDeck(builder: Builder): Unit = {
        builder.reset()
        builder.setName("Default Deck")
        builder.setCards(List.fill(8)(new Scout()) ++ List.fill(2)(new Viper()))
    }

    def constructTradeDeck(builder: Builder, setName: String): Unit = {
        builder.reset()
        builder.setName(s"Trade Deck - $setName")
        val cards = LoadCards.getCardsForSet(setName)
        builder.setCards(cards)
    }

}



