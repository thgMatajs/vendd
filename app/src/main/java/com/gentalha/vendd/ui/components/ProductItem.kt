package com.gentalha.vendd.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.gentalha.vendd.model.Product
import com.gentalha.vendd.ui.theme.Black
import com.gentalha.vendd.ui.theme.TextLight

@Composable
fun ProductItem(modifier: Modifier = Modifier, product: Product, background: Color) {
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
            ItemText(text = product.name)
            ItemText(text = product.quantity.toString())
            ItemText(text = product.price.toString())
            ItemText(text = product.totalPrice.toString())
        }
        SmallSpacer()
        Spacer(modifier = Modifier
            .fillMaxWidth()
            .height(2.dp)
            .background(Black))
    }

}

@Composable
fun ItemText(text: String) {
    Text(text = text, color = TextLight, fontSize = 12.sp)
}