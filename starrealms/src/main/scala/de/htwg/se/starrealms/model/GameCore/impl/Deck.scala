package de.htwg.se.starrealms.model.GameCore.impl

import de.htwg.se.starrealms.model.GameCore.{DeckInterface, CardInterface, DeckDirectorInterface, Builder}
import com.google.inject.{Inject, Guice}

class Deck @Inject() extends DeckInterface {
    private var name: String = ""
    private var cards: Map[CardInterface, Int] = Map()
    private var cardStack: List[CardInterface] = List() // <- Reihenfolge der Karten

    override def setName(name: String): Unit = this.name = name
    override def setCards(newCards: Map[CardInterface, Int]): Unit = {
        cards = newCards
        cardStack = cards.flatMap { case (card, qty) => List.fill(qty)(card) }.toList
    }

    override def setCardStack(newStack: List[CardInterface]): Unit = {
        cardStack = newStack
        cards = cardStack.groupBy(identity).view.mapValues(_.size).toMap
    }

    override def getName: String = name
    override def getCards: Map[CardInterface, Int] = cardStack.groupBy(identity).view.mapValues(_.size).toMap
    override def getCardStack: List[CardInterface] = cardStack

    override def addCard(card: CardInterface): Unit = {
        cards = cards.updated(card, cards.getOrElse(card, 0) + 1)
        cardStack = cardStack :+ card
    }
    override def addCards(cardsToAdd: List[CardInterface]): Unit = { cardsToAdd.foreach(addCard) }

    override def removeCard(card: CardInterface): Unit = {
        cards.get(card) match {
            case Some(qty) if qty > 1 => cards = cards.updated(card, qty - 1)
            case Some(_) => cards = cards - card
            case None => println(s"Card $card not found in deck.")
        }
        val idx = cardStack.indexOf(card)
        if (idx >= 0) cardStack = cardStack.patch(idx, Nil, 1)
    }

    override def shuffle(): Unit = {
        cardStack = scala.util.Random.shuffle(cardStack)
        cards = cardStack.groupBy(identity).view.mapValues(_.size).toMap
    }

    override def drawCard(): Option[CardInterface] = {
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
    override def resetDeck(): Unit = {
        cards = Map()
        cardStack = List()
    }
    override def render(): String = {
        val cardDescriptions = getCardStack.map(_.render()).mkString("\n")
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

class DeckBuilder @Inject() (product: DeckInterface) extends Builder {
    private var productVar: DeckInterface = product

    override def reset: Unit = productVar.resetDeck() 
    override def setName(name: String): Unit = productVar.setName(name)
    override def setCards(newCards: Map[CardInterface, Int]): Unit = productVar.setCards(newCards)
    override def addCards(cards: List[CardInterface]): Unit = cards.foreach(productVar.addCard)
    override def addCard(card: CardInterface): Unit = productVar.addCard(card)
    override def getProduct: DeckInterface = productVar

}

class DeckDirector @Inject() extends DeckDirectorInterface {
    override def constructEmptyDeck(name: String, builderFactory: => Builder): DeckInterface = {
        val builder = builderFactory
        builder.reset
        builder.setName(name)
        builder.setCards(Map.empty)
        builder.getProduct
    }
    override def constructCustomDeck(name: String, builderFactory: => Builder, cards: List[CardInterface]): DeckInterface = {
            val builder = builderFactory
            builder.reset
            builder.setName(name)
            val cardMap = cards.groupBy(identity).map { case (card, list) => card -> list.size }
            builder.setCards(cardMap)
            builder.getProduct
    }
    override def constructDecks(builderFactory: => Builder, groupedCards: Map[String, List[CardInterface]]): Map[String, DeckInterface] = {
        groupedCards.map { case (role, cards) =>
            val builder = builderFactory
            builder.reset
            builder.setName(role)
            val cardMap = cards.groupBy(identity).view.mapValues(_.size).toMap
            builder.setCards(cardMap)
            val product = builder.getProduct
            val deepCopy = new Deck()
            deepCopy.setName(product.getName)
            deepCopy.setCards(product.getCards)
            role -> deepCopy
        }
    }
}