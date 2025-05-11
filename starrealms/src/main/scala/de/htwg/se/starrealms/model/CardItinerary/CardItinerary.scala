package de.htwg.se.starrealms.model.CardItinerary

import de.htwg.se.starrealms.model._

object CardItinerary {
    private var cardsBySet: Map[String, List[Card]] = Map()

    def loadCardsFromFile(): Unit = {
        val filePath = "src/main/resources/CoreSet.csv"
        val rows = CardItineraryApp.loadCsv(filePath)
        val validCards = CardItineraryApp.filterValidCards(rows)
        val cardInstances = validCards.map(CardItineraryApp.createCardInstance)
        cardsBySet = cardInstances.groupBy(_.set.nameOfSet)
    }

    def getCardsForSet(setName: String): List[Card] = {
        cardsBySet.getOrElse(setName, List())
    }
}