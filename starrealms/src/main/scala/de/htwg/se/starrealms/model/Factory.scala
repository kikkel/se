package de.htwg.se.starrealms.model

trait Faction { 
    def factionName: String
    def matches(other: Faction): Boolean
    def render(): String = s"$factionName #factory"
    //def apply()
}

private class TradeFederation extends Faction { override def factionName: String = "Trade Federation"; override def matches(other: Faction): Boolean = other.factionName == factionName }
private class StarEmpire extends Faction { override def factionName: String = "Star Empire"; override def matches(other: Faction): Boolean = other.factionName == factionName }
private class Blob extends Faction { override def factionName: String = "Blob"; override def matches(other: Faction): Boolean = other.factionName == factionName }
private class MachineCult extends Faction { override def factionName: String = "Machine Cult"; override def matches(other: Faction): Boolean = other.factionName == factionName }
private class CompositeFaction(factions: List[Faction]) extends Faction { override def factionName: String = factions.map(_.factionName).mkString(" / "); override def matches(other: Faction): Boolean = factions.exists(_.matches(other)) }
private class Unaligned extends Faction { override def factionName: String = "Unaligned"; override def matches(other: Faction): Boolean = false }

object Faction {
    def apply(factionName: String): Faction = {
        if (factionName.contains("/")) {
            val factionParts = factionName.split("/").map(_.trim)
            val factions = factionParts.map(apply).toList
            new CompositeFaction(factions)
        } else {
            factionName.toLowerCase match {
                case "trade federation" => new TradeFederation
                case "star empire" => new StarEmpire
                case "blob" => new Blob
                case "machine cult" => new MachineCult
                case "unaligned" => new Unaligned
                case _ => throw new IllegalArgumentException(s"You've been hijacked by $factionName!")
            }
        }
    }
}

// Example usage
// val tradeFederation = Faction("trade federation")


trait Set { 
    def nameOfSet: String
    def render(): String = s"$nameOfSet #factory"
    //def apply()
}

private class CoreSet extends Set { override def nameOfSet: String = "Core Set" }
private class HighAlertFirstStrike extends Set { override def nameOfSet: String = "High Alert: First Strike" }
private class HighAlertTech extends Set { override def nameOfSet: String = "High Alert: Tech" }
private class HighAlertRequisition extends Set { override def nameOfSet: String = "High Alert: Requisition" }
private class HighAlertInvasion extends Set { override def nameOfSet: String = "High Alert: Invasion" }
private class HighAlertHeroes extends Set { override def nameOfSet: String = "High Alert: Heroes" }
private class UniversalStorageBox extends Set { override def nameOfSet: String = "Universal Storage Box" }
private class StellarAllies extends Set { override def nameOfSet: String = "Stellar Allies" }
private class CommandDeckLostFleet extends Set { override def nameOfSet: String = "Command Deck: Lost Fleet" }
private class CommandDeckTheUnity extends Set { override def nameOfSet: String = "Command Deck: The Unity" }
private class CommandDeckTheUnion extends Set { override def nameOfSet: String = "Command Deck: The Union" }
private class CommandDeckThePact extends Set { override def nameOfSet: String = "Command Deck: The Pact" }
private class CommandDeckTheCoalition extends Set { override def nameOfSet: String = "Command Deck: The Coalition" }
private class CommandDeckTheAlliance extends Set { override def nameOfSet: String = "Command Deck: The Alliance" }
private class CommandDeckTheAlignment extends Set { override def nameOfSet: String = "Command Deck: The Alignment" }
private class FrontiersKickstarterPromoPack extends Set { override def nameOfSet: String = "Frontiers Kickstarter Promo Pack" }
private class Year2PromoPackOrganizedPlaySeason5 extends Set { override def nameOfSet: String = "Year 2 Promo Pack,\nOrganized Play Season 5" }
private class Year2PromoPack extends Set { override def nameOfSet: String = "Year 2 Promo Pack" }
private class Frontiers extends Set { override def nameOfSet: String = "Frontiers" }
private class Scenarios extends Set { override def nameOfSet: String = "Scenarios" }
private class UnitedMissions extends Set { override def nameOfSet: String = "United: Missions" }
private class UnitedHeroes extends Set { override def nameOfSet: String = "United: Heroes" }
private class UnitedCommand extends Set { override def nameOfSet: String = "United: Command" }
private class UnitedAssault extends Set { override def nameOfSet: String = "United: Assault" }
private class ColonyWars extends Set { override def nameOfSet: String = "Colony Wars" }
private class CosmicGambit extends Set { override def nameOfSet: String = "Cosmic Gambit" }
private class CrisisHeroes extends Set { override def nameOfSet: String = "Crisis: Heroes" }
private class CrisisFleetsAndFortresses extends Set { override def nameOfSet: String = "Crisis: Fleets and Fortresses" }
private class CrisisEvents extends Set { override def nameOfSet: String = "Crisis: Events" }
private class CrisisBasesAndBattleships extends Set { override def nameOfSet: String = "Crisis: Bases & Battleships" }
private class Gambit extends Set { override def nameOfSet: String = "Gambit" }
private class PromoPack1 extends Set { override def nameOfSet: String = "Promo Pack 1" }
private class FirstKickstarterPromoPack extends Set { override def nameOfSet: String = "1st Kickstarter Promo Pack" }
private class PromosBattlecruiserStorageBox extends Set { override def nameOfSet: String = "Promos (Battlecruiser Storage Box)" }
private class PromosImperialFighterDeckBox extends Set { override def nameOfSet: String = "Promos (Imperial Fighter Deck Box)" }
private class PromosDiceTower2016 extends Set { override def nameOfSet: String = "Promos (Dice Tower 2016)" }
private class PromosMechCruiserStorageBox extends Set { override def nameOfSet: String = "Promos (Mech Cruiser Storage Box)" }

