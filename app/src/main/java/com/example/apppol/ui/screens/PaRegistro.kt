package com.example.apppol.ui.screens

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
import androidx.compose.ui.unit.dp

import com.example.apppol.ui.components.FormularioRegistro
import com.example.apppol.ui.model.InfoFormularioRegistro

@Composable
fun PantallaRe(
    onNavReEx: (InfoFormularioRegistro) -> Unit,
    onVolver: () -> Unit
){
    var rut by remember { mutableStateOf("") }
    var nombreCompleto by remember { mutableStateOf("") }
    var correo by remember { mutableStateOf("") }
    var contraseña by remember { mutableStateOf("") }
    var regimiento by remember { mutableStateOf("") }
    var direccion by remember { mutableStateOf("") }

    val scrollState = rememberScrollState()

    Surface (
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.surfaceVariant
    ){
        Column (
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .verticalScroll(scrollState),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "Ingrese sus datos:",
                style = MaterialTheme.typography.headlineSmall
            )

            Spacer(modifier = Modifier.height(24.dp))

            FormularioRegistro(
                rut = rut,
                onRutChange = { rut = it },
                nombre = nombreCompleto,
                onNombreChange = { nombreCompleto = it },
                correo = correo,
                onCorreoChange = { correo = it },
                contraseña = contraseña,
                onContraseñaChange = { contraseña = it },
                regimiento = regimiento,
                onRegimientoChange = { regimiento = it },
                direccion = direccion,
                onDireccionChange = { direccion = it }
            )

            Spacer(modifier = Modifier.height(32.dp))

            Button(
                onClick = {
                    if (rut.isNotBlank() &&
                        nombreCompleto.isNotBlank() &&
                        correo.isNotBlank() &&
                        contraseña.isNotBlank() &&
                        regimiento.isNotBlank() &&
                        direccion.isNotBlank()) {
                        val datosRegistro = InfoFormularioRegistro(
                            rut = rut,
                            nombreCompleto = nombreCompleto,
                            correo = correo,
                            contraseña = contraseña,
                            regimineto = regimiento,
                            direccion = direccion
                        )
                        onNavReEx(datosRegistro)
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ){
                Text(text = "Confirme sus datos")
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = onVolver,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Regresar")
            }
        }
    }
}