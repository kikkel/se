package de.htwg.se.starrealms.controller.ControllerComponent

import de.htwg.util.Command
import de.htwg.se.starrealms.model.Card

class BuyCardCommand(controller: Controller, card: Card) extends Command {
  override def doMove: Unit = controller.gameLogic.buyCard(card); override def undoMove: Unit = controller.gameLogic.returnCardToTradeRow(card) }
