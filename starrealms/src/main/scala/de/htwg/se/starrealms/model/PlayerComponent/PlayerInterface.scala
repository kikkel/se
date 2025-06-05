package de.htwg.se.starrealms.controller.ControllerComponent.Controller_interfaces


trait PlayerInterface {
  def getName: String
  def setName(name: String): Unit
  def getScore: Int
  def setScore(score: Int): Unit
  def getDeckSize: Int
  def setDeckSize(size: Int): Unit
  def getDiscardSize: Int
  def setDiscardSize(size: Int): Unit
  def getHandSize: Int
  def setHandSize(size: Int): Unit
}
