package de.htwg.se.starrealms.model

class DefaultCard(val name: String) {
    override def toString: String = s"DefaultCard($name, $cardType, $value)"
    val cardType: String = name match {
        case "Scout" => "Trade"
        case "Viper" => "Combat"
        //case _ => "Unknown"
    }
    val value: Int = name match {
        case "Scout" => 1
        case "Viper" => 1
        //case _ => 0
    }
}
class ViperCard {

    val name: String = "Viper"
    val cardType: String = "Combat"
    val value: Int = 1

    override def toString: String = s"ViperCard($name, $cardType, $value)"

    def getName: String = name
    def getType: String = cardType
    def getValue: Int = value

}
class ScoutCard {

    val name: String = "Scout"
    val cardType: String = "Trade"
    val value: Int = 1

    override def toString: String = s"ScoutCard($name, $cardType, $value)"

    def getName: String = name
    def getType: String = cardType
    def getValue: Int = value

}