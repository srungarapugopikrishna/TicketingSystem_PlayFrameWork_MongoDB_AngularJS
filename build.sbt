name := """SimpleForm"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava)

scalaVersion := "2.11.1"

libraryDependencies ++= Seq(
  javaJdbc,
  javaEbean,
  cache,
  javaWs
)

libraryDependencies ++= Seq(
  "net.vz.mongodb.jackson" % "play-mongo-jackson-mapper_2.10" % "1.1.0"
)

