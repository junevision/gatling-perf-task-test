package api

import config.BaseHelpers._
import io.gatling.core.Predef._
import io.gatling.core.structure._
import io.gatling.http.Predef._


object Chairs {

  val chairFilePath = "/chairs"

  def navigateToChairs(): ChainBuilder = {
    exec(
      http(chairFilePath)
        .get(baseUrl + chairFilePath)
    )
  }
}
