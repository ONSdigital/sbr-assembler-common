import sbt.Resolver

name := "sbr-assembler-common"

version := "0.1"

scalaVersion := "2.11.12"

lazy val Versions = new {
  val hbase = "1.2.6"
  val spark = "2.3.2"
}

resolvers += "ClouderaRepo" at "https://repository.cloudera.com/artifactory/cloudera-repos"
// resolvers += "Local Maven Repository" at "file:///~/.m2/repository"
resolvers += Resolver.bintrayRepo("ons", "ONS-Registers")

libraryDependencies ++= Seq(
  "org.scalatest" %% "scalatest" % "3.0.5" % "test",

  "org.apache.spark" %% "spark-core" % Versions.spark,
  "org.apache.spark" %% "spark-sql" % Versions.spark,
  "org.apache.hbase" %  "hbase-client" % Versions.hbase,
  "org.apache.hbase" % "hbase-common" % Versions.hbase,

  "com.typesafe" % "config" % "1.3.2"
)
