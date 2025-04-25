package de.htwg.se.starrealms.model

class TestCard(name: String, ability: Ability) extends AbstractCard(name, ability) {
    def this(name: String) = this(name, new Ability(List())) // Default constructor with empty ability
    
    override def toString: String = s"TestCard(name=$name, ability=$ability)"
    
}