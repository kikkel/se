package de.htwg.se.starrealms.app

object Main extends App {
  new GameApp(() => scala.io.StdIn.readLine()).run()
}