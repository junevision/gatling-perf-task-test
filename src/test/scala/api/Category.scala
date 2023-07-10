package api

import config.BaseHelpers._
import io.gatling.core.Predef._
import io.gatling.core.structure._
import io.gatling.http.Predef._


object Category {

  val tableFilePath = "/tables"
  val chairFilePath = "/chairs"

  val regexOfProductName = """<a href="http:\/\/localhost\/products\/(.+?)">"""

  def navigateToTables(): ChainBuilder = {
    navigateToCategory(tableFilePath)
  }

  def navigateToChairs(): ChainBuilder = {
    navigateToCategory(chairFilePath)
  }

  private def navigateToCategory(categoryPath: String): ChainBuilder = {
    exec(
      http(categoryPath)
        .get(baseUrl + categoryPath)
        .check(status.is(200))
        .check(regex(regexOfProductName).findRandom.saveAs("product_name"))
    )
  }
}
