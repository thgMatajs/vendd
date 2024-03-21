package com.gentalha.vendd.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gentalha.vendd.data.SaleRepository
import com.gentalha.vendd.model.Product
import com.gentalha.vendd.ui.state.SaleUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SaleViewModel @Inject constructor(
    private val repository: SaleRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<SaleUiState>(
        SaleUiState.Loading
    )

    val uiState = _uiState.asStateFlow()

    init {
        getProducts()
    }

    fun addProduct(product: Product) {
        viewModelScope.launch {
            repository.addProduct(product)
                .flowOn(Dispatchers.IO)
                .onStart { _uiState.update { SaleUiState.Loading } }
                .catch { error ->
                    _uiState.update { SaleUiState.Failure(error) }
                }
                .collect { products ->
                    _uiState.update {
                        SaleUiState.Success(
                            products
                        )
                    }
                }
        }
    }

    fun getProducts() {
        viewModelScope.launch {
            repository.getProducts()
                .flowOn(Dispatchers.IO)
                .onStart { _uiState.update { SaleUiState.Loading } }
                .catch { error ->
                    _uiState.update { SaleUiState.Failure(error) }
                }
                .collect { products ->
                    _uiState.update {
                        if (products.isEmpty())
                            SaleUiState.Empty
                        else
                            SaleUiState.Success(
                                products
                            )
                    }
                }
        }
    }

    fun clear() {
        viewModelScope.launch {
            repository.clearProducts()
                .flowOn(Dispatchers.IO)
                .onStart { _uiState.update { SaleUiState.Loading } }
                .catch { error ->
                    _uiState.update { SaleUiState.Failure(error) }
                }
                .collect { products ->
                    _uiState.update {
                        if (products.isEmpty())
                            SaleUiState.Empty
                        else
                            SaleUiState.Success(
                                products
                            )
                    }
                }
        }
    }
}