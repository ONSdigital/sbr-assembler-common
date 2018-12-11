
import com.typesafe.config.ConfigException
import common.config.ONSConfiguration
import org.scalatest._

class ONSConfigurationSpec extends FlatSpec {

  System.setProperty("simple-lib.whatever", "This value comes from a system property")

  behavior of "A Configuration system"

  it should "return configuration items when requested" in {

    assert (ONSConfiguration("Hive.DBName") == "registers")
    assert (ONSConfiguration("Hive.TableName") == "reg")
    assert (ONSConfiguration("simple-lib.whatever") == "This value comes from a system property")
    assert (ONSConfiguration("simple-lib.whateverxxx") == ONSConfiguration.NOT_FOUND)
  }

}
