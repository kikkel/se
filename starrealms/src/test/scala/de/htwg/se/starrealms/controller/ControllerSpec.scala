package de.htwg.se.starrealms.controller

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec
import de.htwg.se.starrealms.model._
import java.io.{ByteArrayOutputStream, PrintStream}

class ControllerSpec extends AnyWordSpec with Matchers {

	"A GameLogicController" should {

		"return the game state using getGameState" in {
		val mockGameLogic = new GameLogic {
			override def optionsMenu(): String = "Mocked Options Menu"
		}
		val controller = new GameLogicController {
			override val gameLogic: GameLogic = mockGameLogic
		}

		controller.getGameState shouldBe "Mocked Options Menu"
		}

		"reset the game using resetGame" in {
		var resetCalled = false
		val mockGameLogic = new GameLogic {
			override def resetGame(): Unit = resetCalled = true
		}
		val controller = new GameLogicController {
			override val gameLogic: GameLogic = mockGameLogic
		}

		controller.resetGame()
		resetCalled shouldBe true
		}
	}
	"A DeckController" should {

		"draw a card and return the correct message when a card is available" in {
		val mockDeck = new DefaultDeck("TestDeck", "Default", List(CardFactory.createCard("Scout")))
		val controller = new DeckController {
			override val deck: DefaultDeck = mockDeck
		}

		val result = controller.drawCard("Scout")
		//result should not include("Drew card: Scout")
		}

		"return a message when no cards of the specified type are left in the deck" in {
		val mockDeck = new DefaultDeck("TestDeck", "Default", List())
		val controller = new DeckController {
			override val deck: DefaultDeck = mockDeck
		}

		val result = controller.drawCard("Scout")
		//result should include("No Scout cards left in the deck.")
		}

		"return the current deck state" in {
		val mockDeck = new DefaultDeck("TestDeck", "Default", List(CardFactory.createCard("Scout"), CardFactory.createCard("Viper")))
		val controller = new DeckController {
			override val deck: DefaultDeck = mockDeck
		}

		val result = controller.getDeckState
		result should include("Scout")
		result should include("Viper")
		}

		"reset the deck" in {
		val mockDeck = new DefaultDeck("TestDeck", "Default", List(CardFactory.createCard("Scout"), CardFactory.createCard("Viper")))
		val controller = new DeckController {
			override val deck: DefaultDeck = mockDeck
		}
		controller.resetDeck()
		mockDeck.getCards.size shouldBe 10
		}
	}

	"A Controller" should {

		"draw a Scout card and print the correct message" in {
		val gameLogic = new GameLogic
		val deck = new DefaultDeck("DefaultDeck", "Default", List(CardFactory.createCard("Scout")))
		val controller = new Controller(gameLogic, deck)

		val output = new ByteArrayOutputStream()
		Console.withOut(new PrintStream(output)) {
			controller.drawScout()
		}
		val printedOutput = output.toString
		//printedOutput should include("Scout card drawn: ")
		}
		"print a message when the drawn card is not a Scout" in {
			val gameLogic = new GameLogic
			val deck = new DefaultDeck("DefaultDeck", "Default", List(CardFactory.createCard("Viper")))
			val controller = new Controller(gameLogic, deck)

			val output = new ByteArrayOutputStream()
			Console.withOut(new PrintStream(output)) {
				controller.drawScout()
			}
			val printedOutput = output.toString
			//printedOutput should include("The drawn card is not a Scout card.")
		}
		"print a message when no Scout cards are left in the deck" in {
			val gameLogic = new GameLogic
			val deck = new DefaultDeck("DefaultDeck", "Default", List())
			val controller = new Controller(gameLogic, deck)
			val output = new ByteArrayOutputStream()
			Console.withOut(new PrintStream(output)) {
				controller.drawScout()
			}
			val printedOutput = output.toString
			//printedOutput should include("No Scout cards left in the deck.")
		}

		"draw a Viper card and print the correct message" in {
		val gameLogic = new GameLogic
		val deck = new DefaultDeck("DefaultDeck", "Default", List(CardFactory.createCard("Viper")))
		val controller = new Controller(gameLogic, deck)

		val output = new ByteArrayOutputStream()
		Console.withOut(new PrintStream(output)) {
			controller.drawViper()
		}
		val printedOutput = output.toString
		//printedOutput should include("Viper card drawn: Viper")
		}

		"print a message when the drawn card is not a Viper" in {
		val gameLogic = new GameLogic
		val deck = new DefaultDeck("DefaultDeck", "Default", List(CardFactory.createCard("Scout")))
		val controller = new Controller(gameLogic, deck)

		val output = new ByteArrayOutputStream()
		Console.withOut(new PrintStream(output)) {
			controller.drawViper()
		}
		val printedOutput = output.toString
		printedOutput should include("The drawn card is not a Viper card.")
		}
		"print a message when no Viper cards are left in the deck" in {
		val gameLogic = new GameLogic
		val deck = new DefaultDeck("DefaultDeck", "Default", List())
		val controller = new Controller(gameLogic, deck)

		val output = new ByteArrayOutputStream()
		Console.withOut(new PrintStream(output)) {
			controller.drawViper()
		}
		val printedOutput = output.toString
		//printedOutput should include("No Viper cards left in the deck.")
		}

		"reset the game and print the correct message" in {
		val gameLogic = new GameLogic
		val deck = new DefaultDeck("DefaultDeck", "Default", List(CardFactory.createCard("Scout"), CardFactory.createCard("Viper")))
		val controller = new Controller(gameLogic, deck)

		val output = new ByteArrayOutputStream()
		Console.withOut(new PrintStream(output)) {
			controller.resetGame()
		}
		val printedOutput = output.toString
		printedOutput should include("Game has been reset.")
		deck.getCards.count(_.name == "Scout") shouldBe 8
		deck.getCards.count(_.name == "Viper") shouldBe 2
		}

		"return the game state" in {
		val gameLogic = new GameLogic
		val deck = new DefaultDeck("DefaultDeck", "Default", List())
		val controller = new Controller(gameLogic, deck)

		val gameState = controller.getGameState
		gameState should include("Deck:")
		}
		"process input for drawing a Scout card" in {
		val gameLogic = new GameLogic
		val deck = new DefaultDeck("DefaultDeck", "Default", List(CardFactory.createCard("Scout")))
		val controller = new Controller(gameLogic, deck)

		val result = controller.processInput("s")
		//result should include("Drew card: Scout")
		}

		"process input for drawing a Viper card" in {
		val gameLogic = new GameLogic
		val deck = new DefaultDeck("DefaultDeck", "Default", List(CardFactory.createCard("Viper")))
		val controller = new Controller(gameLogic, deck)

		val result = controller.processInput("v")
		//result should include("Drew card: Viper")
		}

		"process input for resetting the game" in {
		val gameLogic = new GameLogic
		val deck = new DefaultDeck("DefaultDeck", "Default", List())
		val controller = new Controller(gameLogic, deck)

		val result = controller.processInput("r")
		result should include("Game and deck have been reset.")
		}

		"handle invalid input" in {
		val gameLogic = new GameLogic
		val deck = new DefaultDeck("DefaultDeck", "Default", List())
		val controller = new Controller(gameLogic, deck)

		val result = controller.processInput("invalid")
		result should include("Unknown command: invalid")
		}
  	}
}