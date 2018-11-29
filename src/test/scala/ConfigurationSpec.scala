
import com.typesafe.config.ConfigException
import config.Configuration
import org.scalatest._

class ConfigurationSpec extends FlatSpec {

  behavior of "A Configuration system"

  it should "return configuration items when requested" in {

    assert (Configuration("Hive.DBName") == "registers")
    assert (Configuration("Hive.TableName") == "reg")
  }

  it should "throw ConfigException.Missing if an item is not found" in {
    intercept[ConfigException.Missing] {
      Configuration("item not found")
    }
  }

}
