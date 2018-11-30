package config

import com.typesafe.config._

/**
  * A Simple facade over the typesafe configuration system. It currently uses a
  * configuration file and only supports string values.
  *
  * Extend as necessary
  *
  * @param config the configuration object. If none is provided, one will be created
  */
class Configuration(config: Config) {

  def this() = this(ConfigFactory.load())

  private def get(path: String): String = {
    import Configuration._
    try {
      config.getString(path)
    } catch {
      case ioe: ConfigException.Missing => NOT_FOUND
      case e: Exception => CONFIG_ERROR
    }
  }

}

object Configuration {

  val instance = new Configuration
  lazy val NOT_FOUND = "NOT_FOUND"
  lazy val CONFIG_ERROR = "CONFIG_ERROR"

  def apply: Configuration = instance

  def apply(path: String): String = instance.get(path)

  def exists(path: String): Boolean =  Configuration(path) != NOT_FOUND

  lazy val ApplicationEnvironment = Configuration("application.environment")
  lazy val HiveDBName = Configuration("hive.DBName")
  lazy val HiveTablename = Configuration("hive.TableName")
  lazy val ParquetPath = Configuration("parquet.path")

}
