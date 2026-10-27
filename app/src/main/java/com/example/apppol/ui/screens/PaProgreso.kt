package com.example.apppol.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.apppol.ui.components.BarraNavegacionInferior
import com.example.apppol.ui.viewmodels.MainViewModel

@Composable
fun PantallaProgreso(
    viewModel: MainViewModel,
    onNavegarConsultas: () -> Unit = {},
    onNavegarAsignaturas: () -> Unit = {}
) {
    val nombreUsuario by viewModel.nombreUsuario.collectAsState()
    val verdeClaro = Color(0xFFC8E6C9)
    val verdeTarjeta = Color(0xFFE8F5E9)

    Scaffold(
        bottomBar = {
            BarraNavegacionInferior(viewModel = viewModel)
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(20.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            // Usuario
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.AccountCircle,
                    contentDescription = null,
                    modifier = Modifier.size(64.dp),
                    tint = Color(0xFF1B6717)
                )
                Column {
                    Text(text = "¡Hola,", style = MaterialTheme.typography.titleMedium)
                    Text(
                        text = nombreUsuario,
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            // Estado Conectado
            Surface(
                color = verdeClaro,
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 10.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = "Estado: Conectado (online)", fontSize = 14.sp, fontWeight = FontWeight.Medium)
                    Icon(imageVector = Icons.Default.Check, contentDescription = null, modifier = Modifier.size(18.dp))
                }
            }

            // Progreso
            Text(text = "Progreso general de estudio", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)

            Card(
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(2.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier.padding(20.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier.size(100.dp)
                    ) {
                        CircularProgressIndicator(
                            progress = 0.84f,
                            modifier = Modifier.fillMaxSize(),
                            color = Color(0xFF1B6717),
                            strokeWidth = 10.dp
                        )
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Text(text = "84%", fontWeight = FontWeight.Bold, fontSize = 20.sp)
                            Text(text = "completado", fontSize = 11.sp)
                        }
                    }

                    Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Box(modifier = Modifier.size(14.dp).background(Color(0xFF1B6717)))
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(text = "23 completados", fontSize = 13.sp)
                        }
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Box(modifier = Modifier.size(14.dp).background(Color.Gray))
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(text = "5 restantes", fontSize = 13.sp)
                        }
                    }
                }
            }

            // Mis Asignaturas Destacadas
            Text(text = "Avance por Asignatura:", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)

            Card(colors = CardDefaults.cardColors(containerColor = verdeTarjeta), modifier = Modifier.fillMaxWidth()) {
                Column(modifier = Modifier.padding(14.dp)) {
                    Text(text = "Admin y Gestión en Seg. Pública", fontWeight = FontWeight.Bold)
                    Text(text = "Docente: Cap. P. Rodríguez", fontSize = 13.sp)
                    Spacer(modifier = Modifier.height(4.dp))
                    LinearProgressIndicator(progress = 0.8f, modifier = Modifier.fillMaxWidth(), color = Color(0xFF1B6717))
                    Text(text = "80% completado", fontSize = 11.sp, modifier = Modifier.align(Alignment.End))
                }
            }

            Card(colors = CardDefaults.cardColors(containerColor = verdeTarjeta), modifier = Modifier.fillMaxWidth()) {
                Column(modifier = Modifier.padding(14.dp)) {
                    Text(text = "Procedimientos Policiales II", fontWeight = FontWeight.Bold)
                    Text(text = "Docente: Cap. J. Martínez", fontSize = 13.sp)
                    Spacer(modifier = Modifier.height(4.dp))
                    LinearProgressIndicator(progress = 0.4f, modifier = Modifier.fillMaxWidth(), color = Color(0xFF1B6717))
                    Text(text = "40% completado", fontSize = 11.sp, modifier = Modifier.align(Alignment.End))
                }
            }
        }
    }
}