package de.htwg.se.starrealms.controller

import de.htwg.se.starrealms.model.{Card, Deck}

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
}