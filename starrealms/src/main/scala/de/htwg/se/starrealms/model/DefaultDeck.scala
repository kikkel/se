package de.htwg.se.starrealms.model

class DefaultDeck(){

    val scoutCards: List[DefaultCard] = List.fill(8)(new DefaultCard("Scout"))
    val viperCards: List[DefaultCard] = List.fill(2)(new DefaultCard("Viper"))

    def getScoutCards: List[DefaultCard] = scoutCards
    def getViperCards: List[DefaultCard] = viperCards

    override def toString: String = s"DefaultDeck($scoutCards, $viperCards)"



}