// Constants //

val projectName = "uuid-random-derived-benchmark"

// Groups //

val openJdkJmhG = "org.openjdk.jmh"

// Artifacts //

val jmhCoreA = "jmh-core"

// Versions //

val jmhV = "1.21"

// GAVs //

lazy val jmhCore = openJdkJmhG % jmhCoreA % jmhV

// ThisBuild Scoped Settings //

ThisBuild / organization := "io.isomarcte"
ThisBuild / scalaVersion := "2.12.8"
ThisBuild / version      := "1.0.0-SNAPSHOT"

// Enable

// Root Project //

lazy val root = (project in file(".")).settings(
  name := projectName
).aggregate(uuidGen, jmh)

// Projects //

lazy val uuidGen = project.settings(
  name := s"$projectName-uuidGen"
)

lazy val jmh = project.settings(
  name := s"$projectName-jmh",
  libraryDependencies ++= Seq(
    jmhCore
  )
).enablePlugins(
  JmhPlugin
).dependsOn(
  uuidGen
)
