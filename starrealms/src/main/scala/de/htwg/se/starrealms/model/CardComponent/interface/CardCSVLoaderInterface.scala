package de.htwg.se.starrealms.model.CardComponent.interface

import de.htwg.se.starrealms.model.CardComponent.interface.Card

trait CardCSVLoaderInterface {
    def loadCardsFromFile: Unit
    def getAllCards: List[Card]
    def getCardsForEdition(nameOfEdition: String): List[Card]
}