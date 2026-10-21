package com.example.apppol.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.apppol.R

import com.example.apppol.ui.components.formularioInicioSesion
import com.example.apppol.ui.model.InfoInicioSesion

@Composable
fun PantallaInSe(
    onNavegarIndex: (InfoInicioSesion) -> Unit,
    onNavegarRegistro: () -> Unit
) {
    var nombreCompleto by remember { mutableStateOf("") }
    var correo by remember { mutableStateOf("") }
    var contraseña by remember { mutableStateOf("") }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.surfaceVariant
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.logo_carabineros),
                    contentDescription = "Logo de la institución",
                    modifier = Modifier.size(120.dp),
                    contentScale = ContentScale.Fit
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "Inicio de Sesión",
                    style = MaterialTheme.typography.headlineSmall
                )

                Spacer(modifier = Modifier.height(24.dp))

                formularioInicioSesion(
                    nombreCompleto = nombreCompleto,
                    onNomvreCompletoChange = { nombreCompleto = it },
                    correo = correo,
                    onCorreoChange = { correo = it },
                    contraseña = contraseña,
                    onContraseñaChange = { contraseña = it }
                )
            }
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Button(
                    onClick = {
                        if (nombreCompleto.isNotBlank() &&
                            correo.isNotBlank() &&
                            contraseña.isNotBlank()){
                            val datosInicioSesion = InfoInicioSesion(
                                nombreCompleto = nombreCompleto,
                                correo = correo,
                                contraseña = contraseña
                            )
                            onNavegarIndex(datosInicioSesion)
                        }
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(text = "Inicie sesión")
                }

                Button(
                    onClick = onNavegarRegistro,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(text = "Registre su cuenta")
                }
                Spacer(modifier = Modifier.height(24.dp))
            }
        }
    }
}