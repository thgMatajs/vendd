package com.gentalha.vendd.ui.model

import com.gentalha.vendd.model.Product
import com.gentalha.vendd.model.Sale

data class SaleUi(
    override val id: Long? = null,
    override val clientName: String,
    override val products: List<Product>
): Sale
