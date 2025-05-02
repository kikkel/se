package de.htwg.se.starrealms.controller

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class GameStateSpec extends AnyWordSpec with Matchers {
  "A GameState" should {
    "have a player" in {
      val player = new Player("Player1")
      val gameState = new GameState(player)
      gameState.getPlayer should be(player)
    }

    "have a deck" in {
      val deck = new Deck()
      val gameState = new GameState(new Player("Player1"), deck)
      gameState.getDeck should be(deck)
    }

    "have a discard pile" in {
      val discardPile = new DiscardPile()
      val gameState = new GameState(new Player("Player1"), new Deck(), discardPile)
      gameState.getDiscardPile should be(discardPile)
    }
  }
}