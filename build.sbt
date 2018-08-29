organization := "com.ocado"

name := "FP in Scala Workshop"

version := "0.1.0"

scalaVersion := "2.12.6"

scalacOptions ++= Seq(
  "-deprecation",
  "-encoding", "UTF-8",
  "-feature",
  "-target:jvm-1.8",
  "-unchecked",
  "-language:postfixOps",
  "-Xlint",
  "-Ywarn-dead-code",
  "-Ywarn-inaccessible",
  "-Ywarn-unused",
  "-Ywarn-unused-import",
  "-Ypartial-unification",
  "-language:higherKinds"
)

libraryDependencies += "org.typelevel" %% "cats-core" % "1.2.0"



