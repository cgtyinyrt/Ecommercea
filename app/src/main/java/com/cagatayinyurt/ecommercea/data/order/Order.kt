package com.cagatayinyurt.ecommercea.data.order

import com.cagatayinyurt.ecommercea.data.Address
import com.cagatayinyurt.ecommercea.data.CartProduct

data class Order(
    val orderStatus: String,
    val totalPrice: Float,
    val products: List<CartProduct>,
    val address: Address
)
