package de.htwg.se.starrealms.view

import de.htwg.se.starrealms.model.GameCore.{CardInterface, AbilityInterface}

import com.google.inject.Inject
import de.htwg.se.starrealms.model.GameCore.impl._

trait Renderer[T] { def render(entity: T): String }

abstract class RDecorator[T] (wrapped: Renderer[T]) extends Renderer[T] {
  override def render(entity: T): String = wrapped.render(entity)
}

class LoggingDecorator[T] (wrapped: Renderer[T]) extends RDecorator[T](wrapped) {
  override def render(entity: T): String = {
    val result = super.render(entity)
    println(s"[LOG] Rendering entity: ${entity.toString.take(50)}")
    result
  }
}

class ColourHighlightDecorator[T] (wrapped: Renderer[CardInterface]) extends RDecorator[CardInterface](wrapped) {
  override def render(card: CardInterface): String = {
    val base = wrapped.render(card)
    val prefix = card.faction.factionName match {
        case "Trade Federation" => "[TRADFED] "
        case "Star Empire" => "[STAR] "
        case "Blob" => "[BLOB] "
        case "Machine Cult" => "[MACHINE] "
        case _ => " "
  }
    s"$prefix$base"
  }
}

class CompactCardDecorator (wrapped: Renderer[CardInterface]) extends RDecorator[CardInterface](wrapped) {
  override def render(card: CardInterface): String = {
    card match {
        case fc: FactionCard => s"${fc.cardName} (Primary Ability: ${renderAbility(fc.primaryAbility)},  Ally Ability: ${renderAbility(fc.allyAbility)},  Scrap Ability: ${renderAbility(fc.scrapAbility)})"
        case ec: ExplorerCard => s"${ec.cardName} (Ability: ${renderAbility(ec.primaryAbility)},  Scrap Ability: ${renderAbility(ec.scrapAbility)})"
        case dc: DefaultCard => s"${dc.cardName} (Ability: ${renderAbility(dc.primaryAbility)})"
    }
  }
  private def renderAbility(ability: Option[AbilityInterface]): String =
    ability.map(_.render).getOrElse("None")
}

class HtmlStyledDecorator (wrapped: Renderer[CardInterface]) extends RDecorator[CardInterface](wrapped) {
  override def render(card: CardInterface): String = {
    val base = wrapped.render(card)
    s"<div class='card-block'$base</div>"
  }
}