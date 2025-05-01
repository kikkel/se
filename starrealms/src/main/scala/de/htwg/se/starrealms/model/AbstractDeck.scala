package de.htwg.se.starrealms.model

abstract class AbstractDeck(val name: String, val cardType: CardType) {
    def getName: String
    def getCardType: CardType
    def getCards: List[AbstractCard]
    def addCard(card: AbstractCard): Unit
    def removeCard(card: AbstractCard): Unit
    def shuffle(): Unit
    def drawCard(): Option[AbstractCard]
    def isEmpty: Boolean
    def size: Int
    
    override def toString: String = s"Deck(name=$name, cardType=$cardType, cards=$getCards)"
    }
    