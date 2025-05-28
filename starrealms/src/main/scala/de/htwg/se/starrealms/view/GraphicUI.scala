package de.htwg.se.starrealms.view

import de.htwg.se.starrealms.controller.{Controller, CommandHandler}
import de.htwg.util.Observer
import scalafx.application.Platform
import scalafx.scene.Scene
import scalafx.scene.control.{Button, Label, TextArea, TextField}
import scalafx.scene.layout.{BorderPane, HBox, VBox}
import scalafx.scene.paint.Color
import scalafx.scene.text.Font
import scalafx.stage.Stage

class GraphicUI(controller: Controller, onExit: () => Unit) extends Stage with Observer {
    title = "Star Realms"
    width = 800
    height = 600

    private val commandHandler = new CommandHandler(controller)

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

    private val startButton = new Button("Start Game") {
        onAction = _ => processCommand("s")
    }

    private val replenishButton = new Button("Replenish Trade Row") {
        onAction = _ => processCommand("t")
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

    private val controlPanel = new HBox {
        spacing = 10
        children = Seq(inputField, startButton, replenishButton, resetButton, exitButton)
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

    controller.addObserver(this)

    private def processCommand(command: String): Unit = {
        outputArea.appendText(s"> $command\n")
        val result = commandHandler.processCommand(command)
        outputArea.appendText(result + "\n")
    }

    override def update: Unit = {
        outputArea.appendText("\n" + controller.getState + "\n")
    }

    def run(): Unit = {
        show()
        outputArea.appendText("Welcome to Star Realms!\n")
        outputArea.appendText(controller.getState + "\n")
    }
}