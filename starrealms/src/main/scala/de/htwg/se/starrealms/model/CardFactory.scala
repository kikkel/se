package de.htwg.se.starrealms.model

object CardFactory {
    def createCard(cardName: String): Card = cardName.toLowerCase match {
        //Ships: Default
        case "scout" =>
            new Ship(
                name = "Scout",
                cardType = "Ship",
                cost = Some(0),
                primaryAbility = Some(new Ability(List("1 coin"))),
                allyAbility = None,
                scrapAbility = None
            )
        case "viper" =>
            new Ship(
                name = "Viper",
                cardType = "Ship",
                cost = Some(0),
                primaryAbility = Some(new Ability(List("1 damage"))),
                allyAbility = None,
                scrapAbility = None
            )
        case "explorer" =>
            new Ship(
                name = "Explorer",
                cardType = "Ship",
                cost = Some(2),
                primaryAbility = Some(new Ability(List("2 coins"))),
                allyAbility = None,
                scrapAbility = Some(new Ability(List("2 damage")))
            )

//------------------------------------------------------------------------------------------

        case "trade federation base" => new Base(
            name = "Trade Federation Base",
            cost = 4,
            defense = "5",
            isOutpost = false,
            primaryAbility = Some(new Ability(List("Gain 4 authority")))
        )

        case "blob base" => new Base(
            name = "Blob Base",
            cost = 5,
            defense = "5",
            isOutpost = true, // Setze isOutpost auf true
            primaryAbility = Some(new Ability(List("Gain 3 damage")))
        )

        //Bases: Star Empire
        case "star empire base" => new Base(
            name = "Star Empire Base",
            cost = 4,
            defense = "1",
            isOutpost = false, // Setze isOutpost auf false
            primaryAbility = Some(new Ability(List("Draw a card")))
        )

        //Bases: Machine Cult
        case "machine cult base" => new Base(
            name = "Machine Cult Base",
            cost = 5,
            defense = "2",
            isOutpost = true, // Setze isOutpost auf true
            primaryAbility = Some(new Ability(List("Scrap a card from your hand or discard pile")))
        )


        case _ => throw new IllegalArgumentException(s"Unknown card type: $cardName")
    }

}