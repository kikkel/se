package de.htwg.se.starrealms.controller.GameLogicComponent

import de.htwg.se.starrealms.model.GameStateComponent.GameStateInterface
import de.htwg.se.starrealms.model.GameCore.Card

trait GameLogicInterface {
    def drawCard: Option[Card]
    def returnCardToPlayerDeck(card: Card): Unit

    def drawCards(count: Int): List[Card]

    def playCard(card: Card): Unit

    def returnCardToHand(card: Card): Unit


    def buyCard(card: Card): Unit
    def returnCardToTradeRow(card: Card): Unit

    def replenishTradeRow: Unit 

    def undoReplenish(card: Card): Unit


    def endTurn: Unit

    def undoEndTurn: Unit 

    def resetGame: Unit
    def undoResetGame: Unit

    def dealDamageToOpponent(amount: Int): Unit

    /*     def applyCombat: Unit
    } */
}