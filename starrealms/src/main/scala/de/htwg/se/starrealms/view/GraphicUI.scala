package de.htwg.se.starrealms.view

import de.htwg.se.starrealms.controller.CommandProcessor
import de.htwg.util.Observer
import scalafx.application.Platform
import scalafx.scene.Scene
import scalafx.scene.control.{Button, Label, TextArea, TextField}
import scalafx.scene.layout.{BorderPane, HBox, VBox}
import scalafx.scene.paint.Color
import scalafx.scene.text.Font

class GraphicUI(processor: CommandProcessor, onExit: () => Unit) extends Observer {


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
        //outputArea.appendText(s"> $command\n")
        val result = processor.processCommand(command)
        //define textarea depending on command
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

    override def update: Unit = {
        outputArea.appendText("\n" + processor.getState + "\n")

    }

    def run(): Unit = {
        outputArea.appendText("Welcome to Star Realms!\n")
        outputArea.appendText(processor.getState + "\n")

    }
}