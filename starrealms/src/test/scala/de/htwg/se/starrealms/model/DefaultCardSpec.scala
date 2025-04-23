package de.htwg.se.starrealms.model

import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers

class DefaultCardSpec extends AnyWordSpec with Matchers {

  "A DefaultCard" should {
    "have a name" in {
      val card = new DefaultCard("TestCard")
      card.name should be("TestCard")
    }
/* 
    "either be a Scout or a Viper" in {
      val scout = new DefaultCard("Scout")
      val viper = new DefaultCard("Viper")
      scout.name should be("Scout")
      viper.name should be("Viper")
    }
    "not be null" in {
      val card = new DefaultCard("TestCard")
      card should not be null
    } */

  }
}