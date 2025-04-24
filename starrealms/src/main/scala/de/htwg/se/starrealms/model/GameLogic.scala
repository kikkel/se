package de.htwg.se.starrealms.model

class GameLogic {
  private var deck: List[String] = List.fill(8)("Scout") ++ List.fill(2)("Viper")
  private var field: List[String] = List()

  def drawField(): String = {
	val deckState = if (deck.nonEmpty) deck.mkString(", ") else "Empty"
	val fieldState = if (field.nonEmpty) field.mkString(", ") else "Empty"
	s"Deck: $deckState\nField: $fieldState"
  }

  def turnOverCard(): String = {
	if (deck.nonEmpty) {
	  val card = deck.head
	  deck = deck.tail
	  field = field :+ card
	  s"Turned over card: $card"
	} else {
	  "The deck is empty! No more cards to turn over."
	}
  }

  def resetGame(): Unit = {
	deck = List.fill(8)("Scout") ++ List.fill(2)("Viper")
	field = List()
  }

}

