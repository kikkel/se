package de.htwg.se.starrealms.app

import de.htwg.se.starrealms.di.StarRealmsModule
import com.google.inject.Guice


import de.htwg.se.starrealms.model.PlayerComponent.impl._
import de.htwg.se.starrealms.model.GameCore.impl._



import de.htwg.se.starrealms.model.GameStateComponent.GameStateReadOnly
import de.htwg.se.starrealms.model.GameCore.{DeckInterface, Builder, DeckDirectorInterface}

import de.htwg.se.starrealms.view._



import scalafx.application.JFXApp3
import scalafx.scene.Scene
import de.htwg.se.starrealms.model.GameStateComponent.impl.GameState
import de.htwg.se.starrealms.model.GameStateComponent.GameStateInterface
import de.htwg.se.starrealms.controller.GameLogicComponent.GameLogicInterface
import de.htwg.se.starrealms.controller.GameMediatorComponent.GameMediator
import de.htwg.se.starrealms.controller.ControllerComponent.ControllerInterface

object GameApp extends JFXApp3 {
  val injector = Guice.createInjector(new StarRealmsModule())
  @volatile var running = true

  override def start(): Unit = {
    val director: DeckDirectorInterface = injector.getInstance(classOf[DeckDirectorInterface])
    val builderFactory: Builder = injector.getInstance(classOf[Builder])

    val ki_filePath: String = "/Users/kianimoon/se/se/starrealms/src/main/resources/PlayableSets.csv"
    //val ki_filePath: String = "/Users/koeseazra/SE-uebungen/se/starrealms/src/main/resources/PlayableSets.csv"

    val csvLoader = new CardCSVLoader(sys.env.getOrElse("CARDS_CSV_PATH", s"$ki_filePath"))
    val loadCards = new LoadCards(builderFactory, director, csvLoader)
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

    val gameState: GameStateInterface = injector.getInstance(classOf[GameStateInterface])
    val gameLogic: GameLogicInterface = injector.getInstance(classOf[GameLogicInterface])
    val mediator: GameMediator = injector.getInstance(classOf[GameMediator])
    val controller: ControllerInterface = injector.getInstance(classOf[ControllerInterface])
    val commandAdapter: CommandAdapter = injector.getInstance(classOf[CommandAdapter])
    val proxy: GameStateReadOnly = injector.getInstance(classOf[GameStateReadOnly])
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