package de.htwg.se.starrealms.model.random

import scala.collection.mutable.ListBuffer
import de.htwg.util.Observable

//--------------------------------------------------------------------Menu
class OptionItem(val name: String, val action: () => Unit) {
  def render(): String = s"Option: $name"
  def execute(): Unit = action()
}
class OptionsMenu extends Observable {
  private val options = ListBuffer[OptionItem]()

  def addOption(option: OptionItem): Unit = {
    options += option
    notifyObservers()
  }

  def removeOption(option: OptionItem): Unit = {
    options -= option
    notifyObservers()
  }

  def getOptions: List[OptionItem] = options.toList

  def render(): String = {
    val optionsString = options.map(_.render()).mkString("\n")
    s"Options Menu:\n$optionsString"
  }
}
class MainMenu extends Observable {
  private val options = ListBuffer[OptionItem]()

  def addOption(option: OptionItem): Unit = {
    options += option
    notifyObservers()
  }

  def removeOption(option: OptionItem): Unit = {
    options -= option
    notifyObservers()
  }

  def getOptions: List[OptionItem] = options.toList

  def render(): String = {
    val optionsString = options.map(_.render()).mkString("\n")
    s"Main Menu:\n$optionsString"
  }
}
