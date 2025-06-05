package de.htwg.se.starrealms.model.DeckComponent.impl

import de.htwg.se.starrealms.model.CardComponent.str.CardCSVLoader


object LoadCards {
    def loadFromResource(getCsvPath: String, setName: String): Map[String, Deck] = {
        val loader = new CardCSVLoader(getCsvPath)
        loader.loadCardsFromFile()
        val cards = loader.getAllCards.filter(_.edition.nameOfEdition.trim.equalsIgnoreCase(setName.trim))
        val groupedCards = cards.groupBy(_.role.trim)
        groupedCards.map { case (role, cards) =>
            val deck = new Deck()
            deck.setName(role)
            val cardMap = cards.map(card => card -> card.qty).toMap
            deck.setCards(cardMap)
            role -> deck
        }
    }

    val ki_filePath: String = "/Users/kianimoon/se/se/starrealms/src/main/resources/PlayableSets.csv"
    //val ki_filePath: String = "/Users/koeseazra/SE-uebungen/se/starrealms/src/main/resources/PlayableSets.csv"


    def getCsvPath: String =
        sys.env.getOrElse("CARDS_CSV_PATH", s"$ki_filePath")

}




