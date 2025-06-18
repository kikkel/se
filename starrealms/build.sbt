import Dependencies._

ThisBuild / scalaVersion     := "2.13.16"
ThisBuild / version          := "0.1.0-SNAPSHOT"
ThisBuild / organization     := "com.example"
ThisBuild / organizationName := "example"
enablePlugins(CoverallsPlugin)

lazy val root = (project in file("."))
  .settings(
    name := "StarRealms",
    scalacOptions += "-deprecation",
    libraryDependencies ++= Seq(
      //"org.scala-lang.modules" %% "scala-swing" % "3.0.0",
      "org.scalafx" %% "scalafx" % "20.0.0-R31",
      "org.scalatest" %% "scalatest" % "3.2.16" % Test,
      "org.scalameta" %% "munit" % "1.0.0" % Test,
      "org.scalactic" %% "scalactic" % "3.2.19",
      "com.nrinaudo" %% "kantan.csv" % "0.6.1",
      "com.nrinaudo" %% "kantan.csv-generic" % "0.6.1",
      "org.scala-lang.modules" %% "scala-swing" % "3.0.0",
      "net.codingwell" %% "scala-guice" % "7.0.0"
    ),
    Compile / mainClass := Some("de.htwg.se.starrealms.app.GameApp")
  )


// See https://www.scala-sbt.org/1.x/docs/Using-Sonatype.html for instructions on how to publish to Sonatype.
