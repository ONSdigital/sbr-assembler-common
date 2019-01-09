
import org.scalatest._
import util.configuration.Config

class ONSConfigurationSpec extends FlatSpec {

  System.setProperty("simple-lib.whatever", "This value comes from a system property")

  behavior of "A Configuration system"

  it should "return configuration items when requested" in {

    assert (Config("Hive.DBName") == "registers")
    assert (Config("Hive.TableName") == "reg")
    assert (Config("simple-lib.whatever") == "This value comes from a system property")
    assert (Config("simple-lib.whateverxxx") == "simple-lib.whateverxxx: " + Config.NOT_FOUND)
  }

}
