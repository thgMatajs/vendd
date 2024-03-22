package com.gentalha.vendd

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.transitions.SlideTransition
import com.gentalha.vendd.ui.screens.Home
import com.gentalha.vendd.ui.theme.DarkBlack
import com.gentalha.vendd.ui.theme.VenddTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
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
                    Navigator(screen = Home()) { navigator ->
                        SlideTransition(navigator = navigator)
                    }
                }
            }
        }
    }
}