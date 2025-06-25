package de.htwg.se.starrealms.model.PlayerComponent.impl

import de.htwg.se.starrealms.model.PlayerComponent.PlayerInterface

class Player(
    private var name: String,
    private var health: Int
) extends PlayerInterface {

    private var deckSize: Int = 0
    private var discardSize: Int = 0
    private var handSize: Int = 0

    override def takeDamage(amount: Int): Unit = {
        health = math.max(0, health - amount)
    }

    override def heal(amount: Int): Unit = {
        health += amount
    }

    override def isAlive: Boolean = health > 0

    override def getHealth: Int = health

    override def setHealth(newHealth: Int): Unit = {
        health = newHealth
    }

    override def getName: String = name

    override def setName(newName: String): Unit = {
        name = newName
    }

    override def getDeckSize: Int = deckSize

    override def setDeckSize(size: Int): Unit = {
        deckSize = size
    }

    override def getDiscardSize: Int = discardSize

    override def setDiscardSize(size: Int): Unit = {
        discardSize = size
    }

    override def getHandSize: Int = handSize

    override def setHandSize(size: Int): Unit = {
        handSize = size
    }
}