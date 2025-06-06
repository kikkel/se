package de.htwg.se.starrealms.model.DeckComponent.impl

import de.htwg.se.starrealms.model.CardComponent.str.CardCSVLoader
import de.htwg.se.starrealms.model.DeckComponent.interface.Builder
import de.htwg.se.starrealms.model.DeckComponent.interface.DeckDirectorInterface


object LoadCards {
    def loadFromResource(getCsvPath: String, setName: String, builderFactory: => Builder, director: DeckDirectorInterface): Map[String, Deck] = {
        val loader = new CardCSVLoader(getCsvPath)
        loader.loadCardsFromFile
        val cards = loader.getAllCards.filter(_.edition.nameOfEdition.trim.equalsIgnoreCase(setName.trim))
        val groupedCards = cards.groupBy(_.role.trim)
        director.constructDecks(builderFactory, groupedCards).view.mapValues(_.asInstanceOf[Deck]).toMap
    }

    val ki_filePath: String = "/Users/kianimoon/se/se/starrealms/src/main/resources/PlayableSets.csv"
    //val ki_filePath: String = "/Users/koeseazra/SE-uebungen/se/starrealms/src/main/resources/PlayableSets.csv"


    def getCsvPath: String =
        sys.env.getOrElse("CARDS_CSV_PATH", s"$ki_filePath")

}




