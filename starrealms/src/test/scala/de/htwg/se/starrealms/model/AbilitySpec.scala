package de.htwg.se.starrealms.model

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec
import de.htwg.se.starrealms.model.Ability

class AbilitySpec extends AnyWordSpec with Matchers {

  "An Ability" should {
    "hold a list of actions" in {
      val ability = new Ability(List("Attack", "Trade"))
      ability.actions should contain("Attack")
      ability.actions should contain("Trade")
    }

    "return the list of actions" in {
      val ability = new Ability(List("Attack", "Trade"))
      ability.getActions shouldEqual List("Attack", "Trade")
    }

    "check if it has actions" in {
      val abilityWithActions = new Ability(List("Attack"))
      val emptyAbility = new Ability(List())

      abilityWithActions.hasActions shouldBe true
      emptyAbility.hasActions shouldBe false
    }

    /* "provide a string representation" in {
      val ability = new Ability(List("Attack"))
      ability.toString shouldEqual "Ability(actions=List(Attack))"
    } */
  }

}
