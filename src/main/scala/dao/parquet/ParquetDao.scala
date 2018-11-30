package dao.parquet

import config.Configuration._
import org.apache.spark.sql.SparkSession

object ParquetDao extends Serializable {

  def jsonToParquet(jsonFilePath: String)(implicit spark: SparkSession): Unit =
    spark.read.json(jsonFilePath).write.parquet(ParquetPath)

}
