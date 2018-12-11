package common.config.environment
import org.apache.spark.sql.SparkSession

object ClusterEnvironment extends Environment {

  override def getSparkSession(appName: String): SparkSession = {
    SparkSession
      .builder()
      .appName(appName)
      .enableHiveSupport()
      .getOrCreate()
  }

}
