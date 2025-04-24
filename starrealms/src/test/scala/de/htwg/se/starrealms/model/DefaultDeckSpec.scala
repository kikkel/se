package de.htwg.se.starrealms.model

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class DefaultDeckSpec extends AnyWordSpec with Matchers {

"A DefaultDeck" should {
    "have 8 ScoutCards" in {
        val deck = new DefaultDeck()
        deck.getScoutCards.size should be(8)
    }
    "have 2 ViperCards" in {
        val deck = new DefaultDeck()
        deck.getViperCards.size should be(2)
    }
}
}

