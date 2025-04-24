package de.htwg.se.starrealms.model

class Player() {
    var name: String = ""
    var authority: Int = 50
    var trade: Int = 8
    var combat: Int = 2
    var playerDeck: Int = 10
    var playerDiscard: Int = 0
    var playerHand: Int = 5

    def this(name: String) {
        this()
        this.name = name
    }

    override def toString: String = s"Player($name, $authority, $trade, $combat, $playerDeck, $playerDiscard, $playerHand)"
}