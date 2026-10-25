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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.apppol.ui.components.BarraNavegacionInferior
import com.example.apppol.ui.viewmodels.MainViewModel

@Composable
fun PantallaIn(
    viewModel: MainViewModel,
    onNavegarConsultas: () -> Unit = {},
    onNavegarAsignaturas: () -> Unit = {}
) {
    val verdeClaro = Color(0xFFC8E6C9)
    val verdeTarjeta = Color(0xFFD4E7D0)

    Scaffold(
        bottomBar = {
            BarraNavegacionInferior(viewModel = viewModel)
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // 1. Cabecera
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.AccountCircle,
                    contentDescription = "Foto de perfil",
                    modifier = Modifier.size(75.dp)
                )
                Column {
                    Text(
                        text = "¡Hola,",
                        style = MaterialTheme.typography.titleMedium,
                        fontStyle = FontStyle.Italic
                    )
                    Text(
                        text = "Cabo Perez!",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold,
                        fontStyle = FontStyle.Italic
                    )
                }
            }

            // 2. Estado Conectado
            Surface(
                color = verdeClaro,
                shape = RoundedCornerShape(4.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 12.dp, vertical = 8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = "Conectado (online)", fontSize = 14.sp)
                    Icon(
                        imageVector = Icons.Default.Check,
                        contentDescription = "Estado online",
                        modifier = Modifier.size(18.dp)
                    )
                }
            }

            // 3. Progreso General
            Text(
                text = "Progreso general de estudio",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(20.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier.size(90.dp)
                ) {
                    CircularProgressIndicator(
                        progress = 0.84f,
                        modifier = Modifier.fillMaxSize(),
                        color = Color(0xFF4CAF50),
                        strokeWidth = 8.dp
                    )
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(text = "84%", fontWeight = FontWeight.Bold, fontSize = 16.sp)
                        Text(text = "completado", fontSize = 10.sp)
                    }
                }

                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Box(modifier = Modifier.size(16.dp).background(verdeClaro))
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(text = "23 cursos completados", fontSize = 14.sp)
                    }
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Box(modifier = Modifier.size(16.dp).background(Color.Black))
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(text = "5 cursos restantes", fontSize = 14.sp)
                    }
                }
            }

            // 4. Mis asignaturas
            Text(
                text = "Mis asignaturas:",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )

            Card(
                colors = CardDefaults.cardColors(containerColor = verdeTarjeta),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(12.dp)) {
                    Text(text = "Admin y Gestion en Seg. Publica", fontWeight = FontWeight.Bold)
                    Text(text = "Docente: Cap. P. Rodriguez", fontSize = 14.sp)
                    Text(text = "Progreso: 80% (4/5)", fontSize = 14.sp)
                }
            }

            Card(
                colors = CardDefaults.cardColors(containerColor = verdeTarjeta),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(12.dp)) {
                    Text(text = "Procedimientos Policiales II", fontWeight = FontWeight.Bold)
                    Text(text = "Docente: Cap. J. Martinez", fontSize = 14.sp)
                    Text(text = "Progreso: 40% (2/5)", fontSize = 14.sp)
                }
            }

            // 5. Botones de Navegación Funcionales
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                TextButton(onClick = onNavegarConsultas) {
                    Text(
                        text = "Consultas",
                        textDecoration = TextDecoration.Underline,
                        color = Color.Black,
                        fontWeight = FontWeight.Bold
                    )
                }

                TextButton(onClick = onNavegarAsignaturas) {
                    Text(
                        text = "Asignaturas",
                        textDecoration = TextDecoration.Underline,
                        color = Color.Black,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}