package de.htwg.se.starrealms

import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers
import GameModel._ // <- das lädt alles aus deiner Hauptdatei

class GameSpec extends AnyWordSpec with Matchers {

  val sampleDeck: List[Card] = List(
    Card("Trade Pod", Blob, Ship, 2, Some(Ability("Gain 3 Trade")), Some(AllyAbility("Gain extra 2 Trade"))),
    Card("Battle Station", MachineCult, Outpost, 3, Some(Ability("4 Defense")), Some(AllyAbility("Gain 2 Combat")), Some(ScrapAbility("Gain 3 Combat"))),
    Card("Freighter", TradeFederation, Ship, 4, Some(Ability("Gain 4 Trade"))),
    Card("Survey Ship", StarEmpire, Ship, 3, Some(Ability("Draw a card")), Some(AllyAbility("Gain 1 Combat")))
  )

  val player1 = Player("Player 1", sampleDeck)
  val player2 = Player("Player 2", sampleDeck)

  "Game setup" should {
    "create players with correct names and deck sizes" in {
      player1.name shouldBe "Player 1"
      player2.name shouldBe "Player 2"
      player1.deck.length shouldBe 4
      player2.deck.length shouldBe 4
    }

    "correctly assign card properties" in {
      val card = sampleDeck.head
      card.name shouldBe "Trade Pod"
      card.faction shouldBe Blob
      card.cardtype shouldBe Ship
      card.cost shouldBe 2
    }

    "handle cards with all ability types" in {
      val card = sampleDeck(1)
      card.scrapability.map(_.description) shouldBe Some("Gain 3 Combat")
    }

    "have empty discard pile and hand initially" in {
      player1.discardPile shouldBe empty
      player1.hand shouldBe empty
    }
  }
}