package de.htwg.se.starrealms.controller.ControllerComponent.structure


import de.htwg.util.Command
import de.htwg.se.starrealms.model.GameCore.Card
import de.htwg.se.starrealms.controller.GameMediatorComponent.GameMediator

class ReplenishTradeRowCommand(mediator: GameMediator) extends Command {
  private var rep: List[Card] = Nil
  def setRep(cards: List[Card]): Unit = { rep = cards }
  override def doMove: Unit = mediator.getGameLogic.replenishTradeRow
  override def undoMove: Unit = { rep.foreach(mediator.getGameLogic.undoReplenish); rep = Nil }
}