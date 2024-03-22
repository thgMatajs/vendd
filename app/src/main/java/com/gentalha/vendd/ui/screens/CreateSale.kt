package com.gentalha.vendd.ui.screens

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.hilt.getViewModel
import com.gentalha.vendd.ui.viewmodel.SaleViewModel

class CreateSale : Screen {
    @Composable
    override fun Content() {
        val viewModel: SaleViewModel = getViewModel()
        CreateSaleScreen(viewModel = viewModel)
    }
}