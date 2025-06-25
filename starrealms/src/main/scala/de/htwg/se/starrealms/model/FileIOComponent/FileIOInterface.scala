package de.htwg.se.starrealms.model.FileIOComponent

import de.htwg.se.starrealms.model.PlayerComponent.PlayerInterface

trait FileIOInterface {
  def save(players: List[PlayerInterface], filename: String): Unit
  def load(filename: String): List[PlayerInterface]
}


