package de.htwg.se.starrealms.model

class SetUp() {
  val actionSpace: ActionSpace = new ActionSpace()
  val tradeSpace: TradeSpace = new TradeSpace()
  val player1Deck: PlayerDeck = new PlayerDeck()
  val player2Deck: PlayerDeck = new PlayerDeck()
  val player1Discard: PlayerDiscard = new PlayerDiscard()
  val player2Discard: PlayerDiscard = new PlayerDiscard()
  val player1Hand: Hand = new Hand()
  val player2Hand: Hand = new Hand()
  val player1Authority: AuthorityNumber = new AuthorityNumber()
  val player2Authority: AuthorityNumber = new AuthorityNumber()
  val player1Row: PlayerRow = new PlayerRow()
  val player2Row: PlayerRow = new PlayerRow()
  val tradeRow: TradeRow = new TradeRow()
  val tradeDeck: TradeDeck = new TradeDeck()
  val tradeDiscard: TradeDiscard = new TradeDiscard()
  val explorerDeck: ExplorerDeck = new ExplorerDeck()
  }