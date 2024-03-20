package com.gentalha.vendd.cache.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.gentalha.vendd.model.Sale

@Entity(tableName = "sales")
data class SaleEntity(
    @PrimaryKey(autoGenerate = true) override val id: Long,
    override val clientName: String,
    override val products: List<ProductEntity>,
) : Sale
