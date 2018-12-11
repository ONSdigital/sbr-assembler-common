package common.config.environment

import common.config.Configuration
import org.apache.spark.sql.SparkSession

abstract class Environment {

  def withSpark(appName: String, action: SparkSession => Unit): Unit = {
    val spark: SparkSession = getSparkSession(appName)
    action(spark)
    spark.stop()
  }

  def getSparkSession(appName: String) : SparkSession

}

object Environment {

  def isInCluster: Boolean = Configuration.ApplicationEnvironment == "cluster"

  def apply(): Environment = if (isInCluster) ClusterEnvironment else LocalEnvironment

}