package com.example.apppol.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp

@Composable
fun formularioInicioSesion (
    nombreCompleto: String,
    onNomvreCompletoChange: (String) -> Unit,
    correo: String,
    onCorreoChange: (String) -> Unit,
    contraseña: String,
    onContraseñaChange: (String) -> Unit
){
    // Estado interno para controlar si la contraseña es visible o está oculta
    var contrasenaVisible by remember { mutableStateOf(false) }

    Column(modifier = Modifier.fillMaxSize()) {
        Text(
            text = "Nombre completo: ",
            style = MaterialTheme.typography.headlineSmall
        )
        OutlinedTextField(
            value = nombreCompleto,
            onValueChange = onNomvreCompletoChange,
            label = {Text("Ingrese su nombre completo")},
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Correo: ",
            style = MaterialTheme.typography.headlineSmall
        )
        OutlinedTextField(
            value = correo,
            onValueChange = onCorreoChange,
            label = {Text("Ingrese su correo")},
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Contraseña: ",
            style = MaterialTheme.typography.headlineSmall
        )
        OutlinedTextField(
            value = contraseña,
            onValueChange = onContraseñaChange,
            label = {Text("Ingrese su contraseña")},
            // Si contrasenaVisible es true muestra el texto, de lo contrario muestra asterisco/puntos
            visualTransformation = if (contrasenaVisible) VisualTransformation.None else PasswordVisualTransformation(),
            trailingIcon = {
                val imagenIcono = if (contrasenaVisible) Icons.Filled.Visibility else Icons.Filled.VisibilityOff
                IconButton(onClick = { contrasenaVisible = !contrasenaVisible }) {
                    Icon(
                        imageVector = imagenIcono,
                        contentDescription = if (contrasenaVisible) "Ocultar contraseña" else "Mostrar contraseña"
                    )
                }
            },
            modifier = Modifier.fillMaxWidth()
        )
    }
}