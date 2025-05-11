import sbt._

object Dependencies {
  lazy val munit = "org.scalameta" %% "munit" % "0.7.29"
  lazy val scalaCsv: ModuleID = "com.github.tototoshi" %% "scala-csv" % "1.3.10"
}
