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
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.gentalha.vendd.ui.components.Header
import com.gentalha.vendd.ui.components.ProductForm
import com.gentalha.vendd.ui.components.ProductItem
import com.gentalha.vendd.ui.components.ProductsIncluded
import com.gentalha.vendd.ui.model.SaleUi
import com.gentalha.vendd.ui.state.ProductUiState
import com.gentalha.vendd.ui.state.SaleUiState
import com.gentalha.vendd.ui.theme.Black
import com.gentalha.vendd.ui.theme.DarkBlack
import com.gentalha.vendd.ui.theme.DarkGray
import com.gentalha.vendd.ui.theme.LightGray
import com.gentalha.vendd.ui.theme.TextLight
import com.gentalha.vendd.ui.viewmodel.SaleViewModel
import java.math.BigInteger

@Composable
fun CreateSaleScreen(viewModel: SaleViewModel) {
    var saleId by rememberSaveable {
        mutableIntStateOf(BigInteger.ONE.toInt())
    }

    val uiState: ProductUiState by viewModel.uiState.collectAsState()
    val saleUiState: SaleUiState by viewModel.saleUiState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.getProducts()
    }

    LazyColumn(
        Modifier
            .fillMaxSize()
            .background(DarkBlack)
    ) {
        item {
            when (saleUiState) {
                SaleUiState.Empty -> {}
                is SaleUiState.Failure -> {}
                SaleUiState.Loading -> {}
                is SaleUiState.Success -> {
                    saleId = (saleUiState as SaleUiState.Success).sale.id?.toInt()
                        ?.plus(BigInteger.ONE.toInt())
                        ?: BigInteger.ZERO.toInt()
                }
            }
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
                                    clientName = "", products = emptyList()
                                )
                            )
                        }
                    )
                }
            }
        }
    }
}