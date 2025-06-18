package de.htwg.se.starrealms.controller.ControllerComponent.structure


import de.htwg.util.CommandInterface
import de.htwg.se.starrealms.model.GameCore.CardInterface
import de.htwg.se.starrealms.model.GameStateComponent.GameStateInterface
import de.htwg.se.starrealms.controller.GameMediatorComponent.GameMediator
import com.google.inject.Inject

abstract class Command @Inject() extends CommandInterface {
}

case class BuyCardCommand(mediator: GameMediator, card: CardInterface) extends Command with CommandInterface {
  override def doMove: Unit = mediator.getGameLogic.buyCard(card); override def undoMove: Unit = mediator.getGameLogic.returnCardToTradeRow(card) }

case class DrawCardCommand(mediator: GameMediator) extends Command with CommandInterface {
  private var drawnCard: Option[CardInterface] = None
  override def doMove: Unit = { drawnCard = mediator.getGameLogic.drawCard }
  override def undoMove: Unit = { drawnCard.foreach(mediator.getGameLogic.returnCardToPlayerDeck); drawnCard = None }
}

case class StartTurnCommand(mediator: GameMediator, countCards: Int) extends Command with CommandInterface {
  private var drawnCards: List[CardInterface] = Nil
  override def doMove: Unit = { drawnCards = mediator.getGameLogic.drawCards(countCards) }
  override def undoMove: Unit = { drawnCards.foreach(mediator.getGameLogic.returnCardToPlayerDeck); drawnCards = Nil }
}

case class EndTurnCommand(mediator: GameMediator) extends Command with CommandInterface {
  override def doMove: Unit = mediator.getGameLogic.endTurn; override def undoMove: Unit = mediator.getGameLogic.undoEndTurn }

case class InvalidCommand(input: String) extends Command with CommandInterface {
  override def doMove: Unit = println(s"Invalid command: $input"); override def undoMove: Unit = {} }

case class PlayCardCommand(mediator: GameMediator, card: CardInterface) extends Command with CommandInterface {
  override def doMove: Unit = mediator.getGameLogic.playCard(card); override def undoMove: Unit = mediator.getGameLogic.returnCardToHand(card) }

case class ReplenishTradeRowCommand(mediator: GameMediator) extends Command with CommandInterface {
  private var rep: List[CardInterface] = Nil
  def setRep(cards: List[CardInterface]): Unit = { rep = cards }
  override def doMove: Unit = mediator.getGameLogic.replenishTradeRow
  override def undoMove: Unit = { rep.foreach(mediator.getGameLogic.undoReplenish); rep = Nil }
}
case class ResetGameCommand(mediator: GameMediator) extends Command with CommandInterface {
  override def doMove: Unit = mediator.getGameLogic.resetGame; override def undoMove: Unit = mediator.getGameLogic.undoResetGame }

case class ShowDeckCommand(mediator: GameMediator) extends Command with CommandInterface {
  override def doMove: Unit = println(mediator.getGameState.getSnapshot); override def undoMove: Unit = {} }
