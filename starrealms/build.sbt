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
      "org.scalafx" %% "scalafx" % "20.0.0-R31",
      "org.scalatest" %% "scalatest" % "3.2.16" % Test,
      "org.scalameta" %% "munit" % "1.0.0" % Test,
      "org.scalactic" %% "scalactic" % "3.2.19",
      "com.nrinaudo" %% "kantan.csv" % "0.6.1",
      "com.nrinaudo" %% "kantan.csv-generic" % "0.6.1",
      "net.codingwell" %% "scala-guice" % "7.0.0",
      //"org.scala-lang.modules" %% "scala-xml" % "2.4.0",
      //"com.typesafe.play" %% "play-json" % "3.0.4" //UH OHHHHH!!
      "org.scala-lang.modules" %% "scala-xml" % "2.4.0",
      "org.playframework" %% "play-json" % "3.0.4"
    ),
    Compile / mainClass := Some("de.htwg.se.starrealms.app.GameApp")
  )

