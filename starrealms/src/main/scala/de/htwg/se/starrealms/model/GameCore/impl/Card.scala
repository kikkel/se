package de.htwg.se.starrealms.model.GameCore.impl

import de.htwg.se.starrealms.model.GameCore.{CardInterface, CardTypeInterface, FactionInterface, AbilityInterface, EditionInterface, EditionFactoryInterface}
import com.google.inject.{Inject, Provides}
import com.google.inject.name.Named

import scala.util.{Try, Success, Failure}
import scalafx.scene.input.KeyCode.S

//--------------------------------------------------------------------------Card Types
abstract class CardType extends CardTypeInterface {
}
class Ship extends CardType with CardTypeInterface { override def cardType: String = "Ship" }
class Base(val defense: String, val isOutpost: Boolean) extends CardType with CardTypeInterface { override def cardType: String = "Base" }

//--------------------------------------------------------------------------Faction Factory

class TradeFederation extends FactionInterface { override def factionName: String = "Trade Federation"; override def matches(other: FactionInterface): Boolean = other.factionName == factionName }
class StarEmpire extends FactionInterface { override def factionName: String = "Star Empire"; override def matches(other: FactionInterface): Boolean = other.factionName == factionName }
class Blob extends FactionInterface { override def factionName: String = "Blob"; override def matches(other: FactionInterface): Boolean = other.factionName == factionName }
class MachineCult extends FactionInterface { override def factionName: String = "Machine Cult"; override def matches(other: FactionInterface): Boolean = other.factionName == factionName }
class CompositeFaction(factions: List[FactionInterface]) extends FactionInterface { override def factionName: String = factions.map(_.factionName).mkString(" / "); override def matches(other: FactionInterface): Boolean = factions.exists(_.matches(other)) }
class Unaligned extends FactionInterface { override def factionName: String = "Unaligned"; override def matches(other: FactionInterface): Boolean = false }

class FactionFactory {
    def apply(factionName: String): FactionInterface = {
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
                case _ => throw new IllegalArgumentException(s"Faction not recognized: $factionName #Factory.scala: objectFaction")
            }
        }
    }
}

// Example usage
// val tradeFederation = Faction("trade federation")

//--------------------------------------------------------------------------Cards


@Provides
case class ParsedCard (
    edition: EditionInterface,
    cardName: String,
    cost: Option[Int],
    primaryAbility: Option[AbilityInterface],
    allyAbility: Option[AbilityInterface],
    scrapAbility: Option[AbilityInterface],
    faction: FactionInterface,
    cardType: Try[CardType],
    qty: Int,
    role: String,
    notes: Option[String]
) extends CardInterface {

    override def render(): String = {
        val cardTypeStr = cardType match {
            case Success(value) => value.toString
            case Failure(exception) => s"Error: ${exception.getMessage} #ParsedCard"
        }
        s"ParsedCard(${edition.nameOfEdition}, $cardName, $cost, ${primaryAbility.map(_.render).getOrElse("None")}, " +
        s"${allyAbility.map(_.render).getOrElse("None")}, ${scrapAbility.map(_.render).getOrElse("None")}, " +
        s"${faction.factionName}, $cardTypeStr), ${notes.getOrElse("No notes")}) #BRIDGE: ParsedCard"
    }
}
@Provides
case class DefaultCard(
    override val edition: EditionInterface,
    override val cardName: String,
    override val primaryAbility: Option[AbilityInterface],
    override val faction: FactionInterface,
    override val cardType: Try[CardType],
    override val qty: Int,
    override val role: String


) extends CardInterface {
    override def render(): String = {
        val cardTypeStr = cardType match {
            case Success(value) => value.toString
            case Failure(exception) => s"Error: ${exception.getMessage} #DefaultCard"
        }
        s"DefaultCard(${edition.nameOfEdition}, $cardName, " +
        s"${primaryAbility.map(_.render).getOrElse("None")}, ${faction.factionName} $cardTypeStr) #BRIDGE: DefaultCard"
    }
}
@Provides
case class ExplorerCard(
    override val edition: EditionInterface,
    override val cardName: String,
    val cost: Int,
    override val primaryAbility: Option[AbilityInterface],
    val scrapAbility: Option[AbilityInterface],
    override val faction: FactionInterface,
    override val cardType: Try[CardType],
    override val qty: Int,
    override val role: String
) extends CardInterface {
    override def render(): String = {
        val cardTypeStr = cardType match {
            case Success(value) => value.toString
            case Failure(exception) => s"Error: ${exception.getMessage} #ExplorerCard"
        }
        s"ExplorerCard(${edition.nameOfEdition}, $cardName, $cost, ${primaryAbility.map(_.render).getOrElse("None")}, " +
        s"${scrapAbility.map(_.render).getOrElse("None")}, ${faction.factionName}, $cardTypeStr) #BRIDGE: ExplorerCard"
    }
}
@Provides
case class FactionCard(
    override val edition: EditionInterface,
    override val cardName: String,
    val cost: Int,
    override val primaryAbility: Option[AbilityInterface],
    val allyAbility: Option[AbilityInterface],
    val scrapAbility: Option[AbilityInterface],
    override val faction: FactionInterface,
    override val cardType: Try[CardType],
    override val qty: Int,
    override val role: String,
    val notes: Option[String]
    ) extends CardInterface {
        override def render(): String = {
            val cardTypeStr = cardType match {
                case Success(value) => value.toString
                case Failure(exception) => s"Error: ${exception.getMessage} #FactionCard"
            }
            s"FactionCard(${edition.nameOfEdition}, $cardName, $cost, ${primaryAbility.map(_.render).getOrElse("None")}, " +
                s"${allyAbility.map(_.render).getOrElse("None")}, ${scrapAbility.map(_.render).getOrElse("None")}, " +
                s"${faction.factionName}, $cardTypeStr), ${notes.getOrElse("No notes")}) #BRIDGE: FactionCard"
        }
}

