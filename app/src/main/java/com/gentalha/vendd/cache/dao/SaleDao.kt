package com.gentalha.vendd.cache.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.gentalha.vendd.cache.entity.SaleEntity

@Dao
interface SaleDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun add(sales: List<SaleEntity>)
}