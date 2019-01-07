package closures.mocks

import common.closures.BaseClosure
import common.global.AppParams
import org.apache.spark.sql.Row
import common.spark.extensions._

trait MockUnitsIdGenerator {this:BaseClosure =>

  val ernMapping: Map[String, String]
  val lurnMapping: Map[String, String]
  val rurnMapping: Map[String, String]
  val prnMapping: Map[String, String]


  override def generateErn(row: Row, appParams: AppParams) = ernMapping(row.getString("name").get)

  override def generateLurn(row: Row, appParams: AppParams) = {
    val key = row.getString("name").get
    lurnMapping(key)
  }
  override def generateRurn(row: Row, appParams: AppParams) = {
    val key = row.getString("name").get
    rurnMapping(key)
  }

  override def generatePrn(row: Row, appParams: AppParams) = {
    val key = row.getString("name").get
    prnMapping(key)
  }

  override def generateLurnFromEnt(row: Row, appParams: AppParams) = lurnMapping(generateLurn(row, appParams))
}
