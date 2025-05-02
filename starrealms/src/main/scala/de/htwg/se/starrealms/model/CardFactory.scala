package de.htwg.se.starrealms.model

object CardFactory {
    def createCard(cardName: String): AbstractCard = cardName.toLowerCase match {
        case "scout" => new ScoutCard()
        case "viper" => new ViperCard()
        case "explorer" => new ExplorerCard()
        case "freighter" => new TradeFederationCard()
        case "trade pod" => new BlobCard()
        case "survey ship" => new StarEmpireCard()
        case "battle station" => new MachineCultCard()
        case _ => throw new IllegalArgumentException(s"Unknown card type: $cardType")
    }
/*   def createCard(cardType: String): AbstractCard = cardType.toLowerCase match {
    case "scout" => new ScoutCard()
    case "viper" => new ViperCard()
    case _ => throw new IllegalArgumentException(s"Unknown card type: $cardType")
  } */
}