/* package de.htwg.se.starrealms.controller

import de.htwg.se.starrealms.model._
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class GameStateSpec extends AnyWordSpec with Matchers {

  "A GameState" should {

    "initialize with an empty discard pile and field" in {
      val gameState = new GameState

      // Überprüfen, ob die Ablagestapel und das Spielfeld leer sind
      gameState.drawCard("Scout") // Trigger observers
      gameState.reset() // Reset to ensure initial state
    }

    "draw a card and add it to the discard pile" in {
      val gameState = new GameState
      val card = CardFactory.createCard("Scout")
      val drawnCard = gameState.drawCard("Scout")
    }
  }
} */