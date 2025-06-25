package de.htwg.se.starrealms.model.PlayerComponent

trait PlayerInterface {

  def getName: String
  def setName(newName: String): Unit

  def getHealth: Int
  def setHealth(newHealth: Int): Unit

  def takeDamage(amount: Int): Unit
  def heal(amount: Int): Unit
  def isAlive: Boolean

  def getDeckSize: Int
  def setDeckSize(size: Int): Unit

  def getDiscardSize: Int
  def setDiscardSize(size: Int): Unit

  def getHandSize: Int
  def setHandSize(size: Int): Unit
}