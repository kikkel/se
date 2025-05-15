package de.htwg.se.starrealms.view

import de.htwg.se.starrealms.model._
import de.htwg.util._

trait Renderer[T] { def render(entity: T): String }

class CardRenderer extends Observable with Renderer[Card] {
  override def render(card: Card): String =
    card match {
      case default: DefaultCard =>
        s"Default Card: ${default.cardName}, Type: ${default.cardType}, Ability: ${default.primaryAbility.map(_.render()).getOrElse("None")}"
      case explorer: ExplorerCard =>
        s"Explorer Card: ${explorer.cardName}, Type: ${explorer.cardType}, Ability: ${explorer.primaryAbility.map(_.render()).getOrElse("None")}, Scrap Ability: ${explorer.scrapAbility.map(_.render()).getOrElse("None")}"
      case faction: FactionCard =>
        s"Faction Card: ${faction.cardName}, Type: ${faction.cardType}, Cost: ${faction.cost}, " +
          s"Primary Ability: ${faction.primaryAbility.map(_.render()).getOrElse("None")}, " +
          s"Ally Ability: ${faction.allyAbility.map(_.render()).getOrElse("None")}, " +
          s"Scrap Ability: ${faction.scrapAbility.map(_.render()).getOrElse("None")}"
    }
}

class DeckRenderer extends Renderer[Deck] {
  override def render(deck: Deck): String = {
    val cards = deck.getCards.map(card => new CardRenderer().render(card)).mkString("\n")
    s"Deck Name: ${deck.getName}\nCards:\n$cards"
  }
}

class GameStateRenderer extends Renderer[GameLogic] { override def render(gameLogic: GameLogic): String = gameLogic.getDeckState }