package com.gentalha.vendd.cache.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.gentalha.vendd.model.Sale

@Entity(tableName = "sales")
data class SaleEntity(
    @PrimaryKey(autoGenerate = true) override val id: Long? = null,
    override val clientName: String,
    override val products: List<ProductEntity>,
    override val totalSalesPrice: Float
) : Sale


fun Sale.toEntity() = SaleEntity(
    clientName = this.clientName,
    products = this.products.map { it.toEntity() },
    totalSalesPrice = this.totalSalesPrice
)
