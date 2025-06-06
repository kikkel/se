package de.htwg.se.starrealms.controller.ControllerComponent.str

import de.htwg.util.Command
import de.htwg.se.starrealms.model.CardComponent.interface.Card
import de.htwg.se.starrealms.controller.GameMediatorComponent.interface.GameMediator

class ReplenishTradeRowCommand(mediator: GameMediator) extends Command {
  private var rep: List[Card] = Nil
  def setRep(cards: List[Card]): Unit = { rep = cards }
  override def doMove: Unit = mediator.getGameLogic.replenishTradeRow
  override def undoMove: Unit = { rep.foreach(mediator.getGameLogic.undoReplenish); rep = Nil }
}