package de.htwg.se.starrealms.model

import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers
import de.htwg.se.starrealms.model.AbstractCardSpec
import de.htwg.se.starrealms.model.AbstractCard

class DefaultCardSpec extends AnyWordSpec with Matchers {
  "A DefaultCard" should {
    "inherit from AbstractCard" in {
      val card = new DefaultCard("name", new Ability(List())) // Check if it is an instance of DefaultCard
      card shouldBe a[AbstractCard] // Check if it is an instance of AbstractCard
    }
    "either be a ViperCard or a ScoutCard" in {
      val card = new DefaultCard("name", new Ability(List()))
      assert(card.isInstanceOf[ViperCard] || card.isInstanceOf[ScoutCard])
    }
    "have a default ability" in {
      val card = new DefaultCard("name", new Ability(List()))
      card.getAbility should not be null
    }

  }
  "A ViperCard" should {
    "be a DefaultCard" in {
      val card = new ViperCard()
      card shouldBe a[DefaultCard]
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
    "be a DefaultCard" in {
      val card = new ScoutCard()
      card shouldBe a[DefaultCard]
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