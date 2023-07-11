package api

import config.BaseHelpers._
import io.gatling.core.Predef._
import io.gatling.core.structure._
import io.gatling.http.Predef._


object Cart {

  val addToCartFilePath = "/wp-admin/admin-ajax.php"
  val cartFilePath = "/cart"

  val regexOfTotalPrice = """name="total_net" value="(.+?)""""
  val regexOfCartContent = """name="cart_content" value='(.+?)'"""

  def addToCart(): ChainBuilder = {
    exec(
      http("add #{product_name} to cart with id #{current_product_id} and quantity #{current_product_quantity}")
        .post(baseUrl + addToCartFilePath)
        .formParam("action", "ic_add_to_cart")
        .formParam("add_cart_data", "current_product=#{current_product_id}&cart_content=&current_quantity=#{current_product_quantity}")
        .formParam("cart_widget", "0")
        .formParam("cart_container", "0")
        .check(substring("Added!").exists)
        .check(status.is(200))
    )
  }

  def openCart(): ChainBuilder = {
    exec(
      http(cartFilePath)
        .get(baseUrl + cartFilePath)
        .check(regex(regexOfTotalPrice).find.saveAs("total_price"))
        .check(regex(regexOfCartContent).find.saveAs("cart_contents"))
        .check(status.is(200))
    )
  }
}
