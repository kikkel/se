package de.htwg.se.starrealms.controller.ControllerComponent

import de.htwg.util.Command
import de.htwg.se.starrealms.model.Card


class ReplenishTradeRowCommand(controller: Controller) extends Command {
  private var rep: List[Card] = Nil
  def setRep(cards: List[Card]): Unit = { rep = cards }
  override def doMove: Unit = controller.gameLogic.replenishTradeRow()
  override def undoMove: Unit = { rep.foreach(controller.gameLogic.undoReplenish); rep = Nil }
}