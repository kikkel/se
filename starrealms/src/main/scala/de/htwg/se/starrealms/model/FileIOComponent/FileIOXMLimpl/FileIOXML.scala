package de.htwg.se.starrealms.model.FileIOComponent.FileIOXMLimpl

import de.htwg.se.starrealms.model.PlayerComponent.PlayerInterface
import de.htwg.se.starrealms.model.FileIOComponent.FileIOInterface
import scala.xml.{Elem, PrettyPrinter, XML}
import java.io.{File, PrintWriter}
import scala.io.Source

class FileIOXML extends FileIOInterface {

  // Serialisierung eines Players zu XML
  def playerToXml(player: PlayerInterface): Elem =
    <player>
      <name>{player.getName}</name>
      <health>{player.getHealth}</health>
      <deckSize>{player.getDeckSize}</deckSize>
      <discardSize>{player.getDiscardSize}</discardSize>
      <handSize>{player.getHandSize}</handSize>
    </player>

  // Deserialisierung eines Players aus XML
  def xmlToPlayer(node: scala.xml.Node): PlayerInterface = {
    val name = (node \ "name").text
    val health = (node \ "health").text.toInt
    val deckSize = (node \ "deckSize").text.toInt
    val discardSize = (node \ "discardSize").text.toInt
    val handSize = (node \ "handSize").text.toInt
    val player = new de.htwg.se.starrealms.model.PlayerComponent.impl.Player(name, health)
    player.setDeckSize(deckSize)
    player.setDiscardSize(discardSize)
    player.setHandSize(handSize)
    player
  }

  // Speichern einer Liste von Spielern als XML-Datei
  override def save(players: List[PlayerInterface], filename: String): Unit = {
    val xml =
      <players>
        {players.map(playerToXml)}
      </players>
    val printer = new PrettyPrinter(120, 4)
    val pw = new PrintWriter(new File(filename))
    pw.write(printer.format(xml))
    pw.close()
  }

  // Laden einer Liste von Spielern aus einer XML-Datei
  override def load(filename: String): List[PlayerInterface] = {
    val xml = XML.loadFile(filename)
    (xml \\ "player").map(xmlToPlayer).toList
  }
}