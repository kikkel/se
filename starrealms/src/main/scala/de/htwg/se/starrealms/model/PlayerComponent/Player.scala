package de.htwg.se.starrealms.model.PlayerComponent

import de.htwg.se.starrealms.model.SetUpComponent.Card
import de.htwg.se.starrealms.model.SetUpComponent.Deck

case class Player(
    name: String,
    var health: Int = 3
) {
    def takeDamage(amount: Int): Unit = {
        health = math.max(0, health- amount)
    }

    def heal(amount: Int): Unit = {
        health += amount
    }

    def isAlive: Boolean = health > 0

    override def toString: String = s"$name (Leben: $health)"
}
