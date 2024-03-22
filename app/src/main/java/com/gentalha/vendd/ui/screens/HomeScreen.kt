package com.gentalha.vendd.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.gentalha.vendd.ui.components.LargeSpacer
import com.gentalha.vendd.ui.components.PrimaryButton
import com.gentalha.vendd.ui.theme.DarkBlack
import com.gentalha.vendd.ui.theme.TextLight
import com.gentalha.vendd.ui.viewmodel.SaleViewModel
import java.math.BigDecimal

@Composable
fun HomeScreen(viewModel: SaleViewModel) {
    var totalSales by rememberSaveable {
        mutableStateOf(BigDecimal.ZERO)
    }

    LaunchedEffect(Unit) {
        viewModel.getTotalSales()
    }

    val saleUiState by viewModel.totalSales.collectAsState()

    totalSales = saleUiState

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(DarkBlack),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        PrimaryButton(
            label = "Fazer uma venda",
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {}

        LargeSpacer()

        Text(text = "Total de vendas: R$ $totalSales", color = TextLight, fontSize = 24.sp)

    }
}