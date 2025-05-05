package de.htwg.se.starrealms.model

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class TradeCardSpec extends AnyWordSpec with Matchers {
  "A TradeCard" should {
    val card = TradeCard.
    "have a name" in {
      val card = new TradeCard("Trade Card")
      card.getName should be("Trade Card")
    }

    "have a card type" in {
      val cardType = new CardType("Trade")
      val card = new TradeCard("Trade Card", cardType)
      card.getCardType should be(cardType)
    }
    "have a faction" in {
      val faction = new Faction("Trade Federation")
      val card = new TradeCard("Trade Card", new CardType("Trade"), faction)
      card.getFaction should be(faction)
    }
    
  }
}