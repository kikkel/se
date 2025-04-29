package de.htwg.se.starrealms.model

//import de.htwg.se.starrealms.model.TradeDeck
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec
//import org.scalatest.BeforeAndAfterEach


class TradeDeckSpec extends AnyWordSpec with Matchers /* with BeforeAndAfterEach */ {
  //  var tradeDeck: TradeDeck = _

  "A TradeDeck" should {
    "be initialized with a deck of cards" in {
      val tradeDeck = new TradeDeck()
      tradeDeck.cards.size should be > 0
    }

    "allow drawing a card" in {
      val tradeDeck = new TradeDeck()
      val initialSize = tradeDeck.cards.size
      val drawnCard = tradeDeck.drawCard()
      drawnCard should not be empty
      tradeDeck.cards.size should be < initialSize
    }
  }
}