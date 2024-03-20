package com.gentalha.vendd.cache.converter

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.gentalha.vendd.cache.entity.ProductEntity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

@ProvidedTypeConverter
class ProductConverter {

    @TypeConverter
    fun fromString(value: String): List<ProductEntity> {
        val listType = object : TypeToken<List<ProductEntity>>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun fromList(list: List<ProductEntity>): String {
        val gson = Gson()
        return gson.toJson(list)
    }
}