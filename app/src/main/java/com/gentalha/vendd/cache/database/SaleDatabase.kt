package com.gentalha.vendd.cache.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.gentalha.vendd.cache.converter.ProductConverter
import com.gentalha.vendd.cache.dao.SaleDao
import com.gentalha.vendd.cache.entity.SaleEntity

@Database(entities = [SaleEntity::class], version = 1)
@TypeConverters(ProductConverter::class)
abstract class SaleDatabase : RoomDatabase() {

    abstract fun saleDao(): SaleDao

}