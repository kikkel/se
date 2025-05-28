package de.htwg.se.starrealms.model

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec



class GameLogicSpec extends AnyWordSpec with Matchers {

  "GameLogic" should {
    "draw cards using the StartTurnStrategy" in {
      val deck = new Deck()
      val card1 = new DefaultCard(
        set = Set("Core Set"),
        cardName = "Card1",
        primaryAbility = Some(new Ability(List(SimpleAction("Gain 1 Trade")))),
        faction = Faction("Unaligned"),
        cardType = scala.util.Success(new Ship()),
        qty = 1,
        role = "Personal Deck"
      )
      val card2 = new DefaultCard(
        set = Set("Core Set"),
        cardName = "Card2",
        primaryAbility = Some(new Ability(List(SimpleAction("Gain 2 Trade")))),
        faction = Faction("Unaligned"),
        cardType = scala.util.Success(new Ship()),
        qty = 1,
        role = "Personal Deck"
      )
      val card3 = new DefaultCard(
        set = Set("Core Set"),
        cardName = "Card3",
        primaryAbility = Some(new Ability(List(SimpleAction("Gain 3 Trade")))),
        faction = Faction("Unaligned"),
        cardType = scala.util.Success(new Ship()),
        qty = 1,
        role = "Personal Deck"
      )
      deck.addCard(card1)
      deck.addCard(card2)
      deck.addCard(card3)

      val gameLogic = new GameLogic(deck)
      val drawnCards = gameLogic.drawCards(3)

      drawnCards should contain theSameElementsAs List(card1, card2, card3)
    }

    "replenish the trade row using the TradeRowReplenishStrategy" in {
      val tradeRowDeck = new Deck()
      val card1 = new FactionCard(
        set = Set("Core Set"),
        cardName = "TradeCard1",
        cost = 1,
        primaryAbility = Some(new Ability(List(SimpleAction("Gain 1 Trade")))),
        allyAbility = None,
        scrapAbility = None,
        faction = Faction("Unaligned"),
        cardType = scala.util.Success(new Ship()),
        qty = 1,
        role = "Trade Row"
      )
        val card2 = new FactionCard(
            set = Set("Core Set"),
            cardName = "TradeCard2",
            cost = 2,
            primaryAbility = Some(new Ability(List(SimpleAction("Gain 2 Trade")))),
            allyAbility = None,
            scrapAbility = None,
            faction = Faction("Unaligned"),
            cardType = scala.util.Success(new Ship()),
            qty = 1,
            role = "Trade Row"
        )
        val card3 = new FactionCard(
            set = Set("Core Set"),
            cardName = "TradeCard3",
            cost = 3,
            primaryAbility = Some(new Ability(List(SimpleAction("Gain 3 Trade")))),
            allyAbility = None,
            scrapAbility = None,
            faction = Faction("Unaligned"),
            cardType = scala.util.Success(new Ship()),
            qty = 1,
            role = "Trade Row"
        )
        val card4 = new FactionCard(
            set = Set("Core Set"),
            cardName = "TradeCard4",
            cost = 4,
            primaryAbility = Some(new Ability(List(SimpleAction("Gain 4 Trade")))),
            allyAbility = None,
            scrapAbility = None,
            faction = Faction("Unaligned"),
            cardType = scala.util.Success(new Ship()),
            qty = 1,
            role = "Trade Row"
        )
        val card5 = new FactionCard(
            set = Set("Core Set"),
            cardName = "TradeCard5",
            cost = 5,
            primaryAbility = Some(new Ability(List(SimpleAction("Gain 5 Trade")))),
            allyAbility = None,
            scrapAbility = None,
            faction = Faction("Unaligned"),
            cardType = scala.util.Success(new Ship()),
            qty = 1,
            role = "Trade Row"
        )
      tradeRowDeck.addCard(card1)
      tradeRowDeck.addCard(card2)
      tradeRowDeck.addCard(card3)
      tradeRowDeck.addCard(card4)
      tradeRowDeck.addCard(card5)

      val gameLogic = new GameLogic(new Deck())
      val tradeRowDeckField = gameLogic.getClass.getDeclaredField("tradeRowDeck")
      tradeRowDeckField.setAccessible(true)
      tradeRowDeckField.set(gameLogic, tradeRowDeck)

      gameLogic.replenishTradeRow()
    }

    "draw a single card from the deck" in {
      val deck = new Deck()
      val card = new DefaultCard(
        set = Set("Core Set"),
        cardName = "SingleCard",
        primaryAbility = Some(new Ability(List(SimpleAction("Gain 1 Trade")))),
        faction = Faction("Unaligned"),
        cardType = scala.util.Success(new Ship()),
        qty = 1,
        role = "Personal Deck"
      )
      deck.addCard(card)

      val gameLogic = new GameLogic(deck)
      val drawnCard = gameLogic.drawCard()

      drawnCard shouldBe Some(card)
    }

    "purchase a card by removing it from the deck" in {
      val deck = new Deck()
      val card = new FactionCard(
        set = Set("Core Set"),
        cardName = "PurchaseCard",
        cost = 1,
        primaryAbility = Some(new Ability(List(SimpleAction("Gain 1 Trade")))),
        allyAbility = None,
        scrapAbility = None,
        faction = Faction("Unaligned"),
        cardType = scala.util.Success(new Ship()),
        qty = 1,
        role = "Trade Deck"
      )
      deck.addCard(card)

      val gameLogic = new GameLogic(deck)
      gameLogic.purchaseCard(card)

    }

    "play a card by removing it from the hand" in {
        val deck = new Deck()
        val card = new DefaultCard(
            set = Set("Core Set"),
            cardName = "PlayCard",
            primaryAbility = Some(new Ability(List(SimpleAction("Gain 1 Trade")))),
            faction = Faction("Unaligned"),
            cardType = scala.util.Success(new Ship()),
            qty = 1,
            role = "Personal Deck"
        )

      val gameLogic = new GameLogic(deck)
      gameLogic.playCard(card)
    }

    "reset the game by resetting the deck" in {
      val deck = new Deck()
      val card = new DefaultCard(
        set = Set("Core Set"),
        cardName = "ResetCard",
        primaryAbility = Some(new Ability(List(SimpleAction("Gain 1 Trade")))),
        faction = Faction("Unaligned"),
        cardType = scala.util.Success(new Ship()),
        qty = 1,
        role = "Personal Deck"
      )
      deck.addCard(card)

      val gameLogic = new GameLogic(deck)
      gameLogic.resetGame()
    }

    "get the current state of the deck" in {
      val deck = new Deck()
      val card1 = new DefaultCard(
        set = Set("Core Set"),
        cardName = "Card1",
        primaryAbility = Some(new Ability(List(SimpleAction("Gain 1 Trade")))),
        faction = Faction("Unaligned"),
        cardType = scala.util.Success(new Ship()),
        qty = 1,
        role = "Personal Deck"
      )
      val card2 = new FactionCard(
        set = Set("Core Set"),
        cardName = "Card2",
        cost = 2,
        primaryAbility = Some(new Ability(List(SimpleAction("Gain 2 Trade")))),
        allyAbility = None,
        scrapAbility = None,
        faction = Faction("Unaligned"),
        cardType = scala.util.Success(new Ship()),
        qty = 1,
        role = "Trade Deck"
      )
      deck.addCard(card1)
      deck.addCard(card2)

      val gameLogic = new GameLogic(deck)
      val deckState = gameLogic.getDeckState

      deckState shouldBe "Card1, Card2"
    }
  }
}