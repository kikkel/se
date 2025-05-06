package de.htwg.se.starrealms.view

import de.htwg.se.starrealms.model._
import de.htwg.util._

trait Renderer[T] {
  def render(entity: T): String
}

class CardRenderer extends Observable with Renderer[Card] {
  override def render(card: Card): String =
    s"Card Name: ${card.getName}, Card Type: ${card.getCardType}, Abilities: ${card.getPrimaryAbility.map(_.getActions.mkString(", ")).getOrElse("None")}"
}

class DeckRenderer extends Renderer[Deck] {
  override def render(deck: Deck): String = {
    val cards = deck.getCards.map(card => new CardRenderer().render(card)).mkString("\n")
    s"Deck Name: ${deck.getName}\nCards:\n$cards"
  }
}

class GameStateRenderer extends Renderer[GameLogic] {
  override def render(gameLogic: GameLogic): String = gameLogic.optionsMenu()
}