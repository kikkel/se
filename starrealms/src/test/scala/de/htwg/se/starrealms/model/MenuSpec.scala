package de.htwg.se.starrealms.model

import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers

class MenuSpec extends AnyWordSpec with Matchers {

  "An OptionItem" should {
    "render its name" in {
      val item = new OptionItem("Start", () => ())
      item.render() shouldBe "Option: Start"
    }
    "execute its action" in {
      var executed = false
      val item = new OptionItem("Test", () => executed = true)
      item.execute()
      executed shouldBe true
    }
  }

  "An OptionsMenu" should {
    "add, remove, get and render options" in {
      val menu = new OptionsMenu
      val item1 = new OptionItem("Play", () => ())
      val item2 = new OptionItem("Exit", () => ())
      menu.getOptions shouldBe empty

      menu.addOption(item1)
      menu.getOptions should contain(item1)
      menu.render() should include("Play")

      menu.addOption(item2)
      menu.getOptions should contain(item2)
      menu.render() should include("Exit")

      menu.removeOption(item1)
      menu.getOptions should not contain item1
      menu.render() should not include("Play")
    }
  }

  "A MainMenu" should {
    "add, remove, get and render options" in {
      val menu = new MainMenu
      val item1 = new OptionItem("Settings", () => ())
      val item2 = new OptionItem("Quit", () => ())
      menu.getOptions shouldBe empty

      menu.addOption(item1)
      menu.getOptions should contain(item1)
      menu.render() should include("Settings")

      menu.addOption(item2)
      menu.getOptions should contain(item2)
      menu.render() should include("Quit")

      menu.removeOption(item2)
      menu.getOptions should not contain item2
      menu.render() should not include("Quit")
    }
  }
}
