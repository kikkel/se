package de.htwg.se.starrealms.controller.ControllerComponent

import de.htwg.util.Command
import de.htwg.se.starrealms.model.SetUpComponent.Card
import de.htwg.se.starrealms.model.GameState

class BuyCardCommand(controller: Controller, card: Card) extends Command {
  override def doMove: Unit = controller.gameLogic.buyCard(card); override def undoMove: Unit = controller.gameLogic.returnCardToTradeRow(card) }
