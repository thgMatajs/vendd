package com.gentalha.vendd.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.gentalha.vendd.ui.theme.Black
import com.gentalha.vendd.ui.theme.DarkGray
import com.gentalha.vendd.ui.theme.Lemon
import com.gentalha.vendd.ui.theme.TextLight

@Composable
fun PrimaryButton(label: String, modifier: Modifier, onClick: () -> Unit) {
    Button(
        onClick = {
            onClick()
        }, colors = ButtonDefaults.buttonColors(
            containerColor = Lemon
        ), modifier = modifier
    ) {
        Text(text = label, color = Black)
    }
}

@Composable
fun SecondaryButton(label: String, modifier: Modifier, onClick: () -> Unit) {
    Button(
        onClick = {
            onClick()
        },
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Transparent
        ),
        border = BorderStroke(1.dp, DarkGray),
        modifier = modifier
    ) {
        Text(text = label, color = TextLight)
    }
}