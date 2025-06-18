package de.htwg.util

trait CommandInterface { def doMove: Unit; def undoMove: Unit; def redoMove: Unit = doMove }