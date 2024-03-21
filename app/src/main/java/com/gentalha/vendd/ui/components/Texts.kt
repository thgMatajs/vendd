package com.gentalha.vendd.ui.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import com.gentalha.vendd.ui.theme.TextLight

@Composable
fun SmallText(text: String, color: Color = TextLight) {
    Text(text = text, color = color, fontSize = 12.sp)
}