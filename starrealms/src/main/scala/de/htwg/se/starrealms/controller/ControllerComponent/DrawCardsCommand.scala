package de.htwg.se.starrealms.controller.ControllerComponent

import de.htwg.util.Command
import de.htwg.se.starrealms.model.Card



class DrawCardsCommand(controller: Controller, count: Int) extends Command {
  private var drawnCards: List[Card] = Nil
  override def doMove: Unit = { drawnCards = controller.gameLogic.drawCards(count) }
  override def undoMove: Unit = { drawnCards.foreach(controller.gameLogic.returnCardToPlayerDeck); drawnCards = Nil }
}