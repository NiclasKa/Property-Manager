name := """Property Manager"""
organization := "com.example"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava)

scalaVersion := "2.12.5"

resolvers ++= Seq(
   "Typesafe" at "http://repo.typesafe.com/typesafe/releases/",
   "Java.net Maven2 Repository" at "http://download.java.net/maven/2/"
)

libraryDependencies += guice

libraryDependencies += "io.swagger" % "swagger-play2_2.12" % "1.7.1"
libraryDependencies += "org.webjars" % "swagger-ui" % "3.43.0"