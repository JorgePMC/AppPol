package com.example.apppol

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.apppol.ui.navegation.AppNavigation
import com.example.apppol.ui.theme.AppPolTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppPolTheme {
                AppNavigation()
                }
            }
        }
    }