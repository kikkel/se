package de.htwg.se.starrealms.model

import scala.util.Random
import org.scalactic.Fail


class Deck {
    private var name: String = ""
    private var cards: Map[Card, Int] = Map()

    def setName(name: String): Unit = this.name = name
    def setCards(newCards: List[Card]): Unit = { cards = newCards.groupBy(identity).view.mapValues(_.size).toMap }

    def getName: String = name
    def getCards: List[Card] = cards.flatMap { case (card, qty) => List.fill(qty)(card) }.toList

    def addCard(card: Card): Unit = { cards = cards.updated(card, cards.getOrElse(card, 0) + 1) }

    def removeCard(card: Card): Unit = {
        cards.get(card) match {
            case Some(qty) if qty > 1 => cards = cards.updated(card, qty - 1)
            case Some(_) => cards = cards - card
            case None => println(s"Card $card not found in deck.")
        }
    }
    def shuffle(): Unit = { val shuffleCards = Random.shuffle(getCards); setCards(shuffleCards) }
    def drawCard(): Option[Card] = {
        getCards match {
            case Nil => None
            case head :: tail =>
                removeCard(head)
                Some(head)
        }
    }
    def resetDeck(): Unit = cards = Map()
    def render(): String = {
        val cardDescriptions = cards.map { case (card, qty) => s"${card.render()} (x$qty)" }.mkString(", ")
        s"Deck: $name, Cards: [$cardDescriptions]"
    }
}

/* object Deck {
    // Erstellt ein Standard-Playerdeck (8 Scouts, 2 Vipers) mit Bridge-Pattern
    def standardPlayerDeck(): Deck = {
        val deck = new Deck()
        val coreSet = Set("Core Set")
        val unaligned = Faction("Unaligned")
        val scout = new DefaultCard(
            set = coreSet,
            cardName = "Scout",
            primaryAbility = Some(new Ability(List(SimpleAction("Gain 1 Trade")))),
            faction = unaligned,
            cardType = Success(new Ship()),
            qty = 1,
            role = "Personal Deck"
        )
        val viper = new DefaultCard(
            set = coreSet,
            cardName = "Viper",
            primaryAbility = Some(new Ability(List(SimpleAction("Gain 1 Combat")))),
            faction = unaligned,
            cardType = Success(new Ship()),
            qty = 1,
            role = "Personal Deck"
        )
        deck.setName("Player Deck")
        deck.setCards(Random.shuffle(List.fill(8)(scout) ++ List.fill(2)(viper)))
        deck
    }

} */


/* class Manual { } */


trait Builder {
    def reset(): Unit
    def setName(name: String): Unit
    def setCards(cards: List[Card]): Unit

    def addCard(card: Card): Unit
    def addCards(cards: List[Card]): Unit
    def getProduct(): Deck

}


class DeckBuilder extends Builder {
    private var deck: Deck = new Deck()

    override def reset(): Unit = { deck = new Deck() }
    override def setName(name: String): Unit = { deck.setName(name) }
    override def setCards(cards: List[Card]): Unit = { deck.setCards(cards) }

    override def addCards(cards: List[Card]): Unit = { cards.foreach(deck.addCard) }
    override def addCard(card: Card): Unit = { deck.addCard(card) }
    //override def addSet(set: List[Card]) { set.foreach(deck.addCard) }

    override def getProduct(): Deck = {
        val product = deck
        reset()
        product
    }

}

class Director {
    def constructDecks(builder: Builder, decksByRole: Map[String, Deck]): Map[String, Deck] = {
        decksByRole.map { case (role, deck) =>
            builder.reset()
            builder.setName(role)
            builder.setCards(deck.getCards)
            role -> builder.getProduct()
        }
    }
/*     def constructTradeDeck(builder: Builder, decksByRole: Map[String, Deck]): Map[String, Deck] = {
        builder.reset()
        builder.setName(s"Trade Deck - $setName")
        builder.setCards(cards)

    } */
}

// Unlike other creational patterns, builder lets you construct
// products that don't follow the common interface.
/* class DeckManualBuilder implements Builder is
    private field manual:Manual

    constructor DeckManualBuilder() is
        this.reset()

    method reset() is
        this.manual = new Manual()

    method setSeats(...) is
        // Document Deck seat features.

    method setEngine(...) is
        // Add engine instructions.

    method setTripComputer(...) is
        // Add trip computer instructions.

    method setGPS(...) is
        // Add GPS instructions.

    method getProduct():Manual is
        // Return the manual and reset the builder.

 */


