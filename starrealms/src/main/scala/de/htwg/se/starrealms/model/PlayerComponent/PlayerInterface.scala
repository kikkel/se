package de.htwg.se.starrealms.model.PlayerComponent.interface

trait PlayerInterface {
  def getName: String
  def setName(name: String): Unit
  def getHealth: Int
  def setHealth(score: Int): Unit
  def getDeckSize: Int
  def setDeckSize(size: Int): Unit
  def getDiscardSize: Int
  def setDiscardSize(size: Int): Unit
  def getHandSize: Int
  def setHandSize(size: Int): Unit

  def isAlive: Boolean
  def takeDamage(amount: Int): Unit
  def heal(amount: Int): Unit

}
