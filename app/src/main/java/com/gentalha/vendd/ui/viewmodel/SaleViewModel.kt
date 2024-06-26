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
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.math.BigDecimal
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

    private val _totalSales = MutableStateFlow<BigDecimal>(
        BigDecimal.ZERO
    )

    val totalSales = _totalSales.asStateFlow()

    init {
        getProducts()
        getSale()
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
        viewModelScope.launch {
            repository.createSale(sale)
        }
    }

    fun getSale() {
        viewModelScope.launch {
            repository.getSales()
                .flowOn(Dispatchers.IO)
                .onStart { _saleUiState.update { SaleUiState.Loading } }
                .catch { error ->
                    _saleUiState.update { SaleUiState.Failure(error) }
                }
                .collectLatest { sales ->
                    _saleUiState.update {
                        if (sales.isEmpty())
                            SaleUiState.Empty
                        else
                            SaleUiState.Success(sales)
                    }
                }
        }
    }

    fun getTotalSales() {
        viewModelScope.launch {
            repository.getTotalSales().runCatching {
                _totalSales.update { this.toBigDecimal() }
            }
        }
    }
}