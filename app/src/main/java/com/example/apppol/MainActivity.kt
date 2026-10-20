package com.example.apppol

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppNavigation()
        }
    }
}

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
            PantallaInicioSe(
                onNavegarIndexApp = {navController.navigate("#2")},
                onNavegarRegistro = {navController.navigate("pantallaRe")}
            )
        }

        composable(
            route = "pantallaRe",
            enterTransition = {
                slideIntoContainer(
                    towards = AnimatedContentTransitionScope.SlideDirection.Down,
                    animationSpec = tween(4400)
                )
            },
            exitTransition = {
                slideOutOfContainer(
                    towards = AnimatedContentTransitionScope.SlideDirection.Right,
                    animationSpec = tween(2400)
                )
            }

        ){
            PantallaRegistro(
                onNavIndex = {navController.navigate("#2")},
                onVolver = {navController.popBackStack()}

            )
        }


    }
}



@Composable
fun PantallaInicioSe(
    onNavegarIndexApp: () -> Unit,
    onNavegarRegistro: () -> Unit
    ) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.surfaceVariant
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Inicio de Sesion",
                style = MaterialTheme.typography.headlineMedium
            )
            Spacer(modifier = Modifier.height(24.dp))
            Button(onClick = onNavegarIndexApp) {
                Text(text = "Inicie sesion")
            }
            Spacer(modifier = Modifier.height(24.dp))
            Button(onClick = onNavegarRegistro) {
                Text(text = "Registre su cuenta")
            }
        }
    }
}

@Composable
fun PantallaRegistro(
    onNavIndex: () -> Unit,
    onVolver: () -> Unit
    ) {
    Surface (
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.surfaceVariant
    ) {
        Column (
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Ingrese sus datos:",
                style = MaterialTheme.typography.headlineMedium
            )
            Spacer(modifier = Modifier.height(24.dp))
            Button(onClick = onVolver) {
                Text(text = "Regresar")
            }
            Spacer(modifier = Modifier.height(24.dp))
            Button(onClick = onNavIndex) {
                Text(text = "Confirme sus datos")
            }
        }
    }
}