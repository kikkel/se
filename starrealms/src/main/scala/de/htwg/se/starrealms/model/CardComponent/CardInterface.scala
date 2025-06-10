package de.htwg.se.starrealms.model.CardComponent

import de.htwg.se.starrealms.model.EditionComponent.Edition
import de.htwg.se.starrealms.model.AbilityComponent._
import scala.util.{Try, Success, Failure}

import de.htwg.se.starrealms.model.AbilityComponent._

trait Card {
    val edition: Edition
    val cardName: String
    val primaryAbility: Option[AbilityInterface]
    val faction: Faction
    def cardType: Try[CardType]
    val qty: Int
    val role: String
    def render(): String
}
