package de.htwg.se.starrealms.model

import de.htwg.se.starrealms.model.Card
import de.htwg.se.starrealms.model.Deck

case class Player(
    name: String,
    var life: Int = 50
) {
    def takeDamage(amount: Int): Unit = {
        life = math.max(0, life - amount)
    }

    def heal(amount: Int): Unit = {
        life += amount
    }

    def isAlive: Boolean = life > 0

    override def toString: String = s"$name (Leben: $life)"
}
