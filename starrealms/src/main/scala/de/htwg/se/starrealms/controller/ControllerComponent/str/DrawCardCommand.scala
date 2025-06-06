package de.htwg.se.starrealms.controller.ControllerComponent.str

import de.htwg.util.Command
import de.htwg.se.starrealms.model.CardComponent.interface.Card
import de.htwg.se.starrealms.controller.GameLogicComponent.interface.GameLogicInterface
import de.htwg.se.starrealms.controller.ControllerComponent.interface.ControllerInterface

class DrawCardCommand(controller: ControllerInterface, gameLogic: GameLogicInterface) extends Command {
  private var drawnCard: Option[Card] = None
  override def doMove: Unit = { drawnCard = controller.gameLogic.drawCard }
  override def undoMove: Unit = { drawnCard.foreach(controller.gameLogic.returnCardToPlayerDeck); drawnCard = None }
}