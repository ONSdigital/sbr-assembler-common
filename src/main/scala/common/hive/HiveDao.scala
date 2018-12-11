package common.hive

import common.config.ONSConfiguration.{HiveDBName, HiveTablename}
import org.apache.spark.sql.{DataFrame, SparkSession}

object HiveDao {

  def getRegions()(implicit spark: SparkSession): DataFrame =
    spark.sql(s"select postcode, gor as region from $HiveDBName.$HiveTablename")

}
