package common.config

import com.typesafe.config._

/**
  * A Simple facade over the typesafe configuration system. It currently uses a
  * configuration file and only supports string values.
  *
  * Extend as necessary
  *
  * @param config the configuration object. If none is provided, one will be created
  */

object ONSConfiguration extends Serializable {

  private val config =  ConfigFactory.load()

  lazy val NOT_FOUND = "NOT_FOUND"
  lazy val CONFIG_ERROR = "CONFIG_ERROR"

  private def get(path: String): String = {
    try {
      config.getString(path)
    } catch {
      case ioe: ConfigException.Missing => NOT_FOUND
      case e: Exception => CONFIG_ERROR
    }
  }

  def apply(path: String): String = get(path)

  def exists(path: String): Boolean =  get(path) != NOT_FOUND

  // Some common configuration items
  lazy val ApplicationEnvironment: String = get("app.environment")
  lazy val HiveDBName: String = get("hive.DBName")
  lazy val HiveTablename: String = get("hive.TableName")
  lazy val ParquetPath: String = get("parquet.path")

}

