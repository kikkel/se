package de.htwg.se.starrealms.model

import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers

class PlayerSpec extends AnyWordSpec with Matchers {
  "A Player" should {
    "have a name and default health" in {
      val player = Player("Alice")
      player.name shouldBe "Alice"
      player.health shouldBe 3
    }

    "take damage and not go below zero" in {
      val player = Player("Bob", health = 5)
      player.takeDamage(2)
      player.health shouldBe 3
      player.takeDamage(5)
      player.health shouldBe 0
    }

    "heal and increase health" in {
      val player = Player("Carol", health = 1)
      player.heal(2)
      player.health shouldBe 3
    }

    "report if alive" in {
      val player = Player("Dave", health = 1)
      player.isAlive shouldBe true
      player.takeDamage(1)
      player.isAlive shouldBe false
    }

    "have a proper toString" in {
      val player = Player("Eve", health = 7)
      player.toString shouldBe "Eve (Leben: 7)"
    }
  }
}
