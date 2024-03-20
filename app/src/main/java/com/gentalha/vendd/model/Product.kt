package com.gentalha.vendd.model

import java.math.BigDecimal

interface Product {
    val name: String
    val quantity: Int
    val price: BigDecimal
    val totalPrice: BigDecimal
}