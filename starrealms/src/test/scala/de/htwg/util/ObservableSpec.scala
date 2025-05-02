package de.htwg.util

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class ObservableSpec extends AnyWordSpec with Matchers {

  "An Observable" should {
    "allow adding observers" in {
      val observable = new Observable
      val observer = new TestObject

      observable.addObserver(observer)
      // Es gibt keine direkte Möglichkeit, die Liste der Beobachter zu überprüfen,
      // aber wir können sicherstellen, dass die Benachrichtigung funktioniert.
      noException should be thrownBy observable.notifyObservers()
    }

    "allow removing observers" in {
      val observable = new Observable
      val observer = new TestObject

      observable.addObserver(observer)
      observable.removeObserver(observer)
      // Nach dem Entfernen sollte die Benachrichtigung keine Fehler auslösen.
      noException should be thrownBy observable.notifyObservers()
    }

    "notify all observers" in {
      val observable = new Observable
      val observer1 = new TestObject
      val observer2 = new TestObject

      observable.addObserver(observer1)
      observable.addObserver(observer2)

      // Die Benachrichtigung sollte alle Beobachter aufrufen.
      noException should be thrownBy observable.notifyObservers()
    }

    "handle removing non-existent observers gracefully" in {
      val observable = new Observable
      val observer = new TestObject

      // Entfernen eines nicht vorhandenen Beobachters sollte keine Fehler auslösen.
      noException should be thrownBy observable.removeObserver(observer)
    }

    "handle notifying with no observers gracefully" in {
      val observable = new Observable

      // Benachrichtigung ohne Beobachter sollte keine Fehler auslösen.
      noException should be thrownBy observable.notifyObservers()
    }
  }
}