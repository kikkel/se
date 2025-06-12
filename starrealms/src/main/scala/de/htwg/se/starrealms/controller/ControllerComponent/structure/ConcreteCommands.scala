package de.htwg.se.starrealms.controller.ControllerComponent.structure


import de.htwg.util.Command
import de.htwg.se.starrealms.model.GameCore.Card
import de.htwg.se.starrealms.model.GameStateComponent.GameStateInterface
import de.htwg.se.starrealms.controller.GameMediatorComponent.GameMediator

class BuyCardCommand(mediator: GameMediator, card: Card) extends Command {
  override def doMove: Unit = mediator.getGameLogic.buyCard(card); override def undoMove: Unit = mediator.getGameLogic.returnCardToTradeRow(card) }

class DrawCardCommand(mediator: GameMediator) extends Command {
  private var drawnCard: Option[Card] = None
  override def doMove: Unit = { drawnCard = mediator.getGameLogic.drawCard }
  override def undoMove: Unit = { drawnCard.foreach(mediator.getGameLogic.returnCardToPlayerDeck); drawnCard = None }
}

class DrawCardsCommand(mediator: GameMediator, count: Int) extends Command {
  private var drawnCards: List[Card] = Nil
  override def doMove: Unit = { drawnCards = mediator.getGameLogic.drawCards(count) }
  override def undoMove: Unit = { drawnCards.foreach(mediator.getGameLogic.returnCardToPlayerDeck); drawnCards = Nil }
}

class EndTurnCommand(mediator: GameMediator) extends Command {
  override def doMove: Unit = mediator.getGameLogic.endTurn; override def undoMove: Unit = mediator.getGameLogic.undoEndTurn }

class InvalidCommand(input: String) extends Command {
  override def doMove: Unit = println(s"Invalid command: $input"); override def undoMove: Unit = {} }

class PlayCardCommand(mediator: GameMediator, card: Card) extends Command {
  override def doMove: Unit = mediator.getGameLogic.playCard(card); override def undoMove: Unit = mediator.getGameLogic.returnCardToHand(card) }

class ReplenishTradeRowCommand(mediator: GameMediator) extends Command {
  private var rep: List[Card] = Nil
  def setRep(cards: List[Card]): Unit = { rep = cards }
  override def doMove: Unit = mediator.getGameLogic.replenishTradeRow
  override def undoMove: Unit = { rep.foreach(mediator.getGameLogic.undoReplenish); rep = Nil }
}
class ResetGameCommand(mediator: GameMediator) extends Command {
  override def doMove: Unit = mediator.getGameLogic.resetGame; override def undoMove: Unit = mediator.getGameLogic.undoResetGame }

class ShowDeckCommand(mediator: GameMediator) extends Command {
  override def doMove: Unit = println(mediator.getGameState.getSnapshot); override def undoMove: Unit = {} }
