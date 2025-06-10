package de.htwg.se.starrealms.model.CardComponent

import de.htwg.se.starrealms.model.CardComponent.Card

trait CardCSVLoaderInterface {
    def loadCardsFromFile: Unit
    def getAllCards: List[Card]
    def getCardsForEdition(nameOfEdition: String): List[Card]
}