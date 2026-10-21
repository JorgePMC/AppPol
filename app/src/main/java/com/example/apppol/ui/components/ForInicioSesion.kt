package com.example.apppol.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
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
            modifier = Modifier.fillMaxWidth()
        )
    }
}