//--------------------------------------------------------------------------Edition Factory

class CoreSet extends EditionInterface { override def nameOfEdition: String = "Core Set" }
/* class HighAlertFirstStrike extends Edition with EditionInterface { override def nameOfEdition: String = "High Alert: First Strike" }
class HighAlertTech extends Edition with EditionInterface { override def nameOfEdition: String = "High Alert: Tech" }
class HighAlertRequisition extends Edition with EditionInterface { override def nameOfEdition: String = "High Alert: Requisition" }
class HighAlertInvasion extends Edition with EditionInterface { override def nameOfEdition: String = "High Alert: Invasion" }
class HighAlertHeroes extends Edition with EditionInterface { override def nameOfEdition: String = "High Alert: Heroes" }
class UniversalStorageBox extends Edition with EditionInterface { override def nameOfEdition: String = "Universal Storage Box" }
class StellarAllies extends Edition with EditionInterface { override def nameOfEdition: String = "Stellar Allies" }
class CommandDeckLostFleet extends Edition with EditionInterface { override def nameOfEdition: String = "Command Deck: Lost Fleet" }
class CommandDeckTheUnity extends Edition with EditionInterface { override def nameOfEdition: String = "Command Deck: The Unity" }
class CommandDeckTheUnion extends Edition with EditionInterface { override def nameOfEdition: String = "Command Deck: The Union" }
class CommandDeckThePact extends Edition with EditionInterface { override def nameOfEdition: String = "Command Deck: The Pact" }
class CommandDeckTheCoalition extends Edition with EditionInterface { override def nameOfEdition: String = "Command Deck: The Coalition" }
class CommandDeckTheAlliance extends Edition with EditionInterface { override def nameOfEdition: String = "Command Deck: The Alliance" }
class CommandDeckTheAlignment extends Edition with EditionInterface { override def nameOfEdition: String = "Command Deck: The Alignment" }
class FrontiersKickstarterPromoPack extends Edition with EditionInterface { override def nameOfEdition: String = "Frontiers Kickstarter Promo Pack" }
class Year2PromoPackOrganizedPlaySeason5 extends Edition with EditionInterface { override def nameOfEdition: String = "Year 2 Promo Pack,\nOrganized Play Season 5" }
class Year2PromoPack extends Edition with EditionInterface { override def nameOfEdition: String = "Year 2 Promo Pack" }
class Frontiers extends Edition with EditionInterface { override def nameOfEdition: String = "Frontiers" }
class Scenarios extends Edition with EditionInterface { override def nameOfEdition: String = "Scenarios" }
class UnitedMissions extends Edition with EditionInterface { override def nameOfEdition: String = "United: Missions" }
class UnitedHeroes extends Edition with EditionInterface { override def nameOfEdition: String = "United: Heroes" }
class UnitedCommand extends Edition with EditionInterface { override def nameOfEdition: String = "United: Command" }
class UnitedAssault extends Edition with EditionInterface { override def nameOfEdition: String = "United: Assault" }
 */
class ColonyWars extends EditionInterface { override def nameOfEdition: String = "Colony Wars" }
/* class CosmicGambit extends Edition with EditionInterface { override def nameOfEdition: String = "Cosmic Gambit" }
class CrisisHeroes extends Edition with EditionInterface { override def nameOfEdition: String = "Crisis: Heroes" }
class CrisisFleetsAndFortresses extends Edition with EditionInterface { override def nameOfEdition: String = "Crisis: Fleets and Fortresses" }
class CrisisEvents extends Edition with EditionInterface { override def nameOfEdition: String = "Crisis: Events" }
class CrisisBasesAndBattleships extends Edition with EditionInterface { override def nameOfEdition: String = "Crisis: Bases & Battleships" }
class Gambit extends Edition with EditionInterface { override def nameOfEdition: String = "Gambit" }
class PromoPack1 extends Edition with EditionInterface { override def nameOfEdition: String = "Promo Pack 1" }
class FirstKickstarterPromoPack extends Edition with EditionInterface { override def nameOfEdition: String = "1st Kickstarter Promo Pack" }
class PromosBattlecruiserStorageBox extends Edition with EditionInterface { override def nameOfEdition: String = "Promos (Battlecruiser Storage Box)" }
class PromosImperialFighterDeckBox extends Edition with EditionInterface { override def nameOfEdition: String = "Promos (Imperial Fighter Deck Box)" }
class PromosDiceTower2016 extends Edition with EditionInterface { override def nameOfEdition: String = "Promos (Dice Tower 2016)" }
class PromosMechCruiserStorageBox extends Edition with EditionInterface { override def nameOfEdition: String = "Promos (Mech Cruiser Storage Box)" }
 */
class EditionFactory @Inject() (
    @Named("CoreSet") coreSet: EditionInterface,
    @Named("ColonyWars") colonyWars: EditionInterface
) extends EditionFactoryInterface {
    override def create(nameOfEdition: String): EditionInterface = nameOfEdition.toLowerCase match {
        case "core set" => coreSet
        /* case "high alert: first strike" => new HighAlertFirstStrike
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
        case "united: assault" => new UnitedAssault */
        case "colony wars" => colonyWars
        /* case "cosmic gambit" => new CosmicGambit
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
        case "promos (mech cruiser storage box)" => new PromosMechCruiserStorageBox */
        case _ => throw new IllegalArgumentException(s"Unknown Edition: $nameOfEdition")
    }
}