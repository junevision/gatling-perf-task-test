package api

import config.BaseHelpers._
import io.gatling.core.Predef._
import io.gatling.core.structure._
import io.gatling.http.Predef._


object Products {

  val productsFilePath = "/products"

  def navigateToTableProduct(): ChainBuilder = {
    navigateToProducts("/${table_product_name}", "table_product_id", "table_product_price")
  }

  def navigateToChairProduct(): ChainBuilder = {
    navigateToProducts("/${chair_product_name}", "chair_product_id", "chair_product_price")
  }

  private def navigateToProducts(specificProductPath: String, productId: String, productPrice: String): ChainBuilder = {
    exec(
      http(productsFilePath + specificProductPath)
        .get(baseUrl + productsFilePath + specificProductPath)
        .header("Set-Cookie", "${cookies}")
        .check(regex("""name="current_product" value="(.+?)">""").saveAs(productId))
        .check(regex("""small-price ic-design">\S(.*?.00)""").saveAs(productPrice))
    )
  }
}