object Set {
    def apply(nameOfSet: String): Set = nameOfSet.toLowerCase match {
        case "core set" => new CoreSet
        case "high alert: first strike" => new HighAlertFirstStrike
        case "high alert: tech" => new HighAlertTech
        case "high alert: requisition" => new HighAlertRequisition
        case "high alert: invasion" => new HighAlertInvasion
        case "high alert: heroes" => new HighAlertHeroes
        case "universal storage box" => new UniversalStorageBox
        case "stellar allies" => new StellarAllies
        case "command deck: lost fleet" => new CommandDeckLostFleet
        case "command deck: the unity" => new CommandDeckTheUnity
        case "command deck: the union" => new CommandDeckTheUnion
        case "command deck: the pact" => new CommandDeckThePact
        case "command deck: the coalition" => new CommandDeckTheCoalition
        case "command deck: the alliance" => new CommandDeckTheAlliance
        case "command deck: the alignment" => new CommandDeckTheAlignment
        case "frontiers kickstarter promo pack" => new FrontiersKickstarterPromoPack
        case "year 2 promo pack,\norganized play season 5" => new Year2PromoPackOrganizedPlaySeason5
        case "year 2 promo pack" => new Year2PromoPack
        case "frontiers" => new Frontiers
        case "scenarios" => new Scenarios
        case "united: missions" => new UnitedMissions
        case "united: heroes" => new UnitedHeroes
        case "united: command" => new UnitedCommand
        case "united: assault" => new UnitedAssault
        case "colony wars" => new ColonyWars
        case "cosmic gambit" => new CosmicGambit
        case "crisis: heroes" => new CrisisHeroes
        case "crisis: fleets and fortresses" => new CrisisFleetsAndFortresses
        case "crisis: events" => new CrisisEvents
        case "crisis: bases & battleships" => new CrisisBasesAndBattleships
        case "gambit" => new Gambit
        case "promo pack 1" => new PromoPack1
        case "1st kickstarter promo pack" => new FirstKickstarterPromoPack
        case "promos (battlecruiser storage box)" => new PromosBattlecruiserStorageBox
        case "promos (imperial fighter deck box)" => new PromosImperialFighterDeckBox
        case "promos (dice tower 2016)" => new PromosDiceTower2016
        case "promos (mech cruiser storage box)" => new PromosMechCruiserStorageBox
        case _ => throw new IllegalArgumentException(s"Unknown set: $nameOfSet")
    }
}