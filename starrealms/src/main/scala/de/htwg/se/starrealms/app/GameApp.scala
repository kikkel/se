package de.htwg.se.starrealms.app

import de.htwg.se.starrealms.controller._
import de.htwg.se.starrealms.model._
import de.htwg.se.starrealms.view.ConsoleView

class GameApp(inputProvider: () => String, output: String => Unit = println) {
  def run(): Unit = {

    val decksByRole = LoadCards.loadFromResource(LoadCards.getCsvPath, "Core Set")

    val deckbuilder = new DeckBuilder()
    val director = new Director()
    val constructedDecks = director.constructDecks(deckbuilder, decksByRole)


    val tradeDeck = constructedDecks.getOrElse("Trade Deck", new Deck())
    val logic = new GameLogic(tradeDeck)
    val controller = new Controller()
    val commandHandler = new CommandHandler(controller)
    val view = new ConsoleView(commandHandler)

    var running = true
    while (running) {
      output(s"\n\n${view.render()}\n\nYour command:\n\n")
      running = view.processInput(inputProvider())
    }

    output("\n\nGame exited. Goodbye! #main\n\n")
  }
}

