package com.gentalha.vendd.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.gentalha.vendd.model.Product
import com.gentalha.vendd.ui.components.Header
import com.gentalha.vendd.ui.components.ProductForm
import com.gentalha.vendd.ui.components.ProductItem
import com.gentalha.vendd.ui.components.ProductsIncluded
import com.gentalha.vendd.ui.model.SaleUi
import com.gentalha.vendd.ui.state.ProductUiState
import com.gentalha.vendd.ui.theme.Black
import com.gentalha.vendd.ui.theme.DarkBlack
import com.gentalha.vendd.ui.theme.DarkGray
import com.gentalha.vendd.ui.theme.LightGray
import com.gentalha.vendd.ui.theme.TextLight
import com.gentalha.vendd.ui.viewmodel.SaleViewModel
import java.math.BigDecimal

@Composable
fun CreateSaleScreen(viewModel: SaleViewModel) {
    val saleId: BigDecimal = BigDecimal.ONE

    val uiState: ProductUiState by viewModel.uiState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.getProducts()
    }

    LazyColumn(
        Modifier
            .fillMaxSize()
            .background(DarkBlack)
    ) {
        item {
            Header(title = "Numero do pedido: $saleId")
        }
        item {
            ProductForm {
                viewModel.addProduct(it)
            }
        }

        when (uiState) {
            ProductUiState.Empty -> {}
            is ProductUiState.Failure -> TODO()
            ProductUiState.Loading -> {}
            is ProductUiState.Success -> {
                val products = (uiState as ProductUiState.Success).products
                itemsIndexed(
                    products
                ) { position, product ->
                    ProductItem(
                        product = product,
                        background = if (position % 2 == 0) {
                            DarkGray
                        } else {
                            LightGray
                        },
                        textColor = if (position % 2 == 0) {
                            TextLight
                        } else {
                            Black
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp)
                    )
                }
                item {
                    ProductsIncluded(
                        products = products,
                        cancelOnClick = { viewModel.clear() },
                        saveOnClick = {
                            viewModel.createSale(
                                SaleUi(
                                    clientName = "", products = emptyList<Product>()
                                )
                            )
                        }
                    )
                }
            }
        }
    }
}