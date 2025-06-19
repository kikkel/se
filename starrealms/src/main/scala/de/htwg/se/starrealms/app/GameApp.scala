package de.htwg.se.starrealms.app

import de.htwg.se.starrealms.model.GameStateComponent.impl._
import de.htwg.se.starrealms.model.PlayerComponent.impl._
import de.htwg.se.starrealms.model.GameCore.impl._
import de.htwg.se.starrealms.controller.ControllerComponent.impl._
import de.htwg.se.starrealms.controller.GameLogicComponent.impl._
import de.htwg.se.starrealms.controller.GameMediatorComponent.impl._
import de.htwg.se.starrealms.controller.ControllerComponent.structure._


import de.htwg.se.starrealms.model.GameStateComponent.GameStateReadOnly
import de.htwg.se.starrealms.model.GameCore.{DeckInterface, Builder}

import de.htwg.se.starrealms.view._



import scalafx.application.JFXApp3
import scalafx.scene.Scene

object GameApp extends JFXApp3 {
  @volatile var running = true

  override def start(): Unit = {
    val director = new DeckDirector()
    val builderFactory: Builder = new DeckBuilder(new Deck())

    //val ki_filePath: String = "/Users/kianimoon/se/se/starrealms/src/main/resources/PlayableSets.csv"
    val ki_filePath: String = "/Users/koeseazra/SE-uebungen/se/starrealms/src/main/resources/PlayableSets.csv"

    val csvLoader = new CardCSVLoader(sys.env.getOrElse("CARDS_CSV_PATH", s"$ki_filePath"))
    val loadCards = new LoadCards(new DeckBuilder(new Deck()), director, csvLoader)
    val decksByRole = loadCards.load("Core Set")
    //val decksByRole = LoadCards.loadFromResource(LoadCards.getCsvPath, "Core Set", builderFactory, director)
    if (decksByRole.isEmpty) {
      println("No decks found. Exiting the game.")
      return
    }

    //debugging
    println("\n\nDecks by Role:")
    decksByRole.foreach { case (role, deck) =>
      println(s"Role: $role")
      println(s"Cards: ${deck.getCards.map { case (card, qty) => s"${card.cardName} x$qty" }.mkString(", ")}")
    }
    println("\n\n")

    println(s"\n\nDeck loaded: ${decksByRole.keys.mkString(", ")}\n\n")
    val player1 = Player("Player 1")
    val player2 = Player("Player 2")

    val gameState = new GameState(decksByRole, player1, player2, builderFactory, director)
    val gameLogic = new GameLogic(gameState)
    val mediator = new StarRealmsMediator(gameState, gameLogic, List(player1, player2))
    val controller = new Controller(mediator)
    val commandAdapter = new CommandProcessorAdapter(mediator, controller)
    val proxy: GameStateReadOnly = new GameStateProxy(gameState)
    val view = new ConsoleView(commandAdapter, proxy, gameLogic)
    val gui = new GraphicUI(commandAdapter, proxy, () => running = false)

    // TUI in separatem Thread starten
    new Thread(() => {
      while (running) {
        println(s"\n\n${view.render()}\n\nYour command:\n\n")
        running = view.processInput(scala.io.StdIn.readLine())
      }
      println("\n\nGame exited. Goodbye! #main\n\n")
      System.exit(0)
    }).start()


    gui.show()

    mediator.getGameState.addObserver(gui)
    mediator.getGameState.addObserver(view)

  }
}