package de.htwg.se.starrealms.controller.ControllerComponent.str

import de.htwg.util.Command
import de.htwg.se.starrealms.model.CardComponent.interface.Card
import de.htwg.se.starrealms.controller.GameMediatorComponent.interface.GameMediator

class DrawCardsCommand(mediator: GameMediator, count: Int) extends Command {
  private var drawnCards: List[Card] = Nil
  override def doMove: Unit = { drawnCards = mediator.getGameLogic.drawCards(count) }
  override def undoMove: Unit = { drawnCards.foreach(mediator.getGameLogic.returnCardToPlayerDeck); drawnCards = Nil }
}