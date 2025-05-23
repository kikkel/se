package de.htwg.se.starrealms.view

import de.htwg.se.starrealms.model._
import de.htwg.util._

trait Renderer[T] { def render(entity: T): String }

//class WelcomeRenderer extends Renderer

/* class OptionsMenuRender extends Renderer[OptionsMenu] {
  override def render(menu: OptionsMenu): String = {
    val options = menu.getOptions.map(_.render()).mkString("\n")
    s"Options Menu:\n$options"
  }
}
class MainMenuRenderer extends Renderer[MainMenu] {
  override def render(menu: MainMenu): String = {
    val options = menu.getOptions.map(_.render()).mkString("\n")
    s"Main Menu:\n$options"
  }
}
 */

class CardRenderer extends Observable with Renderer[Card] {
  override def render(card: Card): String = card match {
    case default: DefaultCard => renderDefaultCard(default)
    case explorer: ExplorerCard => renderExplorerCard(explorer)
    case faction: FactionCard => renderFactionCard(faction)
  }

  private def renderDefaultCard(card: DefaultCard): String =
    s"""
       |  Name: ${card.cardName}
       |  Type: ${card.cardType}
       |  Ability: ${renderAbility(card.primaryAbility)}
       |""".stripMargin

  private def renderExplorerCard(card: ExplorerCard): String =
    s"""
       |  Name: ${card.cardName}
       |  Cost: ${card.cost}
       |  Type: ${card.cardType}
       |  Ability: ${renderAbility(card.primaryAbility)}
       |  Scrap Ability: ${renderAbility(card.scrapAbility)}
       |""".stripMargin

  private def renderFactionCard(card: FactionCard): String =
    s"""
       |  Name: ${card.cardName}
       |  Type: ${card.cardType}
       |  Cost: ${card.cost}
       |  Primary Ability: ${renderAbility(card.primaryAbility)}
       |  Ally Ability: ${renderAbility(card.allyAbility)}
       |  Scrap Ability: ${renderAbility(card.scrapAbility)}
       |  Faction: ${card.faction.factionName}
       |""".stripMargin

  private def renderAbility(ability: Option[Ability]): String =
    ability.map(_.render()).getOrElse("None")
}

/* class DeckRenderer extends Renderer[Deck] {
  override def render(deck: Deck): String = {
    val cards = deck.getCards.map(card => new CardRenderer().render(card)).mkString("\n")
    s"Deck Name: ${deck.getName}\nCards:\n$cards"
  }
}

class GameStateRenderer extends Renderer[GameLogic] { override def render(gameLogic: GameLogic): String = gameLogic.getDeckState } */