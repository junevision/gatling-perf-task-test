package simulations

import config.BaseHelpers._
import io.gatling.core.Predef._
import scenarios.Shopping._

import scala.concurrent.duration.DurationInt


class PerfTestSimulation extends Simulation {

  // mvn gatling:test
  // mvn clean gatling:test
  // mvn gatling:test -DUsersCount=1
  // mvn gatling:test -DUsersCount=10
  // mvn gatling:test -DUsersCount=100

  setUp(
    scnShopping.inject(rampUsers(System.getProperty("UsersCount", "5").toInt).during(10.seconds))
//    scnShopping.inject(atOnceUsers(System.getProperty("UsersCount", "10").toInt))
  ).maxDuration(10.minutes).protocols(httpProtocol)
}
