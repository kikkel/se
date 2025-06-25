package de.htwg.se.starrealms.app

import de.htwg.se.starrealms.di.StarRealmsModule
import com.google.inject.Guice
import com.google.inject.Key

import de.htwg.se.starrealms.model.PlayerComponent.impl._
import de.htwg.se.starrealms.model.GameCore.impl._
import de.htwg.se.starrealms.view.ConsoleView
import de.htwg.se.starrealms.model.FileIOComponent.FileIOInterface

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
import de.htwg.se.starrealms.model.PlayerComponent.PlayerInterface
import com.google.inject.TypeLiteral

object GameApp extends JFXApp3 {
  val injector = Guice.createInjector(new StarRealmsModule())
  @volatile var running = true

  override def start(): Unit = {
    val director: DeckDirectorInterface = injector.getInstance(classOf[DeckDirectorInterface])
    val builderFactory: Builder = injector.getInstance(classOf[Builder])

    val playerListKey = Key.get(new TypeLiteral[List[PlayerInterface]]() {})
    val players: List[PlayerInterface] = injector.getInstance(playerListKey)
    val player1 = players(0)
    val player2 = players(1)

    val gameState: GameStateInterface = injector.getInstance(classOf[GameStateInterface])
    val gameLogic: GameLogicInterface = injector.getInstance(classOf[GameLogicInterface])
    val mediator: GameMediator = injector.getInstance(classOf[GameMediator])
    val controller: ControllerInterface = injector.getInstance(classOf[ControllerInterface])
    val commandAdapter: CommandAdapter = injector.getInstance(classOf[CommandAdapter])
    val proxy: GameStateReadOnly = injector.getInstance(classOf[GameStateReadOnly])
    val view = injector.getInstance(classOf[ConsoleView])
    val gui = injector.getInstance(classOf[GraphicUI])

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

    // FileIO-Test (optional, kann entfernt werden)
    val fileIO: FileIOInterface = injector.getInstance(classOf[FileIOInterface])
    val testPlayers = List(new Player("Player1", 100), new Player("Player2", 100))
    fileIO.save(testPlayers, "players.json")
    val loaded = fileIO.load("players.json")
    println("Loaded players: " + loaded.map(_.getName))
  }
}