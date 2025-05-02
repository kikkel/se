package de.htwg.se.starrealms.model

class Faction(val name: String) {
    def getName: String = name
    def render(): String = name // Return the name of the faction    
    override def equals(obj: Any): Boolean = obj match {
        case that: Faction => this.name == that.name
        case _ => false
    }
    
    //override def hashCode(): Int = name.hashCode

}


class TradeFederation extends Faction("Trade Federation") {
    override def render(): String = "Trade Federation"
}
class StarEmpire extends Faction("Star Empire") {
    override def render(): String = "Star Empire"
}
class Blob extends Faction("Blob") {
    override def render(): String = "Blob"
}
class MachineCult extends Faction("MachineCult") {
    override def render(): String = "Machine Cult"
}