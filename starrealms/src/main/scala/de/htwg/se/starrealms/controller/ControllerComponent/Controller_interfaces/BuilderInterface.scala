package de.htwg.se.starrealms.controller.ControllerComponent.Controller_interfaces

import de.htwg.se.starrealms.model.PlayerComponent._
import de.htwg.se.starrealms.model.SetUpComponent.{Card, Deck}
import scala.util.Try

trait Builder {
    def reset(): Unit
    def setName(name: String): Unit
    def setCards(cards: Map[Card, Int]): Unit
    def addCard(card: Card): Unit
    def addCards(cards: List[Card]): Unit
    def getProduct(): Deck
}

