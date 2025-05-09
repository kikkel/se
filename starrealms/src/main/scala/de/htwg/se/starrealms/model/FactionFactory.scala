package de.htwg.se.starrealms.model

trait Faction { def apply(factionName: String): Faction }

private class TradeFederation extends Faction { override def apply(factionName: String): Faction }
private class StarEmpire extends Faction { override def apply(factionName: String): Faction }
private class Blob extends Faction { override def apply(factionName: String): Faction }
private class MachineCult extends Faction { override def apply(factionName: String): Faction }       

object Faction {
    def apply(factionName: String): Faction = factionName.toLowerCase match {
        case "trade federation" => new TradeFederation
        case "star empire" => new StarEmpire
        case "blob" => new Blob
        case "machine cult" => new MachineCult
        case _ => throw new IllegalArgumentException(s"Unknown faction: $factionName")
    }
}