/* package de.htwg.se.starrealms.model

import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers

class AbilitySpec extends AnyWordSpec with Matchers {

  "A SimpleAction" should {
    "print its description and return correct description" in {
      val action = SimpleAction("Gain 2 coins")
      noException should be thrownBy action.doMove
      action.description shouldBe "Gain 2 coins"
    }
  }

  "A ConditionalAction" should {
    "execute both condition actions and return a combined description" in {
      val a1 = SimpleAction("Deal 3 damage")
      val a2 = SimpleAction("Heal 2")
      val condAction = ConditionalAction(a1, a2)

      noException should be thrownBy condAction.doMove
      condAction.description should include("Condition")
      condAction.description should include(a1.toString)
      condAction.description should include(a2.toString)
    }
  }

  "A TriggeredAction" should {
    "print trigger info and execute its inner action" in {
      val action = SimpleAction("Draw a card")
      val triggered = TriggeredAction("OnPlay", action)

      noException should be thrownBy triggered.doMove
      triggered.description should include("Triggered by OnPlay")
      triggered.description should include(action.toString)
    }
  }

  "A CompositeAction" should {
    "execute all contained actions and return combined description" in {
      val a1 = SimpleAction("Gain 1 coin")
      val a2 = SimpleAction("Deal 2 damage")
      val composite = CompositeAction(List(a1, a2))

      noException should be thrownBy composite.doMove
      composite.description should include("Composite action")
      composite.description should include(a1.description)
      composite.description should include(a2.description)
    }
  }

  "An Ability" should {
    "return its list of actions and execute them" in {
      val actions = List(SimpleAction("Test 1"), SimpleAction("Test 2"))
      val ability = new Ability(actions)

      ability.getActions shouldEqual actions
      ability.hasActions shouldBe true
      noException should be thrownBy ability.executeActions()
      ability.render() should include("SimpleAction")
    }

    "handle an empty action list gracefully" in {
      val emptyAbility = new Ability(List())
      emptyAbility.getActions shouldBe empty
      emptyAbility.hasActions shouldBe false
      noException should be thrownBy emptyAbility.executeActions()
      emptyAbility.render() shouldBe "No actions available"
    }
  }

  "A PrimaryAbility" should {
    "render actions correctly" in {
      val primary = PrimaryAbility(List(SimpleAction("Primary")))
      primary.render() should include("SimpleAction")
    }

    "render fallback message when empty" in {
      val primary = PrimaryAbility(List())
      primary.render() shouldBe "No primary actions available"
    }
  }

  "An AllyAbility" should {
    "render actions correctly" in {
      val ally = AllyAbility(List(SimpleAction("Ally")))
      ally.render() should include("SimpleAction")
    }

    "render fallback message when empty" in {
      val ally = AllyAbility(List())
      ally.render() shouldBe "No ally actions available"
    }
  }

  "A ScrapAbility" should {
    "render actions correctly" in {
      val scrap = ScrapAbility(List(SimpleAction("Scrap")))
      scrap.render() should include("SimpleAction")
    }

    "render fallback message when empty" in {
      val scrap = ScrapAbility(List())
      scrap.render() shouldBe "No scrap actions available"
    }
  }
} */