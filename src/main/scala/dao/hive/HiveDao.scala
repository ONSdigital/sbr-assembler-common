package dao.hive

import config.Configuration
import org.apache.spark.sql._

object HiveDao {
  def getRegions()(implicit spark: SparkSession): DataFrame =
    spark.sql(s"select postcode, gor as region from ${Configuration("hive.DBName")}.${Configuration("hive.TableName")}")
}
