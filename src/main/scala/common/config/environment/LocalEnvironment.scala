package common.config.environment
import org.apache.spark.sql.SparkSession

object LocalEnvironment extends Environment {

  override def getSparkSession(appName: String): SparkSession = {
    SparkSession
      .builder()
      .master("local[8]")
      .appName(appName)
      .getOrCreate()
  }

}
