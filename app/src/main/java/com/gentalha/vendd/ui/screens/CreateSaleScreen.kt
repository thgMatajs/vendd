package com.gentalha.vendd.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.gentalha.vendd.ui.components.Header
import com.gentalha.vendd.ui.components.ProductForm
import com.gentalha.vendd.ui.components.ProductItem
import com.gentalha.vendd.ui.components.ProductsIncluded
import com.gentalha.vendd.ui.model.ProductUi
import com.gentalha.vendd.ui.theme.Black
import com.gentalha.vendd.ui.theme.DarkBlack
import com.gentalha.vendd.ui.theme.DarkGray
import com.gentalha.vendd.ui.theme.LightGray
import com.gentalha.vendd.ui.theme.TextLight
import java.math.BigDecimal

@Composable
fun CreateSaleScreen() {
    val saleId: BigDecimal = BigDecimal.ONE

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
                println("THG_$it")
            }
        }

        itemsIndexed(
            listOf(
                ProductUi("abc", 1, 1.0.toBigDecimal(), 1.0.toBigDecimal()),
                ProductUi("abc", 1, 1.0.toBigDecimal(), 1.0.toBigDecimal()),
                ProductUi("abc", 1, 1.0.toBigDecimal(), 1.0.toBigDecimal()),
                ProductUi("abc", 1, 1.0.toBigDecimal(), 1.0.toBigDecimal()),
                ProductUi("abc", 1, 1.0.toBigDecimal(), 1.0.toBigDecimal()),
                ProductUi("abc", 1, 1.0.toBigDecimal(), 1.0.toBigDecimal()),
            )
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
                products = listOf(
                    ProductUi("abc", 1, 1.0.toBigDecimal(), 1.0.toBigDecimal()),
                    ProductUi("abc", 1, 1.0.toBigDecimal(), 1.0.toBigDecimal()),
                    ProductUi("abc", 1, 1.0.toBigDecimal(), 1.0.toBigDecimal()),
                    ProductUi("abc", 1, 1.0.toBigDecimal(), 1.0.toBigDecimal()),
                    ProductUi("abc", 1, 1.0.toBigDecimal(), 1.0.toBigDecimal()),
                    ProductUi("abc", 1, 1.0.toBigDecimal(), 1.0.toBigDecimal()),
                )
            )
        }
    }
}