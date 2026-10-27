package com.example.apppol.ui.navegation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

import com.example.apppol.ui.screens.PantallaIn
import com.example.apppol.ui.screens.PantallaProgreso
import com.example.apppol.ui.screens.PantallaRe
import com.example.apppol.ui.screens.PantallaInSe
import com.example.apppol.ui.screens.PantallaMa
import com.example.apppol.ui.screens.PantallaMaCo
import com.example.apppol.ui.screens.PantallaPe
import com.example.apppol.ui.screens.PantallaReEx
import com.example.apppol.ui.screens.PantallaConsultas
import com.example.apppol.ui.screens.PantallaDocente

import com.example.apppol.ui.viewmodels.MainViewModel
import com.example.apppol.ui.viewmodels.NavigationEvent

import kotlinx.coroutines.flow.collectLatest

@Composable
fun AppNavigation(viewModel: MainViewModel = androidx.lifecycle.viewmodel.compose.viewModel()) {
    val navController = rememberNavController()

    LaunchedEffect(key1 = Unit) {
        viewModel.navigationEvents.collectLatest { event ->
            when (event) {
                is NavigationEvent.NavigateTo -> {
                    navController.navigate(route = event.route.route) {
                        launchSingleTop = true
                    }
                }
                else -> {}
            }
        }
    }

    NavHost(navController = navController, startDestination = "pantallaInSe") {
        composable(
            route = "pantallaInSe",
            enterTransition = { fadeIn(animationSpec = tween(1500)) },
            exitTransition = { fadeOut(animationSpec = tween(2500)) }
        ) {
            PantallaInSe(
                viewModel = viewModel,
                onNavegarIndex = { navController.navigate(ScreenIn.Inicio.route) },
                onNavegarDocenteComplementario = {
                    // Redirige directamente al nuevo Panel Docente y quita el Login del stack
                    navController.navigate("pantallaDocente") {
                        popUpTo("pantallaInSe") { inclusive = true }
                    }
                },
                onNavegarRegistro = { navController.navigate("pantallaRe") }
            )
        }

        // PANEL EXCLUSIVO DOCENTE
        composable(route = "pantallaDocente") {
            PantallaDocente(
                viewModel = viewModel,
                onNavegarConsultas = { navController.navigate("pantallaConsultas") },
                onNavegarMaterial = { navController.navigate(ScreenIn.Material.route) },
                onNavegarAsignaturas = { navController.navigate(ScreenIn.Materias.route) },
                onNavegarPerfil = { navController.navigate(ScreenIn.Perfil.route) }
            )
        }

        composable(route = "pantallaRe") {
            PantallaRe(
                onNavReEx = { navController.navigate("PantallaReEx") },
                onVolver = { navController.popBackStack() }
            )
        }

        composable(route = "PantallaReEx") {
            PantallaReEx(
                onNavIndex = { navController.navigate(ScreenIn.Inicio.route) }
            )
        }

        composable(route = ScreenIn.Inicio.route) {
            PantallaIn(
                viewModel = viewModel,
                onNavegarProgreso = { navController.navigate("pantallaProgreso") },
                onNavegarAsignaturas = { navController.navigate(ScreenIn.Materias.route) },
                onNavegarConsultas = { navController.navigate("pantallaConsultas") }
            )
        }

        composable(route = "pantallaProgreso") {
            PantallaProgreso(
                viewModel = viewModel,
                onNavegarConsultas = { navController.navigate("pantallaConsultas") },
                onNavegarAsignaturas = { navController.navigate(ScreenIn.Materias.route) }
            )
        }

        composable(route = ScreenIn.Materias.route) {
            PantallaMa(viewModel = viewModel)
        }

        composable(route = ScreenIn.Material.route) {
            PantallaMaCo(viewModel = viewModel)
        }

        composable(route = ScreenIn.Perfil.route) {
            PantallaPe(
                viewModel = viewModel,
                onCerrarSesion = {
                    navController.navigate("pantallaInSe") {
                        popUpTo(0) { inclusive = true }
                    }
                }
            )
        }

        composable(route = "pantallaConsultas") {
            PantallaConsultas(viewModel = viewModel)
        }
    }
}