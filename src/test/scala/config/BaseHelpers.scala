package config

import io.gatling.core.Predef._
import io.gatling.core.structure._
import io.gatling.http.protocol._
import io.gatling.http.Predef._


object BaseHelpers {

  private val scheme = "http"
  private val host = "localhost"
  val baseUrl: String = scheme + "://" + host

  def thinkTimer(Min: Int = 2, Max: Int = 5): ChainBuilder = {
    pause(Min, Max)
  }

  val httpProtocol: HttpProtocolBuilder = http
    .acceptHeader("text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,*/*;q=0.8")
    .acceptEncodingHeader("gzip, deflate, br")
    .acceptLanguageHeader("en-US,en;q=0.5")
    .upgradeInsecureRequestsHeader("1")
    .userAgentHeader("Mozilla/5.0 (Macintosh; Intel Mac OS X 10.15; rv:109.0) Gecko/20100101 Firefox/114.0")
}
