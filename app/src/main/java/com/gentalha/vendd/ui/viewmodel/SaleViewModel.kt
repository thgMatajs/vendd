package com.gentalha.vendd.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gentalha.vendd.data.SaleRepository
import com.gentalha.vendd.model.Product
import com.gentalha.vendd.model.Sale
import com.gentalha.vendd.ui.state.ProductUiState
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

    private val _uiState = MutableStateFlow<ProductUiState>(
        ProductUiState.Loading
    )

    val uiState = _uiState.asStateFlow()

    private val _saleUiState = MutableStateFlow<SaleUiState>(
        SaleUiState.Loading
    )

    val saleUiState = _saleUiState.asStateFlow()

    init {
        getProducts()
    }

    fun addProduct(product: Product) {
        viewModelScope.launch {
            repository.addProduct(product)
                .flowOn(Dispatchers.IO)
                .onStart { _uiState.update { ProductUiState.Loading } }
                .catch { error ->
                    _uiState.update { ProductUiState.Failure(error) }
                }
                .collect { products ->
                    _uiState.update {
                        ProductUiState.Success(
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
                .onStart { _uiState.update { ProductUiState.Loading } }
                .catch { error ->
                    _uiState.update { ProductUiState.Failure(error) }
                }
                .collect { products ->
                    _uiState.update {
                        if (products.isEmpty())
                            ProductUiState.Empty
                        else
                            ProductUiState.Success(
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
                .onStart { _uiState.update { ProductUiState.Loading } }
                .catch { error ->
                    _uiState.update { ProductUiState.Failure(error) }
                }
                .collect { products ->
                    _uiState.update {
                        if (products.isEmpty())
                            ProductUiState.Empty
                        else
                            ProductUiState.Success(
                                products
                            )
                    }
                }
        }
    }

    fun createSale(sale: Sale) {
        println("THG_create_viewmodel $sale")
        viewModelScope.launch {

            repository.createSale(sale)
        }
//        viewModelScope.launch {
//            repository.createSale(sale)
//                .flowOn(Dispatchers.IO)
//                .onStart { _saleUiState.update { SaleUiState.Loading } }
//                .catch { error ->
//                    _saleUiState.update { SaleUiState.Failure(error) }
//                }.onCompletion {
//                    _saleUiState.update { SaleUiState.Success(sale) }
//                }
//        }
    }
}