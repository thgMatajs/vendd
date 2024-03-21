package com.gentalha.vendd.data

import com.gentalha.vendd.cache.dao.SaleDao
import com.gentalha.vendd.model.Product
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SaleRepository @Inject constructor(
    private val saleDao: SaleDao
) {

    private var temporaryProducts = mutableListOf<Product>()

    fun addProduct(product: Product) = flow {
        temporaryProducts.add(product)
        emit(temporaryProducts)
        println("THG_repository products = $temporaryProducts")
    }

    fun getProducts() = flow {
        emit(temporaryProducts)
    }

    fun clearProducts() = flow {
        temporaryProducts = emptyList<Product>().toMutableList()
        emit(temporaryProducts)
    }

}