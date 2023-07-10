package api

import config.BaseHelpers._
import io.gatling.core.Predef._
import io.gatling.core.structure._
import io.gatling.http.Predef._


object Checkout {

  val checkoutFilePath = "/checkout"

  def goToCheckout(): ChainBuilder = {
    exec(
      http("first checkout")
        .post(baseUrl + checkoutFilePath)
        .formParam("cart_content", "#{cart_contents}")
        .formParam("p_quantity[]", "#{product_quantity}")
        .formParam("shipping", "order")
        .formParam("p_id[]", "#{product_id}")
        .formParam("trans_id", "#{currentTimeMillis()}")
        .formParam("total_net", "#{total_price}")
        .check(status.is(200))
    )
  }

  def fillInAllRequiredFieldsAndCheckOut(): ChainBuilder = {
    exec(
      http("second checkout with filling more fields")
        .post(baseUrl + checkoutFilePath)
        .formParam("cart_city", "Boston")
        .formParam("cart_content", "#{cart_contents}")
        .formParam("ic_formbuilder_redirect", baseUrl + "/thank-you")
        .formParam("cart_submit", "Place Order")
        .formParam("trans_id", "#{currentTimeMillis()}")
        .formParam("cart_type", "order")
        .formParam("cart_country", "US")
        .formParam("shipping", "order")
        .formParam("cart_name", "Peter " + "#{randomAlphanumeric(10)}")
        .formParam("cart_state", "US_NY")
        .formParam("cart_phone", "#{randomLong(2147483648,2147483658)}")
        .formParam("cart_address", "#{randomAlphanumeric(10)}" + " Street")
        .formParam("cart_postal", "#{randomLong(2147483648,2147483658)}")
        .formParam("cart_email", "#{randomAlphanumeric(10)}" + "@gmail.com")
        .formParam("total_net", "#{total_price}")
        .check(status.is(200))
    )
  }
}
