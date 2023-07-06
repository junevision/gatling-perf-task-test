package api

import config.BaseHelpers._
import io.gatling.core.Predef._
import io.gatling.core.structure._
import io.gatling.http.Predef._


object Cart {

  val addToCartFilePath = "/wp-admin/admin-ajax.php"
  val cartFilePath = "/cart"

  def addTableToCart(): ChainBuilder = {
    addToCart("${table_product_id}", tableProductQuantity)
  }

  def addChairToCart(): ChainBuilder = {
    addToCart("${chair_product_id}", chairProductQuantity)
  }

  private def addToCart(productId: String, productQuantity: String): ChainBuilder = {
    exec(
      http(s"add product to cart with id $productId and quantity $productQuantity")
        .post(baseUrl + addToCartFilePath)
        .formParam("action", "ic_add_to_cart")
        .formParam("add_cart_data", s"current_product=$productId&cart_content=&current_quantity=$productQuantity")
        .formParam("cart_widget", "0")
        .formParam("cart_container", "0")
        .check(substring("Added!").exists)
    )
  }

  def openCart(): ChainBuilder = {
    exec(
      http(cartFilePath)
        .get(baseUrl + cartFilePath)
        .check(regex("""name="total_net" value="(.+?)"""").find.saveAs("total_price"))
    )
  }
}
