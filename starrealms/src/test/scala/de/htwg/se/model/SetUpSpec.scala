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
}


