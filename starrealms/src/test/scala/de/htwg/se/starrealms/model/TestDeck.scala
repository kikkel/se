/* package de.htwg.se.starrealms.model

class TestDeck(name: String, cards: List[AbstractCard]) extends AbstractDeck(name, cards) {
    def this(name: String) = this(name, List[AbstractCard]()) 

    override def isEmpty: Boolean = cards.isEmpty 
    override def getName: String = name
    override def getCards: List[AbstractCard] = cards 
    override def addCard(card: AbstractCard): Unit = {} 
    override def removeCard(card: AbstractCard): Unit = {} 
    override def shuffle(): Unit = {} 
    override def drawCard(): Option[AbstractCard] = None 
    
    override def toString: String = s"TestDeck(name=$name, cards=$getCards)"

} */