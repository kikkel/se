package de.htwg.se.starrealms.model.GameCore

import de.htwg.se.starrealms.model.GameCore.Card

trait CardCSVLoaderInterface {
    def loadCardsFromFile: Unit
    def getAllCards: List[Card]
    def getCardsForEdition(nameOfEdition: String): List[Card]
}