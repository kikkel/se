package de.htwg.se.starrealms.controller.GameMediatorComponent.interface

trait GameMediator {
    def notify(sender: Any, event: String): Unit
}