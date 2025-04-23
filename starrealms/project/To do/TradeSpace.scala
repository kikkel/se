package de.htwg.se.starrealms.model

import de.htwg.se.starrealms.model

class TradeSpace {
  var tradeRow: List[TradeCard] = List()
  var tradeDeck: TradeDeck = new TradeDeck()
  var explorerDeck: ExplorerDeck = new ExplorerDeck()

  def drawTradeCard(): TradeCard = {
    if (tradeDeck.cards.isEmpty) {
      tradeDeck.shuffle()
    }
    val card = tradeDeck.drawCard()
    card
  }

  def addToTradeRow(card: TradeCard): Unit = {
    tradeRow = card :: tradeRow
  }
    def removeFromTradeRow(card: TradeCard): Unit = {
        tradeRow = tradeRow.filterNot(_ == card)
    }
    def clearTradeRow(): Unit = {
        tradeRow = List()
    }
    def refillTradeRow(): Unit = {
        while (tradeRow.size < 5) {
            val card = drawTradeCard()
            addToTradeRow(card)
        }
    }

}