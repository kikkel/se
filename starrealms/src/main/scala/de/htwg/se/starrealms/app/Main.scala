package de.htwg.se.starrealms.app

import de.htwg.se.starrealms.controller._
import de.htwg.se.starrealms.model._
import de.htwg.se.starrealms.view.ConsoleView

object Main extends App {

  //println("Welcome to Star Realms!")

  val csvLoader = new CardCSVLoader("src/main/resources/CoreSet.csv")
  csvLoader.loadCardsFromFile()
  val cards = csvLoader.getCardsForSet("Core Set")

  val deckbuilder = new DeckBuilder()
  val director = new Director()
  director.constructTradeDeck(deckbuilder, "Core Set", cards)
  val deck = deckbuilder.getProduct()

  val logic = new GameLogic(deck)
  val controller = new Controller(logic)
  val view = new ConsoleView(controller)

  while (true) {
    view.render()
    println("Options:\n\t's' draw Scout\n\t" +
      "'v' draw Viper\n\t'r' reset game\n\t" +
      "'x' quit game\n\t #main")
      if (!view.processInput(scala.io.StdIn.readLine())) {
        println("\n\nGame exited. Goodbye! #main\n\n")
        sys.exit()
      }
  }
}

