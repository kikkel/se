package de.htwg.se.starrealms.controller.ControllerComponent

import de.htwg.util.Command
import de.htwg.se.starrealms.model.CardComponent.interface.Card

class DrawCardCommand(controller: Controller) extends Command {
  private var drawnCard: Option[Card] = None
  override def doMove: Unit = { drawnCard = controller.gameLogic.drawCard() }
  override def undoMove: Unit = { drawnCard.foreach(controller.gameLogic.returnCardToPlayerDeck); drawnCard = None }
}