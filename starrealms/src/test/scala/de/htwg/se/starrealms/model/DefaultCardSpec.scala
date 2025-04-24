package de.htwg.se.starrealms.model

import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers

class DefaultCardSpec extends AnyWordSpec with Matchers {


  "A ViperCard" should {
    "have a name" in {
      val card = new ViperCard()
      card.name should be("Viper")
    }
    "be a Viper" in {
      val card = new ViperCard()
      card.getName should be("Viper")
    }
    "have an ability with 1 damage" in {
      val card = new ViperCard()
      card.getAbility.getActions should contain("1 damage")
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
    "have an ability with 1 coin" in {
      val card = new ScoutCard()
      card.getAbility.getActions should contain("1 coin")
    }
    "not be null" in {
      val card = new ScoutCard()
      card should not be null
    }
  }
}