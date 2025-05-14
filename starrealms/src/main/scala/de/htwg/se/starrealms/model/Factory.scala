package de.htwg.se.starrealms.model

trait Faction { 
    def factionName: String
    //def apply()

}

private class TradeFederation extends Faction { 
    override def factionName: String = "Trade Federation"
}
private class StarEmpire extends Faction { 
    override def factionName: String = "Star Empire"
}
private class Blob extends Faction { 
    override def factionName: String = "Blob"
}
private class MachineCult extends Faction { 
    override def factionName: String = "Machine Cult" 
}
private class Unaligned extends Faction { 
    override def factionName: String = "Unaligned" 
}

object Faction {
    def apply(factionName: String): Faction = factionName.toLowerCase match {
        case "trade federation" => new TradeFederation
        case "star empire" => new StarEmpire
        case "blob" => new Blob
        case "machine cult" => new MachineCult
        case "unaligned" => new Unaligned
        case _ => throw new IllegalArgumentException(s"Unknown faction: $factionName")
    }
}

// Example usage
// val tradeFederation = Faction("trade federation")


trait Set { def nameOfSet: String }

private class CoreSet extends Set { 
    override def nameOfSet: String = "Core Set"
}

object Set {
    def apply(nameOfSet: String): Set = nameOfSet.toLowerCase match {
        case "core set" => new CoreSet
        case _ => throw new IllegalArgumentException(s"Unknown set: $nameOfSet")
    }
}