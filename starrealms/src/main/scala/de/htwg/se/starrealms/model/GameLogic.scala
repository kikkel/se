package de.htwg.se.starrealms.model

class GameLogic (val playingfield: PlayingField){
  private var deck: List[String] = List.fill(8)("Scout") ++ List.fill(2)("Viper")
  private var field: List[String] = List()

  def drawField(): String = {
	val deckState = if (deck.nonEmpty) deck.mkString(", ") else "Empty"
	val fieldState = if (field.nonEmpty) field.mkString(", ") else "Empty"
	s"Deck: $deckState\nField: $fieldState"
  }

  def turnOverCard(userInput: String): String = {
  val scoutIndex = deck.indexWhere(_.toLowerCase.contains("scout"))
  val viperIndex = deck.indexWhere(_.toLowerCase.contains("viper"))

  userInput.toLowerCase match {
    case "s" =>
      if (scoutIndex != -1) {
        val card = deck(scoutIndex)
        deck = deck.patch(scoutIndex, Nil, 1)
        field = field :+ card
        s"Turned over Scout: $card"
      } else {
        "No Scout cards left in the deck."
      }

    case "v" =>
      if (viperIndex != -1) {
        val card = deck(viperIndex)
        deck = deck.patch(viperIndex, Nil, 1)
        field = field :+ card
        s"Turned over Viper: $card"
      } else {
        "No Viper cards left in the deck."
      }

    case _ =>
      "Invalid input. Please enter 's' for Scout or 'v' for Viper."
  }
}

  def resetGame(): Unit = {
	deck = List.fill(8)("Scout") ++ List.fill(2)("Viper")
	field = List()
  }

}

