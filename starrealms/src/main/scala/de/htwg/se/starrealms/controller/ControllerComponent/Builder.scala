package de.htwg.se.starrealms.controller.ControllerComponent

import de.htwg.se.starrealms.model.DeckComponent.interface.DeckInterface
import de.htwg.se.starrealms.model.DeckComponent.impl.Deck
import de.htwg.se.starrealms.model.CardComponent.interface.Card

class DeckBuilder extends Builder {
    private var deck: DeckInterface = new Deck(): DeckInterface

    override def reset(): Unit = { deck = new Deck(): DeckInterface }
    override def setName(name: String): Unit = { deck.setName(name) }
    override def setCards(newCards: Map[Card, Int]): Unit = {
        deck.setCards(newCards)
    }
    override def addCards(cards: List[Card]): Unit = { cards.foreach(deck.addCard) }
    override def addCard(card: Card): Unit = { deck.addCard(card) }

    override def getProduct(): DeckInterface = {
        val product = deck
        reset()
        product
    }

}

class Director {
    def constructDecks(builder: Builder, decksByRole: Map[String, DeckInterface]): Map[String, DeckInterface] = {
        decksByRole.map { case (role, deck) =>
            builder.reset()
            builder.setName(role)
            builder.setCards(deck.getCards)
            role -> builder.getProduct()
        }
    }
}