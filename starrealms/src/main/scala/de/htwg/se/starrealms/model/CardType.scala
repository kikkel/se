package de.htwg.se.starrealms.model

class CardType(val name: String) {
    def getName: String = name
    def render(): String = name // Return the name of the card type
}

class DefaultCardType extends CardType("Default") {
    override def render(): String = "Default"
}