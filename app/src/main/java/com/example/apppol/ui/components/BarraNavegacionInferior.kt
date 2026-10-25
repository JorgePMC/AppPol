package com.example.apppol.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

import com.example.apppol.ui.navegation.ScreenIn
import com.example.apppol.ui.viewmodels.MainViewModel

@Composable
fun BarraNavegacionInferior(viewModel: MainViewModel) {
    val items = listOf(ScreenIn.Inicio, ScreenIn.Materias, ScreenIn.Material, ScreenIn.Perfil)
    var selectedItem by remember { mutableStateOf(0) }

    NavigationBar {
        items.forEachIndexed { index, screen ->
            NavigationBarItem(
                selected = selectedItem == index,
            onClick = {
                selectedItem = index
                viewModel.navigateTo(screen)
            },
            label = {
                Text(
                    when(screen) {
                        ScreenIn.Inicio -> "Inicio"
                        ScreenIn.Materias -> "Materias"
                        ScreenIn.Material -> "Material"
                        ScreenIn.Perfil -> "Perfil"
                        else -> ""
                    }
                )
            },
            icon = {
                Icon(
                    imageVector = when(screen) {
                        ScreenIn.Inicio -> Icons.Default.Home
                        ScreenIn.Materias -> Icons.Default.List
                        ScreenIn.Material -> Icons.Default.Star
                        ScreenIn.Perfil -> Icons.Default.Person
                        else -> Icons.Default.Home
                    },
                    contentDescription = screen.route
                )
            }
            )
        }
    }
}