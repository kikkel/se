package de.htwg.se.starrealms.app

import de.htwg.se.starrealms.controller._
import de.htwg.se.starrealms.model._
import de.htwg.se.starrealms.view.ConsoleView

class GameApp(inputProvider: () => String, output: String => Unit = println) {
  def run(): Unit = {
    output("Welcome to Star Realms!")
    val cards = LoadCards.loadFromResource("FullCardItinerary.csv", "Core Set")

    val deckbuilder = new DeckBuilder()
    val director = new Director()
    director.constructTradeDeck(deckbuilder, "Core Set", cards)
    val deck = deckbuilder.getProduct()

    val logic = new GameLogic(deck)
    val controller = new Controller(logic)
    val view = new ConsoleView(controller, output)

    var running = true
    while (running) {
      view.render()
      output("Options:\n\t's' draw Scout\n\t'v' draw Viper\n\t'r' reset game\n\t'x' quit game\n\t #main")
      running = view.processInput(inputProvider())
    }

    output("\n\nGame exited. Goodbye! #main\n\n")
  }
}

