package de.htwg.se.starrealms.model

class TestDeck(name: String, cardType: CardType) extends AbstractDeck(name, cardType) {
    //def this (name: String) = this(name, new CardType(name)) // Default constructor with empty card type
    //informative
    override def size(): Int = 0 // Assuming size returns 0 when no cards are present
    override def isEmpty(): Boolean = true // Assuming isEmpty returns true when no cards are present
    override def getName: String = name
    override def getCardType: CardType = cardType
    override def getCards: List[AbstractCard] = List() // Assuming no cards are present
    
    //functionality
    override def addCard(card: AbstractCard): Unit = {} // No-op for test
    override def removeCard(card: AbstractCard): Unit = {} // No-op for test
    override def shuffle(): Boolean = true // Assuming shuffle always returns true
    override def drawCard(): Option[AbstractCard] = None // Assuming drawCard returns None when no cards are present
    
    override def toString: String = s"TestDeck(name=$name, cardType=$cardType, cards=$getCards)"

}