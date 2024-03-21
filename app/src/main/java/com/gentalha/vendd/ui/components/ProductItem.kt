package com.gentalha.vendd.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.gentalha.vendd.model.Product

@Composable
fun ProductItem(
    modifier: Modifier = Modifier,
    product: Product,
    background: Color,
    textColor: Color
) {
    Column(
        modifier = modifier
            .background(background)
            .padding(horizontal = 16.dp)
    ) {
        SmallSpacer()
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            SmallText(text = product.name, textColor)
            SmallText(text = product.quantity.toString(), textColor)
            SmallText(text = product.price.toString(), textColor)
            SmallText(text = product.totalPrice.toString(), textColor)
        }
        SmallSpacer()
    }

}