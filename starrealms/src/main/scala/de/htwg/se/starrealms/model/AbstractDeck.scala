package de.htwg.se.starrealms.model

abstract class AbstractDeck(val name: String, val cardType: CardType) {
    
    //informative
    def size: Int
    def isEmpty: Boolean
    def getName: String
    def getCardType: CardType
    def getCards: List[AbstractCard]

    //functionality
    def addCard(card: AbstractCard): Unit
    def removeCard(card: AbstractCard): Unit
    def shuffle(): Unit
    def drawCard(): Option[AbstractCard]
    
    override def toString: String = s"Deck(name=$name, cardType=$cardType, cards=$getCards)"
    }
    