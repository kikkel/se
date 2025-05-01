package de.htwg.se.starrealms.util


trait Observer {
  def update: Unit
}

class Observable {
  private var observers: List[Observer] = List()

  def addObserver(observer: Observer): Unit = {
    observers = observer :: observers
  }

  def removeObserver(observer: Observer): Unit = {
    observers = observers.filterNot(_ == observer)
  }

  def notifyObservers(): Unit = {
    observers.foreach(_.update)
  }
}

class TestObject extends Observer {
    def update: Unit = println("Ding Dong Test Update")
}

object ObserverPattern {
    val observable = new Observable
    val observer1 = new TestObject
    val observer2 = new TestObject

    observable.addObserver(observer1)
    observable.addObserver(observer2)
    observable.notifyObservers() // Notify all observers

    observable.removeObserver(observer1) // Remove observer1
    observable.notifyObservers() // Notify remaining observers
    observable.removeObserver(observer2) // Remove observer2
    observable.notifyObservers() // No observers left, no output expected
}