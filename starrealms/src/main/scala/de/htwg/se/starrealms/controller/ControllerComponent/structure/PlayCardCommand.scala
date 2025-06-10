package de.htwg.se.starrealms.controller.ControllerComponent.structure


import de.htwg.util.Command
import de.htwg.se.starrealms.model.GameCore.Card
import de.htwg.se.starrealms.controller.GameMediatorComponent.GameMediator

class PlayCardCommand(mediator: GameMediator, card: Card) extends Command {
  override def doMove: Unit = mediator.getGameLogic.playCard(card); override def undoMove: Unit = mediator.getGameLogic.returnCardToHand(card) }
