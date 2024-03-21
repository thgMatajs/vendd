package com.gentalha.vendd.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.gentalha.vendd.ui.theme.Black
import com.gentalha.vendd.ui.theme.Lemon
import com.gentalha.vendd.ui.theme.TextLight
import java.math.BigDecimal

@Composable
fun ProductForm() {
    var itemTotalPrice by rememberSaveable {
        mutableStateOf(BigDecimal.ZERO)
    }

    var qtt by rememberSaveable {
        mutableStateOf(BigDecimal.ZERO)
    }

    var price by rememberSaveable {
        mutableStateOf(BigDecimal.ZERO)
    }

    Column(
        Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .background(Black, RoundedCornerShape(30.dp))
    ) {
        InputTextField(
            label = "Nome do cliente", modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {}
        InputTextField(
            label = "Nome do produto", modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {}

        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            InputTextField(
                label = "Valor unitario",
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 16.dp),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal)
            ) {
                price = it.toBigDecimal()
                itemTotalPrice = (qtt * price)
            }

            QuantityField {
                qtt = it.toBigDecimal()
                itemTotalPrice = (qtt * price)
            }
        }

        Row(
            modifier = Modifier
                .padding(16.dp)
        ) {
            Column(
                horizontalAlignment = Alignment.Start,
            ) {
                Text(text = "Valor total do item", color = TextLight, fontSize = 12.sp)
                Text(text = "R$ $itemTotalPrice", color = TextLight, fontSize = 12.sp)
            }

            Spacer(modifier = Modifier.size(16.dp))

            Button(
                onClick = { /*TODO*/ }, colors = ButtonDefaults.buttonColors(
                    containerColor = Lemon
                ), modifier = Modifier.weight(1f)
            ) {
                Text(text = "Incluir", color = Black)
            }
        }
    }
}