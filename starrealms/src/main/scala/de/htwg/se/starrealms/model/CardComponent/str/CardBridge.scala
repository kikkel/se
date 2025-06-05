package de.htwg.se.starrealms.model.CardComponent.str

import de.htwg.se.starrealms.model.CardComponent.interface.CardType

class Ship extends CardType {
    override def cardType: String = "Ship"
}

class Base(val defense: String, val isOutpost: Boolean) extends CardType {
    override def cardType: String = "Base"

}