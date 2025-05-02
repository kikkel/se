package de.htwg.se.starrealms.view

import de.htwg.se.starrealms.model._
import de.htwg.util._

trait Renderer[T] {
  def render(entity: T): String
}

class CardRenderer extends Observable with Renderer[AbstractCard] {
  override def render(card: AbstractCard): String = 
    s"Card Name: ${card.getName}, Card Type: ${card.getCardType.getName}, Abilities: ${card.getAbility.getActions.mkString(", ")}"
}

class DeckRenderer extends Renderer[AbstractDeck] {
  override def render(deck: AbstractDeck): String = {
    val cards = deck.getCards.map(card => new CardRenderer().render(card)).mkString("\n")
    s"Deck Name: ${deck.getName}\nCards:\n$cards"
  }
}

class GameStateRenderer extends Renderer[GameLogic] {
  override def render(gameLogic: GameLogic): String = gameLogic.optionsMenu()
}