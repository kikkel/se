package de.htwg.se.starrealms.model

class Faction(val name: String) {
    def getName: String = name
    
    override def toString: String = s"Faction(name=$name)"
    
    override def equals(obj: Any): Boolean = obj match {
        case that: Faction => this.name == that.name
        case _ => false
    }
    
    override def hashCode(): Int = name.hashCode

}


class TradeFederation extends Faction("Trade Federation") {
    override def toString: String = s"TradeFederation(name=$name)"
}
class StarEmpire extends Faction("Star Empire") {
    override def toString: String = s"StarEmpire(name=$name)"
}
class Blob extends Faction("Blob") {
    override def toString: String = s"Blob(name=$name)"
}
class MachineCult extends Faction("MachineCult") {
    override def toString: String = s"MachineCult(name=$name)"
}