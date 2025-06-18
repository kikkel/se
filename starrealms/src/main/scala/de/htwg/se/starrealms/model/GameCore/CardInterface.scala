package de.htwg.se.starrealms.model.GameCore

import scala.util.{Try, Success, Failure}

trait CardTypeInterface {
    def cardType: String
}

trait FactionInterface {
    def factionName: String
    def matches(other: FactionInterface): Boolean
    def render(): String = s"$factionName #factory"
    //def apply()
}
trait CardInterface {
    val edition: EditionInterface
    val cardName: String
    val primaryAbility: Option[AbilityInterface]
    val faction: FactionInterface
    def cardType: Try[CardTypeInterface]
    val qty: Int
    val role: String
    def render(): String
}


trait EditionInterface {
    def nameOfEdition: String
    def render(): String = s"$nameOfEdition #factory"
    //def apply()
}


