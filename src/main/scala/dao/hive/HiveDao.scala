package dao.hive

import config.Configuration._
import org.apache.spark.sql._

object HiveDao {

  def getRegions()(implicit spark: SparkSession): DataFrame =
    spark.sql(s"select postcode, gor as region from $HiveDBName.$HiveTablename")

}
