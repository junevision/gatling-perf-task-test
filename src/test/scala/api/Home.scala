package api

import config.BaseHelpers._
import io.gatling.core.Predef._
import io.gatling.core.structure._
import io.gatling.http.Predef._


object Home {

  def openHomePage(): ChainBuilder = {
    exec(
      http("home")
        .get(baseUrl)
        .check(headerRegex("Set-Cookie", "wp_ic_session_(.+)").saveAs("cookies"))
    )
  }
}
