package de.htwg.se.starrealms.view

import de.htwg.util.Observer
import de.htwg.se.starrealms.controller.GameStateReadOnly
import de.htwg.se.starrealms.model.Card

import scalafx.application.Platform
import scalafx.scene.Scene
import scalafx.scene.control.{Button, Label, TextArea, TextField}
import scalafx.scene.layout.{BorderPane, HBox, VBox}
import scalafx.scene.paint.Color
import scalafx.scene.text.Font
import scalafx.stage.Stage

/*
Use readOnlyState sparingly, and only for:
	•	read-only visual rendering
	•	shared UI components (TUI + GUI) that need direct game context
example of how to use readOnlyState in GUI:

outputArea.appendText(
  s"Cards in hand: ${readOnlyState.getHand(readOnlyState.getCurrentPlayer).map(_.cardName).mkString(", ")}"
) 
*/

class GraphicUI(processor: CommandAdapter, readOnlyState: GameStateReadOnly, onExit: () => Unit) extends Stage with Observer {
    private val baseRenderer = new CardRenderer()
    private val cardRenderer: Renderer[Card] = new ColourHighlightDecorator(
        new CompactCardDecorator(
            new LoggingDecorator(baseRenderer)
        )
    )


    title = "Star Realms"
    width = 800
    height = 600

    // UI Components
    private val statusLabel = new Label("Welcome to Star Realms!") {
        textFill =  Color.Purple
        font = Font.font(null, 24)
    }

    private val inputField = new TextField {
        promptText = "Enter command here"
        onAction = _ => {
            val input = text.value.trim
            if (input.nonEmpty) {
                handleInput(input)
                text = ""
            }
        }
    }

    private val outputArea = new TextArea {
        editable = false
        wrapText = true
    }

    private val startButton = new Button("Start Game") {
        onAction = _ => handleInput("t")
    }

    private val replenishButton = new Button("Start turn") {
        onAction = _ => handleInput("s")
    }

    private val resetButton = new Button("Reset Game") {
        onAction = _ => handleInput("r")
    }

    private val exitButton = new Button("Exit Game") {
        onAction = _ => {
            onExit()
            Platform.exit()
        }
    }
    private val buyButton = new Button("Buy Card") {
        onAction = _ => {
            val input = inputField.text.value.trim
            if (input.nonEmpty) {
                handleInput(s"b $input")
                inputField.text = ""
            }
        }
    }

    private val controlPanel = new HBox {
        spacing = 10
        children = Seq(inputField, startButton, replenishButton, buyButton, resetButton, exitButton)
    }

    private val contentPanel = new VBox {
        spacing = 10
        children = Seq(statusLabel, outputArea, controlPanel)
    }

    scene = new Scene {
        root = new BorderPane {
            top = statusLabel
            center = outputArea
            bottom = controlPanel
        }
        stylesheets.add("style.css")
    }

    private def handleInput(command: String): Unit = {
        outputArea.appendText(s"> $command\n")
        val result = processor.handleInput(command)
        outputArea.appendText(result + "\n")
    }

    override def update: Unit = {
        val handCards = readOnlyState.getHand(readOnlyState.getCurrentPlayer)  
        val renderedHand = handCards.map(cardRenderer.render).mkString("\n---\n")

        Platform.runLater {
            outputArea.text = (s"Hand:\n$renderedHand\n")
            outputArea.appendText(processor.getState + "\n")
        }

    }

    def run(): Unit = {
        show()
        outputArea.appendText("Welcome to Star Realms!\n")
        outputArea.appendText(processor.getState + "\n")

    }
}