package de.htwg.se.starrealms.model.GameCore.structure

import de.htwg.se.starrealms.model.GameCore.CardType

class Ship extends CardType {
    override def cardType: String = "Ship"
}

class Base(val defense: String, val isOutpost: Boolean) extends CardType {
    override def cardType: String = "Base"

}