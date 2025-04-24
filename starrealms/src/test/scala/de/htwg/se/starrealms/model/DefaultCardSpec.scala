package de.htwg.se.starrealms.model

import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers

class DefaultCardSpec extends AnyWordSpec with Matchers {

  "A DefaultCard" should {
    "either be a Scout or a Viper" in {
      val scout = new DefaultCard("Scout")
      val viper = new DefaultCard("Viper")
      scout.name should be("Scout")
      viper.name should be("Viper")
    }

  }
  "A ViperCard" should {
    "have a name" in {
      val card = new ViperCard()
      card.name should be("Viper")
    }
    "be a Viper" in {
      val card = new ViperCard()
      card.getName should be("Viper")
    }
    "have a type" in {
      val card = new ViperCard()
      card.getType should be("Combat")
    }
    "have a value in damage" in {
      val card = new ViperCard()
      card.getValue should be(1)
    }
    "not be null" in {
      val card = new ViperCard()
      card should not be null
    }
  }
  "A ScoutCard" should {
    "have a name" in {
      val card = new ScoutCard()
      card.name should be("Scout")
    }
    "be a Scout" in {
      val card = new ScoutCard()
      card.getName should be("Scout")
    }
    "have a type" in {
      val card = new ScoutCard()
      card.getType should be("Trade")
    }
    "have a value" in {
      val card = new ScoutCard()
      card.getValue should be(1)
    }
    "not be null" in {
      val card = new ScoutCard()
      card should not be null
    }
  }
}