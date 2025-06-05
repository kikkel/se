package de.htwg.se.starrealms.model.EditionComponent.interface

trait Edition {
    def nameOfEdition: String
    def render(): String = s"$nameOfEdition #factory"
    //def apply()
}