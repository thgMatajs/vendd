package com.gentalha.vendd.ui.state

import com.gentalha.vendd.model.Product

sealed class ProductUiState {

    data object Loading : ProductUiState()

    data object Empty : ProductUiState()

    data class Success(
        val products: List<Product> = emptyList()
    ) : ProductUiState()

    data class Failure(
        val error: Throwable
    ) : ProductUiState()
}