package de.htwg.se.starrealms.controller.GameLogicComponent

import de.htwg.se.starrealms.model.GameStateComponent.GameStateInterface
import de.htwg.se.starrealms.model.GameCore.CardInterface

trait GameLogicInterface {
    def drawCard: Option[CardInterface]
    def returnCardToPlayerDeck(card: CardInterface): Unit

    def drawCards(count: Int): List[CardInterface]

    def playCard(card: CardInterface): Unit

    def returnCardToHand(card: CardInterface): Unit


    def buyCard(card: CardInterface): Unit
    def returnCardToTradeRow(card: CardInterface): Unit

    def replenishTradeRow: Unit 

    def undoReplenish(card: CardInterface): Unit


    def endTurn: Unit

    def undoEndTurn: Unit 

    def resetGame: Unit
    def undoResetGame: Unit

    def dealDamageToOpponent(amount: Int): Unit

    /*     def applyCombat: Unit
    } */
}