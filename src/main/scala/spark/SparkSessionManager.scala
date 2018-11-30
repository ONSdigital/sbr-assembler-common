package spark

import config.Configuration._
import org.apache.spark.sql.SparkSession

object SparkSessionManager {

  def withSpark(appName: String)(action: SparkSession => Unit): Unit = {

    implicit val spark: SparkSession = {
      if (ApplicationEnvironment == "cluster")
        SparkSession
          .builder()
          .appName(appName)
          .enableHiveSupport()
          .getOrCreate()
      else
        SparkSession
          .builder()
          .master("local[8]")
          .appName(appName)
          .getOrCreate()
    }

    action

    spark.stop()
  }

}
