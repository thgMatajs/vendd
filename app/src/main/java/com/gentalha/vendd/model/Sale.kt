package com.gentalha.vendd.model

interface Sale {
    val id: Long
    val clientName: String
    val products: List<Product>
}