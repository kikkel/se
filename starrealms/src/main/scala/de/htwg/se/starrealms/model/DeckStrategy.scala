package de.htwg.se.starrealms.model

trait DeckStrategy {
  def buildDeck(deckBuilder: DeckBuilder): Deck
}

class StandardDeckStrategy extends DeckStrategy {
  override def buildDeck(deckBuilder: DeckBuilder): Deck =
    deckBuilder.buildFromCSV("src/main/resources/StandardDeck.csv")

}
