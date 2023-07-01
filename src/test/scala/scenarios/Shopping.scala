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
        group("open the application"){
          exec(api.Home.openHomePage())
            .exec(thinkTimer())
        }
          .group("navigate to the 'tables' tab"){
            exec(api.Tables.navigateToTables())
              .exec(thinkTimer())
          }
          .feed(productsFeeder)
          .group("open a table product cart"){
            exec(api.Products.navigateToTableProduct())
              .exec(thinkTimer())
          }
          .group("add table to cart"){
            exec(api.Cart.addTableToCart())
              .exec(thinkTimer())
          }
          .group("navigate to the 'chairs' tab") {
            exec(api.Chairs.navigateToChairs())
              .exec(thinkTimer())
          }
          .group("open a random chair") {
            exec(api.Products.navigateToChairProduct())
              .exec(thinkTimer())
          }
          .group("add the chair to cart") {
            exec(api.Cart.addChairToCart())
              .exec(thinkTimer())
          }
          .group("open cart") {
            exec(api.Cart.openCart())
              .exec(thinkTimer())
          }
          .group("place an order") {
            exec(api.Checkout.goToCheckout())
              .exec(thinkTimer())
          }
          .feed(usersFeeder)
          .group("fill in all required fields and place order") {
            exec(api.Checkout.fillInAllRequiredFieldsAndCheckOut())
              .exec(thinkTimer(10, 15))
          }
      )
  }
}
