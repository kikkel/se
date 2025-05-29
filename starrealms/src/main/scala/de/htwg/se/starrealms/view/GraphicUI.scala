package de.htwg.se.starrealms.view

import scala.swing.Reactor
import de.htwg.se.starrealms.controller.{CommandProcessor, UpdateEvent}
import scalafx.application.Platform
import scalafx.scene.Scene
import scalafx.scene.control.{Button, Label, TextArea, TextField}
import scalafx.scene.layout.{BorderPane, HBox, VBox}
import scalafx.scene.paint.Color
import scalafx.scene.text.Font
import scalafx.stage.Stage
import scala.swing.Publisher

class GraphicUI(processor: CommandProcessor with Publisher, onExit: () => Unit) extends Stage with Reactor {

    title = "Star Realms"
    width = 800
    height = 600

    listenTo(processor)

    reactions += {
      case _: UpdateEvent =>
        Platform.runLater {
          outputArea.appendText("\n" + processor.getState + "\n")
        }
    }

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
                processCommand(input)
                text = ""
            }
        }
    }

    private val outputArea = new TextArea {
        editable = false
        wrapText = true
    }
    private val playingField = new TextArea {
        editable = false
        wrapText = true
        text = "place cards here and view playable actions, count available coins, damage and health"
    }
    private val discardArea = new TextArea {
        editable = false
        wrapText = true
        text = "place discard cards here"
    }
    private val tradeRow = new TextArea {
        editable = false
        wrapText = true
        text = "place trade row cards here"
    }

    private val startButton = new Button("Start Game") {
        onAction = _ => processCommand("t")
    }

    private val replenishButton = new Button("Start turn") {
        onAction = _ => processCommand("s")
    }

    private val resetButton = new Button("Reset Game") {
        onAction = _ => processCommand("r")
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
                processCommand(s"b $input")
                inputField.text = ""
            }
            processCommand("t")
        }
    }

    private val controlPanel = new HBox {
        spacing = 10
        children = Seq(inputField, startButton, replenishButton, buyButton, resetButton, exitButton)
    }

    private val playPanel = new VBox {
        spacing = 10
        children = Seq(tradeRow, playingField, discardArea, outputArea)
    }

    def buildScene: Scene = new Scene {
        root = new BorderPane {
            top = statusLabel
            center = playPanel
            bottom = controlPanel
        }
        stylesheets.add("style.css")
    }

    private def processCommand(command: String): Unit = {
        val result = processor.processCommand(command)
        command match {
            case "t" => tradeRow.text = result
            case "s" => playingField.text = result
            case "b" => playingField.text = result
            case "show trade" => tradeRow.text = result
            case "show hand" => playingField.text = result
            case "show discard" => discardArea.text = result
            case "show turn" => outputArea.text = result
            case _ => // Do nothing for other commands
        }
        val state = processor.getState
        outputArea.text = state
    }

    def run(): Unit = {
        outputArea.appendText("Welcome to Star Realms!\n")
        outputArea.appendText(processor.getState + "\n")
    }
}