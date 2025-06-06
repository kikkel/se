package de.htwg.se.starrealms.controller.ControllerComponent.str

import de.htwg.util.Command
import de.htwg.se.starrealms.model.CardComponent.interface.Card
import de.htwg.se.starrealms.controller.GameMediatorComponent.interface.GameMediator

class BuyCardCommand(mediator: GameMediator, card: Card) extends Command {
  override def doMove: Unit = mediator.getGameLogic.buyCard(card); override def undoMove: Unit = mediator.getGameLogic.returnCardToTradeRow(card) }
