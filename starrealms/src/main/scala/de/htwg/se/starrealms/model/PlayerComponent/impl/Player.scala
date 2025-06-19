package de.htwg.se.starrealms.model.PlayerComponent.impl

import de.htwg.se.starrealms.model.PlayerComponent.PlayerInterface
import com.google.inject.Inject

case class Player @Inject() (
    name: String,
    var health: Int //todo: implement starting helth elsewhere in code, e.g. start game
) extends PlayerInterface {
    override def takeDamage(amount: Int): Unit = {
        health = math.max(0, health- amount)
    }

    override def heal(amount: Int): Unit = {
        health += amount
    }

    override def isAlive: Boolean = health > 0

    //override def toString: String = s"$name (Leben: $health)"
    override def getHealth: Int = health
    override def getHandSize: Int = ???
    override def getName: String = name
    override def getDiscardSize: Int = ???
    override def getDeckSize: Int = ???

    override def setDeckSize(size: Int): Unit = ???
    override def setDiscardSize(size: Int): Unit = ???
    override def setName(name: String): Unit = ???
    override def setHandSize(size: Int): Unit = ???
    override def setHealth(score: Int): Unit = ???
}
