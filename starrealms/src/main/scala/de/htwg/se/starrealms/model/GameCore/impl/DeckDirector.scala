package de.htwg.se.starrealms.model.GameCore.impl


import de.htwg.se.starrealms.model.GameCore.{Card, DeckDirectorInterface, DeckInterface, Builder}

class DeckDirector extends DeckDirectorInterface {
    override def constructEmptyDeck(name: String, builderFactory: => Builder): DeckInterface = {
        val builder = builderFactory
        builder.reset
        builder.setName(name)
        builder.setCards(Map.empty)
        builder.getProduct
    }
    override def constructCustomDeck(name: String, builderFactory: => Builder, cards: List[Card]): DeckInterface = {
            val builder = builderFactory
            builder.reset
            builder.setName(name)
            val cardMap = cards.groupBy(identity).map { case (card, list) => card -> list.size }
            builder.setCards(cardMap)
            builder.getProduct
    }
    override def constructDecks(builderFactory: => Builder, groupedCards: Map[String, List[Card]]): Map[String, DeckInterface] = {
        groupedCards.map { case (role, cards) =>
            val builder = builderFactory
            builder.reset
            builder.setName(role)
            val cardMap = cards.groupBy(identity).view.mapValues(_.size).toMap
            builder.setCards(cardMap)
            role -> builder.getProduct
        }
    }
}