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
        .formParam("cart_content", "{\"#{product_id(0)}\":" + "#{product_quantity(0)}" + "}")
        .formParam("cart_content", "{\"#{product_id(1)}\":" + "#{product_quantity(1)}" + "}")
        .formParam("p_quantity[]", "#{product_quantity(0)}")
        .formParam("p_quantity[]", "#{product_quantity(1)}")
        .formParam("shipping", "order")
        .formParam("p_id[]", "#{product_id(0)}")
        .formParam("p_id[]", "#{product_id(1)}")
        .formParam("trans_id", "#{currentTimeMillis()}")
        .formParam("total_net", "${total_price}")
        .check(status.is(200))
    )
  }

  def fillInAllRequiredFieldsAndCheckOut(): ChainBuilder = {
    exec(
      http("second checkout with filling more fields")
        .post(baseUrl + checkoutFilePath)
        .formParam("cart_city", "Boston")
        .formParam("cart_content", "{\"#{product_id(0)}\":" + "#{product_quantity(0)}" + "}")
        .formParam("cart_content", "{\"#{product_id(1)}\":" + "#{product_quantity(1)}" + "}")
        .formParam("ic_formbuilder_redirect", baseUrl + "/thank-you")
        .formParam("cart_submit", "Place Order")
        .formParam("trans_id", "#{currentTimeMillis()}")
        .formParam("cart_type", "order")
        .formParam("cart_country", "US")
        .formParam("shipping", "order")
        .formParam("product_price_#{product_id(0)}", "#{product_price(0)}")
        .formParam("product_price_#{product_id(1)}", "#{product_price(1)}")
        .formParam("cart_name", "Peter_#{randomAlphanumeric(10)}")
        .formParam("cart_state", "US_NY")
        .formParam("cart_phone", "#{randomLong(1000000000,9999999999)}")
        .formParam("cart_address", "#{randomAlphanumeric(10)}_Street")
        .formParam("cart_postal", "#{randomLong(100000,999999)}")
        .formParam("cart_inside_header_2", "<b>DELIVERY ADDRESS</b> (FILL ONLY IF DIFFERENT FROM THE BILLING ADDRESS)")
        .formParam("cart_email", "#{randomAlphanumeric(10)}@gmail.com")
        .formParam("cart_inside_header_1", "<b>BILLING ADDRESS</b>")
        .formParam("total_net", "${total_price}")
        .check(status.is(200))
    )
  }
}
