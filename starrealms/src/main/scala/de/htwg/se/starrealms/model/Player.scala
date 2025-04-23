package de.htwg.se.starrealms.model

class Player(
    val name: String,
    val playerDeck: PlayerDeck,
   /* val actionSpace: ActionSpace,
    val tradeSpace: TradeSpace,
    val health: Int = 50 */
) {
    def this(name: String) = this(name, new PlayerDeck())
    def this() = this("Player", new PlayerDeck())
    
    override def toString: String = {
        s"Player($name, $playerDeck)"
    }

}