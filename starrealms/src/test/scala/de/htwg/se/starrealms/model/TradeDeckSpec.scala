/* package de.htwg.se.starrealms.model

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class TradeDeckSpec extends AnyWordSpec with Matchers {
    "A TradeDeck" should {
        "have a name" in {
            val deck = new TradeDeck("TradeDeck", List())
            deck.getName should be("TradeDeck")
        }

        "initialize with shuffled trade cards" in {
            val deck = new TradeDeck("TradeDeck", List())
            deck.getCards should not be empty
            deck.getCards should contain allElementsOf List(
                new TradeFederation(),
                new StarEmpire(),
                new Blob(),
                new MachineCult()
            )
        }
        "only contain cardType: Trade" in {
            val deck = new TradeDeckBuilder().build("TradeDeck")
            deck.getCards.foreach { card =>
                card.getCardType.getName should be("Trade")
            }
        }

        "format cards using a SimpleFormatter" in {
            val deck = new TradeDeckBuilder().build("TradeDeck")
            val formatter = new SimpleFormatter()
            val formattedCards = deck.formatCards(formatter)

            formattedCards should contain allElementsOf List(
                "TradeFederation (Trade)",
                "StarEmpire (Trade)",
                "Blob (Trade)",
                "MachineCult (Trade)"
            )
        }

        "format cards using a DetailedFormatter" in {
            val deck = new TradeDeckBuilder().build("TradeDeck")
            val formatter = new DetailedFormatter()
            val formattedCards = deck.formatCards(formatter)

            formattedCards should contain allElementsOf List(
                "Card Name: TradeFederation, Card Type: Trade",
                "Card Name: StarEmpire, Card Type: Trade",
                "Card Name: Blob, Card Type: Trade",
                "Card Name: MachineCult, Card Type: Trade"
            )
        }

    }

} */