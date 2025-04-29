sealed trait Faction
case object TradeFederation extends Faction
case object Blob extends Faction
case object StarEmpire extends Faction
case object MachineCult extends Faction

sealed trait CardType
case object Base extends CardType
case object Outpost extends CardType
case object Ship extends CardType

case class Ability(description: String)
case class AllyAbility(description: String)
case class ScrapAbility(description: String)

case class Card(
    name: String, faction: Faction, cardtype: CardType, cost: Int, ability: Option[Ability] = None, allyAbility: Option[AllyAbility] = None, scrapability: Option[ScrapAbility] = None
)


case class Player(name: String, deck: List[Card], discardPile: List[Card] = List(), hand: List[Card] = List())

//-------------------------------------------------------------------------------------------------------------------------------------------------------------------------//


val sampleDeck: List[Card] = List(
  Card("Trade Pod", Blob, Ship, 2, Some(Ability("Gain 3 Trade")), Some(AllyAbility("Gain extra 2 Trade"))),
  Card("Battle Station", MachineCult, Outpost, 3, Some(Ability("4 Defense")), Some(AllyAbility("Gain 2 Combat")), Some(ScrapAbility("Gain 3 Combat"))),
  Card("Freighter", TradeFederation, Ship, 4, Some(Ability("Gain 4 Trade"))),
  Card("Survey Ship", StarEmpire, Ship, 3, Some(Ability("Draw a card")), Some(AllyAbility("Gain 1 Combat")))
)


val player1 = Player("Player 1", sampleDeck)
val player2 = Player("Player 2", sampleDeck)


// Game Setup
val players = List(player1, player2)

// Print Game Setup
println(s"${players(0).name} has ${players(0).deck.length} cards in deck.")
println(s"${players(1).name} has ${players(1).deck.length} cards in deck.")
