package de.htwg.se.starrealms.model

trait Faction { 
    def factionName: String
    def colour: String
    //def apply()

}

private class TradeFederation extends Faction { 
    override def factionName: String = "Trade Federation"
    override def colour: String = "Blue"
}
private class StarEmpire extends Faction { 
    override def factionName: String = "Star Empire"
    override def colour: String = "Yellow"
}
private class Blob extends Faction { 
    override def factionName: String = "Blob"
    override def colour: String = "Green" 
}
private class MachineCult extends Faction { 
    override def factionName: String = "Machine Cult" 
    override def colour: String = "Red"
}

object Faction {
    override def apply(factionName: String): Faction = factionName.toLowerCase match {
        case "trade federation" => new TradeFederation
        case "star empire" => new StarEmpire
        case "blob" => new Blob
        case "machine cult" => new MachineCult
        case _ => throw new IllegalArgumentException(s"Unknown faction: $factionName")
    }
}

// Example usage
// val tradeFederation = Faction("trade federation")