package com.gentalha.vendd.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.gentalha.vendd.R
import com.gentalha.vendd.ui.theme.TextLight
import java.math.BigInteger

private const val MAX_QUERY_CHAR = 5

@Composable
fun QuantityField(modifier: Modifier = Modifier, qttOnChange: (BigInteger) -> Unit) {
    var qtt by rememberSaveable {
        mutableStateOf(BigInteger.ZERO)
    }
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
    ) {
        IconButton(onClick = {
            qtt++
            qttOnChange(qtt)
        }) {
            Icon(
                painter = painterResource(id = R.drawable.ic_plus_circle),
                contentDescription = null,
                tint = TextLight,
                modifier = Modifier.size(16.dp)
            )
        }
        BasicTextField(
            value = qtt.toString(),
            onValueChange = {
                if (it.isNotBlank() && it.length <= MAX_QUERY_CHAR) {
                    qtt = it.toBigInteger()
                    qttOnChange(qtt)
                }
            },
            singleLine = true,
            textStyle = TextStyle(
                color = TextLight,
                fontSize = 12.sp,
                textAlign = TextAlign.Center
            ),
            cursorBrush = SolidColor(TextLight),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal)
        )
        IconButton(onClick = {
            if (qtt > BigInteger.ZERO) {
                qtt--
                qttOnChange(qtt)
            }
        }
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_minus_circle),
                contentDescription = null,
                tint = TextLight,
                modifier = Modifier.size(16.dp)
            )
        }
    }
}