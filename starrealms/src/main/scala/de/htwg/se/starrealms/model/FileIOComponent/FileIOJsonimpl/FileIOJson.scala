package de.htwg.se.starrealms.model.FileIOComponent.FileIOJsonimpl

import de.htwg.se.starrealms.model.PlayerComponent.PlayerInterface
import de.htwg.se.starrealms.model.FileIOComponent.FileIOInterface
import play.api.libs.json._
import java.io.{File, PrintWriter}
import scala.io.Source

class FileIOJson extends FileIOInterface {

  // Serialisierung eines Players zu JSON
  def playerToJson(player: PlayerInterface): JsObject = Json.obj(
    "name" -> player.getName,
    "health" -> player.getHealth,
    "deckSize" -> player.getDeckSize,
    "discardSize" -> player.getDiscardSize,
    "handSize" -> player.getHandSize
  )

  // Deserialisierung eines Players aus JSON
  def jsonToPlayer(json: JsValue): PlayerInterface = {
    val name = (json \ "name").as[String]
    val health = (json \ "health").as[Int]
    val deckSize = (json \ "deckSize").as[Int]
    val discardSize = (json \ "discardSize").as[Int]
    val handSize = (json \ "handSize").as[Int]
    // Passe ggf. die Player-Implementierung an!
    val player = new de.htwg.se.starrealms.model.PlayerComponent.impl.Player(name, health)
    player.setDeckSize(deckSize)
    player.setDiscardSize(discardSize)
    player.setHandSize(handSize)
    player
  }

  // Speichern einer Liste von Spielern als JSON-Datei
  override def save(players: List[PlayerInterface], filename: String): Unit = {
    val json = Json.obj(
      "players" -> players.map(playerToJson)
    )
    val pw = new PrintWriter(new File(filename))
    pw.write(Json.prettyPrint(json))
    pw.close()
  }

  // Laden einer Liste von Spielern aus einer JSON-Datei
  override def load(filename: String): List[PlayerInterface] = {
    val source = Source.fromFile(filename)
    val jsonString = try source.mkString finally source.close()
    val json = Json.parse(jsonString)
    (json \ "players").as[List[JsValue]].map(jsonToPlayer)
  }
}