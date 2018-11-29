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

  def get(path: String): String = config.getString(path)
}

object Configuration {

  val instance = new Configuration

  def apply: Configuration = instance

  def apply(path: String): String = instance.get(path)
}
