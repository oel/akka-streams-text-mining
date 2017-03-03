name := "Akka Stream Text-mining"

version := "1.0"

scalaVersion := "2.11.8"
lazy val akkaVersion = "2.4.9"

scalacOptions ++= Seq("-deprecation", "-feature")

resolvers += "Typesafe Repository" at "http://repo.typesafe.com/typesafe/releases/"

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-actor" % akkaVersion,
  "com.typesafe.akka" %% "akka-stream" % akkaVersion
)
