package de.htwg.se.starrealms.model.CardComponent

trait Faction {
    def factionName: String
    def matches(other: Faction): Boolean
    def render(): String = s"$factionName #factory"
    //def apply()
}