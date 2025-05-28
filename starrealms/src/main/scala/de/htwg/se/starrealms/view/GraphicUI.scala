package de.htwg.se.starrealms.view

import de.htwg.se.starrealms.controller.{Controller, CommandHandler}
import de.htwg.util.Observer
import scala.swing._
import scala.swing.event._
import javax.swing.BorderFactory

class GraphicUI(controller: Controller, onExit: () => Unit) extends MainFrame with Reactor with Observer {
    title = "Star Realms"
    preferredSize = new Dimension(800, 600)

    private val commandHandler = new CommandHandler(controller)

    // UI Components
    private val statusLabel = new Label("Welcome to Star Realms!")
    private val inputField = new TextField(20)
    private val outputArea = new TextArea(10, 40) {
        editable = false
        lineWrap = true
        wordWrap = true
    }

    val startButton = new Button("Start Game")
    val replenishButton = new Button("replenish Trade Row")
    val resetButton = new Button("reset Game")
    val exitButton = new Button("Exit Game")

    val controlPanel = new BoxPanel(Orientation.Horizontal) {
    border = BorderFactory.createTitledBorder("Actions")
    contents += inputField
    contents += startButton
    contents += replenishButton
    contents += resetButton
    contents += exitButton
    contents += inputField
  }

    // Layout
    contents = new BorderPanel {
        layout(statusLabel) = BorderPanel.Position.North
        layout(outputArea) = BorderPanel.Position.Center
        //layout(inputField) = BorderPanel.Position.South
        layout(controlPanel) = BorderPanel.Position.South

        listenTo(inputField.keys)
        reactions += {
        case KeyPressed(`inputField`, Key.Enter, _, _) => processInput()
        }
    }

    // Observer registrieren
    controller.addObserver(this)

    def processInput(): Unit = {
        val inputText = inputField.text.trim
        inputField.text = ""
        inputText match {
            case "x" =>
            onExit()
            close()
            case "s" | "t" | "r" | "e" | "z" | "y" | "show" =>
            outputArea.append(s"> $inputText\n")
            val result = commandHandler.processCommand(inputText)
            outputArea.append(result + "\n")
            case p if p.matches("""p \d+""") =>
            outputArea.append(s"> $inputText\n")
            val result = commandHandler.processCommand(inputText)
            outputArea.append(result + "\n")
            case b if b.matches("""b \d+""") =>
            outputArea.append(s"> $inputText\n")
            val result = commandHandler.processCommand(inputText)
            outputArea.append(result + "\n")
            case other if other.nonEmpty =>
            outputArea.append(s"> $inputText\n")
            outputArea.append("Ungültiger Befehl!\n")
            case _ =>
        }
    }

    // Observer-Methode: Wird bei jedem Controller-Update aufgerufen
    override def update: Unit = {
        outputArea.append("\n" + controller.getState + "\n")
    }

    def run(): Unit = {
        visible = true
        inputField.requestFocusInWindow()
        outputArea.append("Welcome to Star Realms!\n")
        outputArea.append(controller.getState + "\n")
    }
}