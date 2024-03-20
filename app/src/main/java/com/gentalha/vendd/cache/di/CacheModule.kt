package com.gentalha.vendd.cache.di

import android.content.Context
import androidx.room.Room
import com.gentalha.vendd.cache.converter.ProductConverter
import com.gentalha.vendd.cache.database.SaleDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CacheModule {

    @Provides
    @Singleton
    fun provideSaleDatabase(@ApplicationContext context: Context): SaleDatabase {
        return Room.databaseBuilder(
            context,
            SaleDatabase::class.java,
            "vendd-db"
        )
            .addTypeConverter(ProductConverter())
            .build()
    }

    @Provides
    fun provideSaleDao(database: SaleDatabase) = database.saleDao()

}