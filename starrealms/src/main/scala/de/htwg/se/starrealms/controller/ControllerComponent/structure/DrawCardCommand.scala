package de.htwg.se.starrealms.controller.ControllerComponent.structure


import de.htwg.util.Command
import de.htwg.se.starrealms.model.CardComponent.Card
import de.htwg.se.starrealms.controller.GameMediatorComponent.GameMediator

class DrawCardCommand(mediator: GameMediator) extends Command {
  private var drawnCard: Option[Card] = None
  override def doMove: Unit = { drawnCard = mediator.getGameLogic.drawCard }
  override def undoMove: Unit = { drawnCard.foreach(mediator.getGameLogic.returnCardToPlayerDeck); drawnCard = None }
}