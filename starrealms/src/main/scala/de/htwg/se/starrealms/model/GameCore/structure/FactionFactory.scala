package de.htwg.se.starrealms.model.GameCore.structure

import de.htwg.se.starrealms.model.GameCore.Faction

private class TradeFederation extends Faction { override def factionName: String = "Trade Federation"; override def matches(other: Faction): Boolean = other.factionName == factionName }
private class StarEmpire extends Faction { override def factionName: String = "Star Empire"; override def matches(other: Faction): Boolean = other.factionName == factionName }
private class Blob extends Faction { override def factionName: String = "Blob"; override def matches(other: Faction): Boolean = other.factionName == factionName }
private class MachineCult extends Faction { override def factionName: String = "Machine Cult"; override def matches(other: Faction): Boolean = other.factionName == factionName }
private class CompositeFaction(factions: List[Faction]) extends Faction { override def factionName: String = factions.map(_.factionName).mkString(" / "); override def matches(other: Faction): Boolean = factions.exists(_.matches(other)) }
private class Unaligned extends Faction { override def factionName: String = "Unaligned"; override def matches(other: Faction): Boolean = false }

object Faction {
    def apply(factionName: String): Faction = {
        if (factionName.contains("/")) {
            val factionParts = factionName.split("/").map(_.trim)
            val factions = factionParts.map(apply).toList
            new CompositeFaction(factions)
        } else {
            factionName.toLowerCase match {
                case "trade federation" => new TradeFederation
                case "star empire" => new StarEmpire
                case "blob" => new Blob
                case "machine cult" => new MachineCult
                case "unaligned" => new Unaligned
                case _ => throw new IllegalArgumentException(s"Faction not recognized: $factionName #Factory.scala: objectFaction")
            }
        }
    }
}

// Example usage
// val tradeFederation = Faction("trade federation")

