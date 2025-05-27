package de.htwg.se.starrealms.model

import scala.util.Random
import org.scalactic.Fail


class Deck {
    private var name: String = ""
    private var cards: Map[Card, Int] = Map()

    def setName(name: String): Unit = this.name = name
    def setCards(newCards: Map[Card, Int]): Unit = { cards = newCards }

    def getName: String = name
    def getCards: Map[Card, Int] = getExpandedCards.groupBy(identity).view.mapValues(_.size).toMap
    def getExpandedCards: List[Card] = { cards.flatMap { case (card, qty) => List.fill(qty)(card) }.toList }


    def addCard(card: Card): Unit = { cards = cards.updated(card, cards.getOrElse(card, 0) + 1) }
    def addCards(cardsToAdd: List[Card]): Unit = { cardsToAdd.foreach(addCard) }

    def removeCard(card: Card): Unit = {
        cards.get(card) match {
            case Some(qty) if qty > 1 => cards = cards.updated(card, qty - 1)
            case Some(_) => cards = cards - card
            case None => println(s"Card $card not found in deck.")
        }
    }
    //def shuffle(): Unit = { val shuffleCards = Random.shuffle(getCards); setCards(shuffleCards.groupBy(identity).view.mapValues(_.size).toMap) }
    def shuffle(): Unit = {
        val expandedCards = cards.flatMap { case (card, qty) => List.fill(qty)(card) } // Expand cards based on quantity
        val shuffledCards = Random.shuffle(expandedCards) // Shuffle the expanded list
        val reshuffledMap = shuffledCards.groupBy(identity).view.mapValues(_.size).toMap // Rebuild Map[Card, Int]
        setCards(reshuffledMap) // Update the deck with the shuffled cards
    }
    def drawCard(): Option[Card] = {
        cards.keys.headOption match {
            case Some(card) =>
                removeCard(card)
                Some(card)
            case None =>
                println("No cards left in the deck.")
                None
        }
    }
    def resetDeck(): Unit = cards = Map()
    def render(): String = {
        val cardDescriptions = getExpandedCards.map(_.render()).mkString("\n")
        s"Deck:\n$name\nCards:\n[$cardDescriptions]"
    }
}

/* class Manual { } */


trait Builder {
    def reset(): Unit
    def setName(name: String): Unit
    def setCards(cards: Map[Card, Int]): Unit

    def addCard(card: Card): Unit
    def addCards(cards: List[Card]): Unit
    def getProduct(): Deck

}


class DeckBuilder extends Builder {
    private var deck: Deck = new Deck()

    override def reset(): Unit = { deck = new Deck() }
    override def setName(name: String): Unit = { deck.setName(name) }
    override def setCards(newCards: Map[Card, Int]): Unit = {
        deck.setCards(newCards)
    }
    override def addCards(cards: List[Card]): Unit = { cards.foreach(deck.addCard) }
    override def addCard(card: Card): Unit = { deck.addCard(card) }

    override def getProduct(): Deck = {
        val product = deck
        reset()
        product
    }

}

class Director {
    def constructDecks(builder: Builder, decksByRole: Map[String, Deck]): Map[String, Deck] = {
        decksByRole.map { case (role, deck) =>
            builder.reset()
            builder.setName(role)
            builder.setCards(deck.getCards)
            role -> builder.getProduct()
        }
    }
/*     def constructTradeDeck(builder: Builder, decksByRole: Map[String, Deck]): Map[String, Deck] = {
        builder.reset()
        builder.setName(s"Trade Deck - $setName")
        builder.setCards(cards)

    } */
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


