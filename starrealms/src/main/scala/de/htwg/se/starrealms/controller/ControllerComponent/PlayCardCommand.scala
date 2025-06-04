package de.htwg.se.starrealms.controller.ControllerComponent

import de.htwg.util.Command
import de.htwg.se.starrealms.model.Card

class PlayCardCommand(controller: Controller, card: Card) extends Command {
  override def doMove: Unit = controller.gameLogic.playCard(card); override def undoMove: Unit = controller.gameLogic.returnCardToHand(card) }
