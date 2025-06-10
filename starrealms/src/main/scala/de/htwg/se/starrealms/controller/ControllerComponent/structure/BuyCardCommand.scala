package de.htwg.se.starrealms.controller.ControllerComponent.structure


import de.htwg.util.Command
import de.htwg.se.starrealms.model.GameCore.Card
import de.htwg.se.starrealms.controller.GameMediatorComponent.GameMediator

class BuyCardCommand(mediator: GameMediator, card: Card) extends Command {
  override def doMove: Unit = mediator.getGameLogic.buyCard(card); override def undoMove: Unit = mediator.getGameLogic.returnCardToTradeRow(card) }
