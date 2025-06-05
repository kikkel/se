package de.htwg.se.starrealms.model.DeckComponent.impl

import de.htwg.se.starrealms.model.DeckComponent.interface.{DeckInterface, Builder}

class Director {
    def constructDecks(builderFactory: => Builder, decksByRole: Map[String, DeckInterface]): Map[String, DeckInterface] = {
        decksByRole.map { case (role, deck) =>
            val builder = builderFactory
            builder.setName(role)
            builder.setCards(deck.getCards)
            role -> builder.getProduct
        }
    }
}