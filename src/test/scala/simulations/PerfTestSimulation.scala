package simulations

import config.BaseHelpers._
import io.gatling.core.Predef._
import scenarios.Shopping._


class PerfTestSimulation extends Simulation {

  // mvn gatling:test
  // mvn clean gatling:test
  // mvn gatling:test -DTableProductQuantity=2 -DChairProductQuantity=2

  setUp(
    scnShopping.inject(atOnceUsers(1))
  ).protocols(httpProtocol)
}
