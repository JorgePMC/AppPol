package com.example.apppol.ui.navegation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.apppol.ui.screens.PantallaIn

import com.example.apppol.ui.screens.PantallaRe
import com.example.apppol.ui.screens.PantallaInSe
import com.example.apppol.ui.screens.PantallaReEx

@Composable
fun AppNavigation(){
    val navController = rememberNavController()
    NavHost(navController = navController , startDestination = "pantallaInSe"){
        composable (
            route = "pantallaInSe",
            enterTransition = { fadeIn(animationSpec = tween(1500))},
            exitTransition = {fadeOut(animationSpec = tween(2500)) },
            popEnterTransition ={ fadeIn(animationSpec = tween(1500))},
            popExitTransition = {fadeOut(animationSpec = tween(1500)) },
        ){
            PantallaInSe (
                onNavegarIndex = {navController.navigate("PantallaIn")},
                onNavegarRegistro = {navController.navigate("pantallaRe")}
            )
        }

        composable(
            route = "pantallaRe",
            enterTransition = {
                slideIntoContainer(
                    towards = AnimatedContentTransitionScope.SlideDirection.Up,
                    animationSpec = tween(1000)
                )
            },
            exitTransition = {
                slideOutOfContainer(
                    towards = AnimatedContentTransitionScope.SlideDirection.Down,
                    animationSpec = tween(500)
                )
            }

        ){
            PantallaRe(
                onNavReEx = {navController.navigate("PantallaReEx")},
                onVolver = {navController.popBackStack()}

            )
        }

        composable (
            route = "PantallaReEx"
        ){
            PantallaReEx(
                onNavIndex = {navController.navigate("PantallaIn")}
            )
        }

        composable(
            route = "PantallaIn"
        ){
            PantallaIn(
                onAlgo = {navController.navigate("#3")}
            )
        }
    }
}