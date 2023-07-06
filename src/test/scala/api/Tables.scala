package api

import config.BaseHelpers._
import io.gatling.core.Predef._
import io.gatling.core.structure._
import io.gatling.http.Predef._


object Tables {

  val tableFilePath = "/tables"

  def navigateToTables(): ChainBuilder = {
    exec(
      http(tableFilePath)
        .get(baseUrl + tableFilePath)
    )
  }
}
