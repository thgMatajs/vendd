package com.gentalha.vendd.cache.entity

import com.gentalha.vendd.model.Product
import java.math.BigDecimal

data class ProductEntity(
    override val name: String,
    override val quantity: Int,
    override val price: BigDecimal,
    override val totalPrice: BigDecimal
): Product

fun Product.toEntity() = ProductEntity(
    this.name,
    this.quantity,
    this.price,
    this.totalPrice
)
