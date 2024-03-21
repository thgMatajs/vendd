package com.gentalha.vendd.ui.state

import com.gentalha.vendd.model.Product

sealed class SaleUiState {

    data object Loading : SaleUiState()

    data object Empty : SaleUiState()

    data class Success(
        val products: List<Product> = emptyList()
    ) : SaleUiState()

    data class Failure(
        val error: Throwable
    ) : SaleUiState()
}