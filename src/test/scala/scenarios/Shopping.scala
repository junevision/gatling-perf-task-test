package scenarios

import config.BaseHelpers._
import io.gatling.core.Predef._
import io.gatling.core.structure._
import io.gatling.http.Predef._


object Shopping {

  def scnShopping: ScenarioBuilder = {
    scenario("shopping process bases on task requirements")
      .exec(flushHttpCache)
      .exec(flushCookieJar)
      .exitBlockOnFail(
        group("open the application") {
          exec(api.Home.openHomePage())
            .exec(thinkTimer())
        }
          .group("navigate to the 'tables' tab") {
            exec(api.Category.navigateToTables())
              .exec(thinkTimer())
          }
          .group("open a table product cart") {
            exec(api.Products.navigateToProducts())
              .exec(thinkTimer())
          }
          .group("add table to cart") {
            exec(api.Cart.addToCart())
              .exec(thinkTimer())
          }
          .randomSwitch(
            50.0 ->
              group("navigate to the 'chairs' tab") {
                exec(api.Category.navigateToChairs())
                  .exec(thinkTimer())
              }
                .group("open a random chair") {
                  exec(api.Products.navigateToProducts())
                    .exec(thinkTimer())
                }
                .group("add the chair to cart") {
                  exec(api.Cart.addToCart())
                    .exec(thinkTimer())
                }
          )
          .randomSwitch(
            30.0 ->
              group("open cart") {
                exec(api.Cart.openCart())
                  .exec(thinkTimer())
              }
                .group("place an order") {
                  exec(api.Checkout.goToCheckout())
                    .exec(thinkTimer())
                }
                .group("fill in all required fields and place order") {
                  exec(api.Checkout.fillInAllRequiredFieldsAndCheckOut())
                    .exec(thinkTimer())
                }
          )
      )
  }
}
