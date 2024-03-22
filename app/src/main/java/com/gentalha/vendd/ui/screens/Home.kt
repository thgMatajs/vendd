package com.gentalha.vendd.ui.screens

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.hilt.getViewModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.gentalha.vendd.ui.viewmodel.SaleViewModel

class Home : Screen {
    @Composable
    override fun Content() {
        val viewModel: SaleViewModel = getViewModel()
        val navigator = LocalNavigator.currentOrThrow
        HomeScreen(viewModel = viewModel) {
            navigator.push(CreateSale())
        }
    }
}