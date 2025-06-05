package de.htwg.se.starrealms.model.DeckComponent.impl

import de.htwg.se.starrealms.model.DeckComponent.interface.{DeckInterface, Builder}
import de.htwg.se.starrealms.model.CardComponent.interface.Card

class DeckBuilder(product: DeckInterface) extends Builder {
    private var productVar: DeckInterface = product

    override def reset: Unit = productVar.resetDeck() 
    override def setName(name: String): Unit = productVar.setName(name)
    override def setCards(newCards: Map[Card, Int]): Unit = productVar.setCards(newCards)
    override def addCards(cards: List[Card]): Unit = cards.foreach(productVar.addCard)
    override def addCard(card: Card): Unit = productVar.addCard(card)
    override def getProduct(): DeckInterface = productVar

}

