package de.htwg.se.starrealms.model.DeckComponent

import de.htwg.se.starrealms.model.CardComponent.Card

trait DeckDirectorInterface {
    def constructEmptyDeck(name: String, builderFactory: => Builder): DeckInterface
    def constructCustomDeck(name: String, builderFactory: => Builder, cards: List[Card]): DeckInterface
    def constructDecks(builderFactory: => Builder, groupedCards: Map[String, List[Card]]): Map[String, DeckInterface]
}