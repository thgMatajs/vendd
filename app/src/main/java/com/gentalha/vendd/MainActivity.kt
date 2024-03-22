package com.gentalha.vendd

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.gentalha.vendd.ui.screens.HomeScreen
import com.gentalha.vendd.ui.theme.DarkBlack
import com.gentalha.vendd.ui.theme.VenddTheme
import com.gentalha.vendd.ui.viewmodel.SaleViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: SaleViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            VenddTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier
                        .fillMaxSize(),
                    color = DarkBlack
                ) {
                    HomeScreen(viewModel)

                }
            }
        }
    }
}