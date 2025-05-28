package de.htwg.se.starrealms.app

object Main extends App {
  println("\n\nWelcome to Star Realms!\n\n")
  new GameApp(() => scala.io.StdIn.readLine()).run()
}