name := """Property Manager"""
organization := "com.example"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava, PlayEbean)

scalaVersion := "2.12.5"

resolvers ++= Seq(
   "Typesafe" at "http://repo.typesafe.com/typesafe/releases/",
   "Java.net Maven2 Repository" at "http://download.java.net/maven/2/"
)

libraryDependencies ++= Seq(guice, evolutions, javaJdbc, javaWs)

libraryDependencies += "io.swagger" % "swagger-play2_2.12" % "1.7.1"
libraryDependencies += "org.webjars" % "swagger-ui" % "3.43.0"
libraryDependencies += "org.postgresql" % "postgresql" % "42.3.2"
libraryDependencies += "org.glassfish.jaxb" % "jaxb-core" % "2.3.0.1"
libraryDependencies += "org.glassfish.jaxb" % "jaxb-runtime" % "2.3.2"
libraryDependencies += ehcache