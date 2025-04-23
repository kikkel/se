package de.htwg.se.starrealms.model

import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers

class SetUpSpec extends AnyWordSpec with Matchers {

    "The SetUp" should {
        "have a ActionSpace" in {
        val setUp = new SetUp()
        val actionSpace = setUp.actionSpace
        actionSpace should not be null
        }

        "have a TradeSpace" in {
        val setUp = new SetUp()
        val tradeSpace = setUp.tradeSpace
        tradeSpace should not be null
        }
    }
    "The ActionSpace" should {
        "have a PlayerDeck" in {
            val setUp = new SetUp()
            val playerDeck = setUp.actionSpace.playerDeck
            playerDeck should not be null
        }
        "have a PlayerDiscard" in {
            val setUp = new SetUp()
            val playerDiscard = setUp.actionSpace.playerDiscard
            playerDiscard should not be null
        }
        "have a Hand" in {
            val setUp = new SetUp()
            val hand = setUp.actionSpace.hand
            hand should not be null
        }
        "have a AuthorityNumber" in {
            val setUp = new SetUp()
            val authority = setUp.actionSpace.authority
            authority should not be null
        }
    }
    "The TradeSpace" should {
        "have a TradeRow" in {
            val setUp = new SetUp()
            val tradeRow = setUp.tradeSpace.tradeRow
            tradeRow should not be null
        }
        "have a TradeDeck" in {
            val setUp = new SetUp()
            val tradeDeck = setUp.tradeSpace.tradeDeck
            tradeDeck should not be null
        }
        "have a DiscardPile" in {
            val setUp = new SetUp()
            val tradeDiscard = setUp.tradeSpace.tradeDiscard
            tradeDiscard should not be null
        }
        "have a ExplorerDeck" in {
            val setUp = new SetUp()
            val explorerDeck = setUp.tradeSpace.explorerDeck
            explorerDeck should not be null
        }
    }
}


