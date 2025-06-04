package de.htwg.util

trait Command { def doMove: Unit; def undoMove: Unit; def redoMove: Unit = doMove }