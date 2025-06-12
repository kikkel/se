package de.htwg.se.starrealms.view

import de.htwg.se.starrealms.model.GameCore.{Card, AbilityInterface}
import de.htwg.se.starrealms.model.PlayerComponent.PlayerInterface
import de.htwg.se.starrealms.model.GameStateComponent.{GameStateInterface, GameSnapshot, PlayerSnapshot}
import de.htwg.util._

import de.htwg.se.starrealms.model.GameCore.structure.{OptionsMenu, MainMenu}
import de.htwg.se.starrealms.model.GameCore.impl.{DefaultCard, ExplorerCard, FactionCard}

class OptionsMenuRender extends Renderer[OptionsMenu] {
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

  private def renderAbility(ability: Option[AbilityInterface]): String =
    ability.map(_.render).getOrElse("None")
}

/* class DeckRenderer extends Renderer[Deck] {
  override def render(deck: Deck): String = {
    val cards = deck.getCards.map(card => new CardRenderer().render(card)).mkString("\n")
    s"Deck Name: ${deck.getName}\nCards:\n$cards"
  }
}

class GameStateRenderer extends Renderer[GameLogic] { override def render(gameLogic: GameLogic): String = gameLogic.getDeckState } */

class PlayerRenderer extends Renderer[PlayerInterface] {
  override def render(player: PlayerInterface): String = {
    s"""
       |Player Name: ${player.getName}
       |Health: ${player.getHealth}
       |""".stripMargin
  }
}

class SnapshotRenderer extends Renderer[GameSnapshot] {
  override def render(snapshot: GameSnapshot): String = {
    val currentPlayer = renderPlayerSnapshot(snapshot.currentPlayer)
    val opponent = renderPlayerSnapshot(snapshot.opponent)
    val tradeRow = snapshot.tradeRow.mkString(", ")
    val tradeDeckCount = snapshot.tradeDeckCount
    val explorerCount = snapshot.explorerCount

    s"""
       |Current Player:
       |$currentPlayer
       |
       |Opponent:
       |$opponent
       |
       |Trade Row: $tradeRow
       |Trade Deck Count: $tradeDeckCount
       |Explorer Count: $explorerCount
       |""".stripMargin
  }

  private def renderPlayerSnapshot(player: PlayerSnapshot): String = {
    s"""
       |Name: ${player.name}
       |Health: ${player.health}
       |Hand: ${player.hand.mkString(", ")}
       |Discard Pile: ${player.discardPile.mkString(", ")}
       |Deck Size: ${player.playerDeck.size}
       |""".stripMargin
  }
}