package de.htwg.se.starrealms.model.DeckComponent.interface

import de.htwg.se.starrealms.model.CardComponent.interface.Card
import de.htwg.se.starrealms.model.DeckComponent.interface.DeckInterface
import scala.util.Try

trait Builder {
    def reset: Unit
    def setName(name: String): Unit
    def setCards(cards: Map[Card, Int]): Unit
    def addCard(card: Card): Unit
    def addCards(cards: List[Card]): Unit
    def getProduct: DeckInterface
}

