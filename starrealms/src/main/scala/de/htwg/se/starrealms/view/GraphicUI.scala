package de.htwg.se.starrealms.view

import de.htwg.se.starrealms.controller.{Controller, CommandHandler}
import de.htwg.util.Observer
import scala.swing._
import scala.swing.event._

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

  // Layout
  contents = new BorderPanel {
    layout(statusLabel) = BorderPanel.Position.North
    layout(outputArea) = BorderPanel.Position.Center
    layout(inputField) = BorderPanel.Position.South

    listenTo(inputField)
    reactions += {
      case _: EditDone => processInput()
    }
  }

  // Observer registrieren
  controller.addObserver(this)

  def processInput(): Unit = {
    val inputText = inputField.text.trim
    if (inputText == "x") {
      onExit()
      close()
    } else if (inputText.nonEmpty) {
      outputArea.append(s"> $inputText\n")
      outputArea.append(commandHandler.processCommand(inputText) + "\n")
      inputField.text = ""
      // Kein updateStatus mehr nötig, update kommt durch Observer!
    }
  }

  // Observer-Methode: Wird bei jedem Controller-Update aufgerufen
  override def update: Unit = {
    outputArea.append("\n" + controller.getState + "\n")
  }

  def run(): Unit = {
    visible = true
    outputArea.append("Welcome to Star Realms!\n")
    outputArea.append(controller.getState + "\n")
  }
}