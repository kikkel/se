/* package de.htwg.se.starrealms.model.CardItinerary

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

object CardItinerarySpec extends AnyWordSpec with Matchers {

  "CardItineraryApp" should {
    "load cards from file" in {
      CardItinerary.loadCardsFromFile()
      val cards = CardItinerary.getCardsForSet("CoreSet")
      cards should not be empty
    }

    "return an empty list for a non-existent set" in {
      val cards = CardItinerary.getCardsForSet("NonExistentSet")
      cards shouldBe empty
    }
  }
} */