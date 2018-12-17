package common.config.environment

import common.config.ONSConfiguration
import org.apache.spark.sql.SparkSession

abstract class Environment extends Serializable {

  def withSpark(appName: String, action: SparkSession => Unit): Unit = {
    val spark: SparkSession = getSparkSession(appName)
    action(spark)
    spark.stop()
  }

  def getSparkSession(appName: String) : SparkSession

}

object Environment extends Serializable {

  def isInCluster: Boolean = ONSConfiguration.ApplicationEnvironment == "cluster"

  def apply(): Environment = if (isInCluster) ClusterEnvironment else LocalEnvironment

}