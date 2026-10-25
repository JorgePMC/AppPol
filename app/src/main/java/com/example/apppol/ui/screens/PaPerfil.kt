package com.example.apppol.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

import com.example.apppol.ui.viewmodels.MainViewModel
import com.example.apppol.ui.components.BarraNavegacionInferior

@Composable
fun PantallaPe(viewModel: MainViewModel) {

    Scaffold(
        bottomBar = {
            BarraNavegacionInferior(viewModel = viewModel)
        }
    ) { innerPadding ->
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            color = MaterialTheme.colorScheme.surfaceVariant
        ){
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "PERFIL EN PROCESO",
                    style = MaterialTheme.typography.headlineMedium
                )
            }
        }
    }
}