package com.gentalha.vendd.ui.components

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier

@Composable
fun InputTextField(
    modifier: Modifier = Modifier,
    label: String,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    valueOnChange: (String) -> Unit
) {
    var text by rememberSaveable {
        mutableStateOf("")
    }

    TextField(
        value = text,
        onValueChange = {
            text = it
            valueOnChange(it)
        },
        label = { Text(label) },
        singleLine = true,
        keyboardOptions = keyboardOptions,
        modifier = modifier
    )
}