package com.gentalha.vendd.ui.state

import com.gentalha.vendd.model.Sale

sealed class SaleUiState {
    data object Loading : SaleUiState()

    data object Empty : SaleUiState()

    data class Success(
        val sale: Sale
    ) : SaleUiState()

    data class Failure(
        val error: Throwable
    ) : SaleUiState()
}