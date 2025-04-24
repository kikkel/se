package de.htwg.se.starrealms.model

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class AbilitySpec extends AnyWordSpec with Matchers {

    "An Ability" should {
        "hold a list of actions" in {
        val ability = new Ability(List("Attack", "Trade"))
        ability.actions should contain("Attack")
        ability.actions should contain("Trade")
        }

        "be empty if no actions are provided" in {
        val ability = new Ability(List())
        ability.hasActions should be(false)
        }

        "indicate it has actions if actions are present" in {
        val ability = new Ability(List("Attack", "Trade"))
        ability.hasActions should be(true)
        }
  }
  
}
