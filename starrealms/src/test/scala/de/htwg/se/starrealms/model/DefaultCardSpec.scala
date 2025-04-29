package de.htwg.se.starrealms.model

import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers
import de.htwg.se.starrealms.model.AbstractCardSpec
import de.htwg.se.starrealms.model.AbstractCard



class DefaultCardSpec extends AnyWordSpec with Matchers {
  "A DefaultCard" should {
    "be created with a name and an ability" in {
      val ability = new Ability(List("TestAbility"))
      val card = new DefaultCard("TestCard", ability)
      card.getName should be("TestCard")
      card.getAbility should be(ability)
    }

    "be created with a name and a default empty ability" in {
      val card = new DefaultCard("TestCard")
      card.getName should be("TestCard")
      card.getAbility.getActions shouldBe empty
    }
  }

  "A ViperCard" should {
    "be a DefaultCard" in {
      val card = new ViperCard()
      card shouldBe a[DefaultCard]
    }

    "have the name 'Viper'" in {
      val card = new ViperCard()
      card.getName should be("Viper")
    }

    "have an ability with '1 damage'" in {
      val card = new ViperCard()
      card.getAbility.getActions should contain("1 damage")
    }

    "have a proper toString implementation" in {
      val card = new ViperCard()
      card.toString should be("ViperCard(name=Viper, ability=Ability(actions=List(1 damage)))")
    }
  }

  "A ScoutCard" should {
    "be a DefaultCard" in {
      val card = new ScoutCard()
      card shouldBe a[DefaultCard]
    }

    "have the name 'Scout'" in {
      val card = new ScoutCard()
      card.getName should be("Scout")
    }

    "have an ability with '1 coin'" in {
      val card = new ScoutCard()
      card.getAbility.getActions should contain("1 coin")
    }

    "have a proper toString implementation" in {
      val card = new ScoutCard()
      card.toString should be("ScoutCard(name=Scout, ability=Ability(actions=List(1 coin)))")
    }
  }
}

/* class DefaultCardSpec extends AnyWordSpec with Matchers {


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
} */