package simulations

import config.BaseHelpers._
import io.gatling.core.Predef._
import scenarios.Shopping._


class PerfTestSimulation extends Simulation {

  // mvn gatling:test
  // mvn clean gatling:test
  // mvn gatling:test -DUsersCount=1
  // mvn gatling:test -DUsersCount=10

  setUp(
    scnShopping.inject(atOnceUsers(System.getProperty("UsersCount", "100").toInt))
  ).protocols(httpProtocol)
}
