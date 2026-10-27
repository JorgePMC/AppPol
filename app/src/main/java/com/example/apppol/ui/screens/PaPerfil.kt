package com.example.apppol.ui.screens

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.apppol.R
import com.example.apppol.ui.components.BarraNavegacionInferior
import com.example.apppol.ui.viewmodels.MainViewModel

@Composable
fun PantallaPe(viewModel: MainViewModel, onCerrarSesion: () -> Unit = {}) {
    val nombreUsuario by viewModel.nombreUsuario.collectAsState()
    val esDocente by viewModel.esDocente.collectAsState()
    val context = LocalContext.current

    Scaffold(
        bottomBar = {
            if (!esDocente) {
                BarraNavegacionInferior(viewModel = viewModel)
            }
        }
    ) { innerPadding ->
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            color = MaterialTheme.colorScheme.surfaceVariant
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Spacer(modifier = Modifier.height(10.dp))

                // Avatar / Escudo
                Surface(
                    shape = CircleShape,
                    color = Color(0xFF1B6717),
                    modifier = Modifier.size(100.dp)
                ) {
                    Box(contentAlignment = Alignment.Center) {
                        Image(
                            painter = painterResource(id = R.drawable.logo_carabineros),
                            contentDescription = null,
                            modifier = Modifier.size(70.dp),
                            contentScale = ContentScale.Fit
                        )
                    }
                }

                Text(
                    text = nombreUsuario,
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = if (esDocente) "Rol: Docente Institucional" else "Rol: Alumno / Funcionario",
                    color = Color.Gray,
                    fontSize = 14.sp
                )

                Card(
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        ItemPerfil("Institución", "Carabineros de Chile")
                        ItemPerfil("Unidad", "1a Comisaría Santiago Central")
                        ItemPerfil("Estado en Plataforma", "Activo")
                    }
                }

                Button(
                    onClick = {
                        Toast.makeText(context, "Para una futura actualización", Toast.LENGTH_SHORT).show()
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Icon(imageVector = Icons.Default.Person, contentDescription = null)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Editar Datos de Perfil")
                }

                OutlinedButton(
                    onClick = {
                        viewModel.cerrarSesion()
                        onCerrarSesion()
                    },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.Red)
                ) {
                    Icon(imageVector = Icons.Default.ExitToApp, contentDescription = null)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Cerrar Sesión")
                }
            }
        }
    }
}

@Composable
fun ItemPerfil(etiqueta: String, valor: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = etiqueta, fontWeight = FontWeight.SemiBold, fontSize = 13.sp)
        Text(text = valor, fontSize = 13.sp, color = Color.DarkGray)
    }
}