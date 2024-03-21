package com.gentalha.vendd.ui.model

import com.gentalha.vendd.model.Product
import java.math.BigDecimal

data class ProductUi(
    override val name: String,
    override val quantity: Int,
    override val price: BigDecimal,
    override val totalPrice: BigDecimal
): Product
