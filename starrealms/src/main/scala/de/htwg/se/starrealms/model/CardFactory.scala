package de.htwg.se.starrealms.model

object CardFactory {
    def createCard(cardName: String): Card = cardName.toLowerCase match {
        //Ships: Default
        case "scout" => new Ship("Scout", new CardType("Ship"), None, Some(new Ability(List("1 coin"))), None, None)
        case "viper" => new Ship("Viper", new CardType("Ship"), None, Some(new Ability(List("1 damage"))), None, None)
        case "explorer" => new Ship("")

        //Ships: Trade Federation
        case "example trade federation ship" => new Ship("")

        //Ships: Blob
        case "example blob ship" => new Ship("")

        //Ships: Star Empire
        case "star empire ship" => new Ship("")

        //Ships: Machine Cult
        case "machine cult ship" => new Ship("")
        
//------------------------------------------------------------------------------------------

        //Bases: Trade Federation
        case "trade federation base" => new Base("", defense = 1, isOutPost = false)

        //Bases: Blob
        case "blob base" => new Base("", defense = 2, isOutPost = true)

        //Bases: Star Empire
        case "star empire base" => new Base("", defense = 1, isOutPost = false)

        //Bases: Machine Cult
        case "machine cult base" => new Base("", defense = 2, isOutPost = true)


        case _ => throw new IllegalArgumentException(s"Unknown card type: $cardType")
    }

}