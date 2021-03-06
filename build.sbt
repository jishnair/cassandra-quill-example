name := "cassandra-quill-example"

version := "0.1"

scalaVersion := "2.12.3"

libraryDependencies ++= Seq(
  "io.getquill" %% "quill-cassandra" % "1.4.0",
  "com.typesafe" %% "scalalogging-slf4j" % "1.0.1",
  "org.slf4j" %% "slf4j-api" % "1.7.1",
  "org.slf4j" %% "log4j-over-slf4j" % "1.7.1", // for any java classes looking for this
  "ch.qos.logback" %% "logback-classic" % "1.0.3"
)
        