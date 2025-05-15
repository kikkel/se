package de.htwg.se.starrealms.model

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec
import java.io.{File, PrintWriter}

object LoadCardsSpec extends AnyWordSpec with Matchers {
  val testCsvPath = "src/main/resources/CoreSet.csv"
  val testCsvContent =
    """Set,Qty,Name,Text,CardType,Faction,Cost,Defense,Role
      |Core Set,1,Battle Blob,"{Gain 8 Combat}<hr>{Blob Ally}: Draw a card.<hr>{Scrap}: {Gain 4 Combat}",Ship,Blob,6,,Trade Deck,
      |Core Set,2,Battle Pod,"{Gain 4 Combat}You may scrap a card in the trade row.<hr>{Blob Ally}: {Gain 2 Combat}",Ship,Blob,2,,Trade Deck,
      |Core Set,1,Blob Carrier,"{Gain 7 Combat}<hr>{Blob Ally}: Acquire any ship for free and put it on top of your deck.",Ship,Blob,6,,Trade Deck,
      |Core Set,2,Blob Destroyer,"{Gain 6 Combat}<hr>{Blob Ally}: You may destroy target base and/or scrap a card in the trade row.",Ship,Blob,4,,Trade Deck,
      |Core Set,3,Blob Fighter,"{Gain 3 Combat}<hr>{Blob Ally}: Draw a card.",Ship,Blob,1,,Trade Deck,
      |Core Set,3,Blob Wheel,"{Gain 1 Combat}<hr>{Scrap}: {Gain 3 Trade}",Base,Blob,3,5,Trade Deck,
      |""".stripMargin

  def writeTestCsv(): Unit = {
    val pw = new PrintWriter(new File(testCsvPath))
    pw.write(testCsvContent)
    pw.close()
  }

  "LoadCards" should {
    "load cards from a resource file path" in {
      writeTestCsv()
      val loader = new CardCSVLoader(testCsvPath)
      loader.loadCardsFromFile()
      val cards = loader.getCardsForSet("Core Set")
      cards should not be empty
    }

    "get all cards across sets" in {
      writeTestCsv()
      val loader = new CardCSVLoader(testCsvPath)
      loader.loadCardsFromFile()
      val allCards = loader.getAllCards
      allCards should not be empty
    }

    "ignore invalid or missing fields" in {
      val badCsvPath = "src/main/resources/CoreSet.csv"
      val badContent =
        """Set,Qty,Name,Text,CardType,Faction,Cost,Defense,Role
          |Core Set,4,,{Gain 1 Combat},Ship,Unaligned,,,Personal Deck,
          |Core Set (First Edition),6,Scorecard (10) | Scorecard (20),,Scorecard,,,,Scorecard,
          |""".stripMargin
      
      val pw = new PrintWriter(new File(badCsvPath))
      pw.write(badContent)
      pw.close()

      val loader = new CardCSVLoader(badCsvPath)
      loader.loadCardsFromFile()
      val cards = loader.getCardsForSet("Core Set")
      cards shouldBe empty
    }

    "throw error or unknown card type or role" in {
      val invalidCsvPath = "src/main/resources/CoreSet.csv"
      val invalidContent =
        """Set,Qty,Name,Text,CardType,Faction,Cost,Defense,Role
          |Core Set,1,Stealth Needle,Copy another ship you've played this turn. Stealth Needle has that ship's faction in addition to Machine Cult.,,Machine Cult,4,,,
          |""".stripMargin

      val pw = new PrintWriter(new File(invalidCsvPath))
      pw.write(invalidContent)
      pw.close()

      val loader = new CardCSVLoader(invalidCsvPath)
      an [IllegalArgumentException] should be thrownBy loader.loadCardsFromFile()
    }


  }

}