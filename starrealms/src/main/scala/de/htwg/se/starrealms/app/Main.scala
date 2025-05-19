package de.htwg.se.starrealms.app

object Main extends App {
  println("Welcome to Star Realms!")
  new GameApp(() => scala.io.StdIn.readLine()).run()
}