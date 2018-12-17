package common.spark

import common.config.environment.Environment
import org.apache.spark.sql.SparkSession

object SparkSessionManager {

  def withSpark(appName: String, action: SparkSession => Unit): Unit = {

    implicit val spark: SparkSession = Environment().getSparkSession(appName)
    action(spark)
    spark.stop()
  }

}
