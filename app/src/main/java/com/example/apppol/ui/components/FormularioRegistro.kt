package com.example.apppol.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun FormularioRegistro(
    rut: String,
    onRutChange: (String) -> Unit,
    nombre: String,
    onNombreChange: (String) -> Unit,
    correo: String,
    onCorreoChange: (String) -> Unit,
    contraseña: String,
    onContraseñaChange: (String) -> Unit,
    regimiento: String,
    onRegimientoChange: (String) -> Unit,
    direccion: String,
    onDireccionChange: (String) -> Unit,

) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = "Rut: ",
            style = MaterialTheme.typography.headlineSmall
        )
        OutlinedTextField(
            value = rut,
            onValueChange = onRutChange,
            label = { Text("Ingrese su rut con punto y guion") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Nombre Completo: ",
            style = MaterialTheme.typography.headlineSmall
        )
        OutlinedTextField(
            value = nombre,
            onValueChange = onNombreChange,
            label = { Text("Ingrese su primer y segundo nombre") },
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
            label = { Text("Ingrese correo electrónico") },
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
            label = { Text("Ingrese contraseña") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Nombre del regimiento: ",
            style = MaterialTheme.typography.headlineSmall
        )
        OutlinedTextField(
            value = regimiento,
            onValueChange = onRegimientoChange,
            label = { Text("Ingrese el nombre de su regimiento") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Direccion del regimiento: ",
            style = MaterialTheme.typography.headlineSmall
        )
        OutlinedTextField(
            value = direccion,
            onValueChange = onDireccionChange,
            label = { Text("Ingrese su direccion") },
            modifier = Modifier.fillMaxWidth()
        )
    }
}