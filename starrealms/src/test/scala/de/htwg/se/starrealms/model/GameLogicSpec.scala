package de.htwg.se.starrealms.model

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class GameLogicSpec extends AnyWordSpec with Matchers {

  val edition = Edition("Core Set")
  val player1 = Player("Player 1")
  val player2 = Player("Player 2")
  val decksByRole = Map("Personal Deck" -> new Deck(), "Trade Deck" -> new Deck(), "Trade Row" -> new Deck())

  def newGameLogicWithDeck(deck: Deck): GameLogic = {
    val gs = new GameState(decksByRole.updated("Personal Deck", deck), player1, player2)
    gs.setPlayerDeck(player1, deck) // WICHTIG: Deck explizit zuweisen!
    new GameLogic(gs)
  }

  "GameLogic" should {
    "draw cards using the StartTurnStrategy" in {
      val deck = new Deck()
      val card1 = new DefaultCard(
        edition,
        "Card1",
        Some(new Ability(List(SimpleAction("Gain 1 Trade")))),
        Faction("Unaligned"),
        scala.util.Success(new Ship()),
        1,
        "Personal Deck"
      )
      val card2 = card1.copy(cardName = "Card2")
      val card3 = card1.copy(cardName = "Card3")
      deck.addCard(card1)
      deck.addCard(card2)
      deck.addCard(card3)

      val gameLogic = newGameLogicWithDeck(deck)
      val drawnCards = gameLogic.drawCards(3)

      drawnCards should contain theSameElementsAs List(card1, card2, card3)
    }

    "replenish the trade row using the TradeRowReplenishStrategy" in {
      val tradeRowDeck = new Deck()
      val card1 = new FactionCard(
        edition = edition,
        cardName = "TradeCard1",
        cost = 1,
        primaryAbility = Some(new Ability(List(SimpleAction("Gain 1 Trade")))),
        allyAbility = None,
        scrapAbility = None,
        faction = Faction("Unaligned"),
        cardType = scala.util.Success(new Ship()),
        qty = 1,
        role = "Trade Row",
        notes = None
      )
      val card2 = card1.copy(cardName = "TradeCard2", cost = 2)
      val card3 = card1.copy(cardName = "TradeCard3", cost = 3)
      val card4 = card1.copy(cardName = "TradeCard4", cost = 4)
      val card5 = card1.copy(cardName = "TradeCard5", cost = 5)
      tradeRowDeck.addCard(card1)
      tradeRowDeck.addCard(card2)
      tradeRowDeck.addCard(card3)
      tradeRowDeck.addCard(card4)
      tradeRowDeck.addCard(card5)

      val gs = new GameState(decksByRole.updated("Trade Deck", tradeRowDeck), player1, player2)
      val gameLogic = new GameLogic(gs)
      gameLogic.replenishTradeRow()
      gameLogic.gameState.getTradeRow.size shouldBe 5
    }

    "draw a single card from the deck" in {
      val deck = new Deck()
      val card = new DefaultCard(
        edition,
        "SingleCard",
        Some(new Ability(List(SimpleAction("Gain 1 Trade")))),
        Faction("Unaligned"),
        scala.util.Success(new Ship()),
        1,
        "Personal Deck"
      )
      deck.addCard(card)

      val gs = new GameState(decksByRole.updated("Personal Deck", deck), player1, player2)
      gs.setPlayerDeck(player1, deck)
      val gameLogic = new GameLogic(gs)
      val drawnCard = gameLogic.drawCard()

      drawnCard shouldBe Some(card)
    }

    "buy a card by removing it from the trade row" in {
      val deck = new Deck()
      val card = new FactionCard(
        edition = edition,
        cardName = "PurchaseCard",
        cost = 1,
        primaryAbility = Some(new Ability(List(SimpleAction("Gain 1 Trade")))),
        allyAbility = None,
        scrapAbility = None,
        faction = Faction("Unaligned"),
        cardType = scala.util.Success(new Ship()),
        qty = 1,
        role = "Trade Row",
        notes = None
      )
      deck.addCard(card)

      val gs = new GameState(decksByRole, player1, player2)
      gs.setTradeRow(List(card))
      val gameLogic = new GameLogic(gs)
      gameLogic.buyCard(card)
      gs.getTradeRow should not contain card
    }

    "play a card by removing it from the hand" in {
      val deck = new Deck()
      val card = new DefaultCard(
        edition,
        "PlayCard",
        Some(new Ability(List(SimpleAction("Gain 1 Trade")))),
        Faction("Unaligned"),
        scala.util.Success(new Ship()),
        1,
        "Personal Deck"
      )
      val gs = new GameState(decksByRole, player1, player2)
      gs.setHand(player1, List(card))
      val gameLogic = new GameLogic(gs)
      gameLogic.playCard(card)
      gs.getHand(player1) should not contain card
      gs.getDiscardPile(player1) should contain(card)
    }

    "reset the game by resetting the deck" in {
      val deck = new Deck()
      val card = new DefaultCard(
        edition,
        "ResetCard",
        Some(new Ability(List(SimpleAction("Gain 1 Trade")))),
        Faction("Unaligned"),
        scala.util.Success(new Ship()),
        1,
        "Personal Deck"
      )
      deck.addCard(card)

      val gs = new GameState(decksByRole.updated("Personal Deck", deck), player1, player2)
      gs.setPlayerDeck(player1, deck)
      val gameLogic = new GameLogic(gs)
      gameLogic.resetGame()
      gs.getHand(player1) shouldBe empty
      gs.getDiscardPile(player1) shouldBe empty
    }

    "get the current state of the deck" in {
      val deck = new Deck()
      val card1 = new DefaultCard(
        edition,
        "Card1",
        Some(new Ability(List(SimpleAction("Gain 1 Trade")))),
        Faction("Unaligned"),
        scala.util.Success(new Ship()),
        1,
        "Personal Deck"
      )
      val card2 = new FactionCard(
        edition = edition,
        cardName = "Card2",
        cost = 2,
        primaryAbility = Some(new Ability(List(SimpleAction("Gain 2 Trade")))),
        allyAbility = None,
        scrapAbility = None,
        faction = Faction("Unaligned"),
        cardType = scala.util.Success(new Ship()),
        qty = 1,
        role = "Trade Deck",
        notes = None
      )
      deck.addCard(card1)
      deck.addCard(card2)

      // Die Map von getCards ist nicht sortiert, daher sortieren wir nach cardName
      val deckState = deck.getCards.toList.sortBy(_._1.cardName).map { case (card, qty) => s"Card[$qty, ${card.cardName}]" }.mkString(", ")

      deckState shouldBe "Card[1, Card1], Card[1, Card2]"
    }
  }
}