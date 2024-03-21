package com.gentalha.vendd.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.gentalha.vendd.model.Product
import com.gentalha.vendd.ui.theme.Black
import java.math.BigDecimal

@Composable
fun ProductsIncluded(products: List<Product>) {

    var totalSale by rememberSaveable {
        mutableStateOf(BigDecimal.ZERO)
    }

    Column(
        Modifier
            .fillMaxSize()
            .padding(top = 16.dp, start = 8.dp, end = 8.dp)
            .background(
                Black,
                RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp)
            )
    ) {
        MediumSpacer()
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) {
            items(products) { product ->
                ProductItem(
                    product = product,
                    background = Color.DarkGray,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                )
            }
        }

        LaunchedEffect(key1 = Unit) {
            products.forEach { product -> totalSale += product.totalPrice }
        }

        MediumSpacer()

        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {
            ItemText(text = "Qtd. total de itens: ${products.size}")
            MediumSpacer()
            ItemText(text = "Valor total do pedido: R$ $totalSale")
        }

        MediumSpacer()

        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            SecondaryButton(
                label = "Cancelar",
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 16.dp, end = 8.dp)
            ) {
            }
            PrimaryButton(
                label = "Salvar",
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 8.dp, end = 16.dp)
            ) {
            }
        }

        LargeSpacer()

    }
}