package de.htwg.se.starrealms.model.GameCore

import scala.util.{Try, Success, Failure}

trait CardType {
    def cardType: String
}

trait Faction {
    def factionName: String
    def matches(other: Faction): Boolean
    def render(): String = s"$factionName #factory"
    //def apply()
}
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


trait Edition {
    def nameOfEdition: String
    def render(): String = s"$nameOfEdition #factory"
    //def apply()
}


