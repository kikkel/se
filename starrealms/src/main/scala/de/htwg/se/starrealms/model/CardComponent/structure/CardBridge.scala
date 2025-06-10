package de.htwg.se.starrealms.model.CardComponent.structure

import de.htwg.se.starrealms.model.CardComponent.CardType

class Ship extends CardType {
    override def cardType: String = "Ship"
}

class Base(val defense: String, val isOutpost: Boolean) extends CardType {
    override def cardType: String = "Base"

}