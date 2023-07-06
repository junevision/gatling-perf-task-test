package api

import config.BaseHelpers._
import io.gatling.core.Predef._
import io.gatling.core.structure._
import io.gatling.http.Predef._


object Checkout {

  val checkoutFilePath = "/checkout"

  def goToCheckout(): ChainBuilder = {
    exec(
      http("go to checkout")
        .post(baseUrl + checkoutFilePath)
        .formParam("cart_content", "{\"${table_product_id}__\":" + s"${tableProductQuantity}" + ",\"${chair_product_id}__\":" + s"${chairProductQuantity}" + "}")
        .formParam("p_quantity[]", s"${tableProductQuantity},${chairProductQuantity}")
        .formParam("shipping", "order")
        .formParam("p_id[]", "${table_product_id}__,${chair_product_id}__")
        .formParam("trans_id", "16873163302612")
        .formParam("total_net", "${total_price}")
    )
  }

  def fillInAllRequiredFieldsAndCheckOut(): ChainBuilder = {
    exec(
      http("fill in required fields and check out")
        .post(baseUrl + checkoutFilePath)
        .formParam("cart_city", "${city}")
        .formParam("cart_content", "{\"${table_product_id}__\":" + s"${tableProductQuantity}" + ",\"${chair_product_id}__\":" + s"${chairProductQuantity}" + "}")
        .formParam("ic_formbuilder_redirect", baseUrl + "/thank-you")
        .formParam("cart_submit", "Place Order")
        .formParam("trans_id", "16873163302612")
        .formParam("cart_type", "order")
        .formParam("cart_country", "${country}")
        .formParam("shipping", "order")
        .formParam("product_price_${table_product_id}__", "${table_product_price}")
        .formParam("product_price_${chair_product_id}__", "${chair_product_price}")
        .formParam("cart_name", "${username}")
        .formParam("cart_state", "${state}")
        .formParam("cart_phone", "${phone_number}")
        .formParam("cart_address", "${address}")
        .formParam("cart_postal", "${post_number}")
        .formParam("cart_inside_header_2", "<b>DELIVERY ADDRESS</b> (FILL ONLY IF DIFFERENT FROM THE BILLING ADDRESS)")
        .formParam("cart_email", "${email_address}")
        .formParam("cart_inside_header_1", "<b>BILLING ADDRESS</b>")
        .formParam("total_net", "${total_price}")
    )
  }
}
