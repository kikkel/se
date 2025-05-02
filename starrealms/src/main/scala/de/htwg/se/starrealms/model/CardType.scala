package de.htwg.se.starrealms.model

class CardType(val name: String) {
    def getName: String = name
    override def toString: String = s"CardType(name=$name)"
}