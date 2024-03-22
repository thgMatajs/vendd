package com.gentalha.vendd.cache.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.gentalha.vendd.cache.entity.SaleEntity

@Dao
interface SaleDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun createSale(sale: SaleEntity)

    @Query("SELECT * FROM sales")
    suspend fun getSales(): List<SaleEntity>

    @Query("SELECT SUM(totalSalesPrice) FROM sales")
    suspend fun getTotalSales(): Float

}