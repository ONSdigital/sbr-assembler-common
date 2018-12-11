import sbt.Keys.organization
import sbt.Resolver

scalaVersion := "2.11.8"

lazy val Versions = new {
  val hbase = "1.2.6"
  val spark = "2.2.0"
}

name := "sbr-assembler-common"
organization := "uk.gov.ons"
version := "0.1-SNAPSHOT"

// disable using the Scala version in output paths and artifacts
crossPaths := false

resolvers += "ClouderaRepo" at "https://repository.cloudera.com/artifactory/cloudera-repos"
// resolvers += "Local Maven Repository" at "file:///~/.m2/repository"
resolvers += Resolver.bintrayRepo("ons", "ONS-Registers")

libraryDependencies ++= Seq(
  "org.scalatest" %% "scalatest" % "3.0.5" % "test",

  "org.apache.spark" %% "spark-core" % Versions.spark,
  "org.apache.spark" %% "spark-sql" % Versions.spark,
  "org.apache.hbase" % "hbase-client" % Versions.hbase,
  "org.apache.hbase" % "hbase-common" % Versions.hbase,

  "org.apache.zookeeper" % "zookeeper" % "3.4.5-cdh5.13.1",
  "org.apache.curator" % "curator-framework" % "2.12.0",
  "org.apache.curator" % "curator-recipes" % "2.12.0",

  "org.scalatest" %% "scalatest" % "3.0.5" % "test",
  "org.apache.curator" % "curator-test" % "4.0.1" % Test,

  "com.typesafe" % "config" % "1.3.2"
)
