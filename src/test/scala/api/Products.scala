package api

import config.BaseHelpers._
import io.gatling.core.Predef._
import io.gatling.core.structure._
import io.gatling.http.Predef._


object Products {

  val productsFilePath: String = "/products" + "/${product_name}"

  val regexOfCurrentProductId = """name="current_product" value="(.+?)">"""
  val regexOfCurrentProductQuantity = """<input name="current_quantity".+value="(.+?)">"""

  def navigateToProducts(): ChainBuilder = {
    exec(
      http(productsFilePath)
        .get(baseUrl + productsFilePath)
        .check(status.is(200))
        .check(regex(regexOfCurrentProductId).find.saveAs("current_product_id"))
        .check(regex(regexOfCurrentProductQuantity).find.saveAs("current_product_quantity"))
    )
  }
}
