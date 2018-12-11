
import com.typesafe.config.ConfigException
import common.config.Configuration
import org.scalatest._

class ConfigurationSpec extends FlatSpec {

  System.setProperty("simple-lib.whatever", "This value comes from a system property")

  behavior of "A Configuration system"

  it should "return configuration items when requested" in {

    assert (Configuration("Hive.DBName") == "registers")
    assert (Configuration("Hive.TableName") == "reg")
    assert (Configuration("simple-lib.whatever") == "This value comes from a system property")
    assert (Configuration("simple-lib.whateverxxx") == Configuration.NOT_FOUND)
  }

}
