package de.htwg.se.starrealms.view

import de.htwg.se.starrealms.controller.{Controller, CommandHandler}
import de.htwg.util.Observer
import scala.swing._
import scala.swing.event._
import javax.imageio.ImageIO
import java.io.File
import java.awt.Color
import java.awt.Font

class GraphicUI(controller: Controller, onExit: () => Unit) extends MainFrame with Reactor with Observer {
    title = "Star Realms"
    preferredSize = new Dimension(800, 600)

    private val commandHandler = new CommandHandler(controller)


    // UI Components
    private val statusLabel = new Label("Welcome to Star Realms!")
    statusLabel.foreground = Color.WHITE
    statusLabel.font = new Font(statusLabel.font.getName, Font.BOLD, 24)
    private val inputField = new TextField(20)
    private val outputArea = new TextArea(10, 40) {
        editable = false
        lineWrap = true
        wordWrap = true
    }

    val ashPurple = new Color(75, 60, 90)

    outputArea.background = ashPurple
    outputArea.foreground = Color.WHITE
    outputArea.border = Swing.EmptyBorder(10)

    val ashBlue = new Color(80, 110, 140)

    val startButton = new Button("Start Game") {
        background = ashPurple
        foreground = ashBlue
        opaque = true
    }
    val replenishButton = new Button("replenish Trade Row") {
        background = ashPurple
        foreground = ashBlue
        opaque = true
    }
    val resetButton = new Button("reset Game") {
        background = ashPurple
        foreground = ashBlue
        opaque = true
    }
    val exitButton = new Button("Exit Game") {
        background = ashPurple
        foreground = ashBlue
        opaque = true
    }

        listenTo(inputField.keys, startButton, replenishButton, resetButton, exitButton)

        reactions += {
            case KeyPressed(`inputField`, Key.Enter, _, _) =>
                processInput()
            case ButtonClicked(`startButton`) =>
                outputArea.append("> Start Game clicked\n")
                val result = commandHandler.processCommand("s")
                outputArea.append(result + "\n")
            case ButtonClicked(`replenishButton`) =>
                outputArea.append("> Replenish Trade Row clicked\n")
                val result = commandHandler.processCommand("t")
                outputArea.append(result + "\n")
            case ButtonClicked(`resetButton`) =>
                outputArea.append("> Reset Game clicked\n")
                val result = commandHandler.processCommand("r")
                outputArea.append(result + "\n")
            case ButtonClicked(`exitButton`) =>
                outputArea.append("> Exit Game clicked\n")
                onExit()
                close()
        }


    val controlPanel = new BoxPanel(Orientation.Horizontal) {
        contents += inputField
        contents += startButton
        contents += replenishButton
        contents += resetButton
        contents += exitButton
        background = ashPurple
        opaque = true
    }

    val contentPanel = new BorderPanel {
        opaque = false
        layout(statusLabel) = BorderPanel.Position.North
        layout(new ScrollPane(outputArea)) = BorderPanel.Position.Center
        layout(controlPanel) = BorderPanel.Position.South

        listenTo(inputField.keys)
        reactions += {
        case KeyPressed(`inputField`, Key.Enter, _, _) => processInput()
        }
    }

    val backgroundPanel = new BorderPanel {
    background = ashPurple
    opaque = true

    layout(statusLabel) = BorderPanel.Position.North
    layout(new ScrollPane(outputArea)) = BorderPanel.Position.Center
    layout(controlPanel) = BorderPanel.Position.South
    }

    contents = backgroundPanel
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