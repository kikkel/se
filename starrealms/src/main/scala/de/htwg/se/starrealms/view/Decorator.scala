package de.htwg.se.starrealms.view

import de.htwg.se.starrealms.model.{Card, Faction, FactionCard, ExplorerCard, DefaultCard}

trait Renderer[T] { def render(entity: T): String }

abstract class RDecorator[T](wrapped: Renderer[T]) extends Renderer[T] {
  override def render(entity: T): String = wrapped.render(entity)
}

class LoggingDecorator[T](wrapped: Renderer[T]) extends RDecorator[T](wrapped) {
  override def render(entity: T): String = {
    val result = super.render(entity)
    println(s"[LOG] Rendering entity: ${entity.toString.take(50)}")
    result
  }
}

class ColourHighlightDecorator[T](wrapped: Renderer[Card]) extends RDecorator[Card](wrapped) {
  override def render(card: Card): String = {
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

class CompactCardDecorator(wrapped: Renderer[Card]) extends RDecorator[Card](wrapped) {
  override def render(card: Card): String = {
    card match {
        case fc: FactionCard => s"${fc.cardName} (Cost: ${fc.cost})"
        case ec: ExplorerCard => s"${ec.cardName} (Cost: ${ec.cost})"
        case dc: DefaultCard => s"${dc.cardName}"
    }
  }
}

class HtmlStyledDecorator(wrapped: Renderer[Card]) extends RDecorator[Card](wrapped) {
  override def render(card: Card): String = {
    val base = wrapped.render(card)
    s"<div class='card-block'$base</div>"
  }
}