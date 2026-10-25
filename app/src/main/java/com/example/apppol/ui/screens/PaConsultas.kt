package com.example.apppol.ui.screens


import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.apppol.ui.components.BarraNavegacionInferior
import com.example.apppol.ui.viewmodels.MainViewModel

@Composable
fun PantallaConsultas(viewModel: MainViewModel) {
    val verdeEncabezado = Color(0xFF8BB174)
    val verdeRespondido = Color(0xFFC8E6C9)
    val beigePendiente = Color(0xFFE6DEC8)
    val context = LocalContext.current

    var mensaje by remember { mutableStateOf("") }
    var docenteSeleccionado by remember { mutableStateOf("") }

    Scaffold(
        bottomBar = {
            BarraNavegacionInferior(viewModel = viewModel)
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
        ) {
            // Header
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(verdeEncabezado)
                    .padding(vertical = 16.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Comunicacion con superiores",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    fontStyle = FontStyle.Italic,
                    color = Color.White
                )
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                // Formulario
                Text(
                    text = "Escoja al docente",
                    fontWeight = FontWeight.Bold,
                    fontStyle = FontStyle.Italic
                )

                OutlinedTextField(
                    value = docenteSeleccionado,
                    onValueChange = { docenteSeleccionado = it },
                    placeholder = { Text("seleccione una opcion...") },
                    modifier = Modifier.fillMaxWidth()
                )

                OutlinedTextField(
                    value = mensaje,
                    onValueChange = { mensaje = it },
                    placeholder = { Text("Ingrese su mensaje...") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(120.dp)
                )

                Button(
                    onClick = {
                        Toast.makeText(context, "Para una futura actualización", Toast.LENGTH_SHORT).show()
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Enviar Consulta")
                }

                Divider(modifier = Modifier.padding(vertical = 8.dp))

                // Mis consultas
                Text(
                    text = "Mis consultas",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )

                // Consulta 1: Respondido
                Card(
                    colors = CardDefaults.cardColors(containerColor = verdeRespondido),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(modifier = Modifier.padding(12.dp)) {
                        Text(text = "Respondido", fontWeight = FontWeight.Bold)
                        Text(text = "Asignatura: Admin. y Gestión", fontSize = 13.sp)
                        Text(text = "Unidad 2 - Manual_Procedimientos_V1.pdf", fontSize = 13.sp)
                        Text(text = "\"Consulta sobre aplicación del art. 4...\"", fontSize = 13.sp)
                        Text(text = "Resp: \"Estimado Cabo, se aplica según...\"", fontSize = 13.sp, fontWeight = FontWeight.Medium)
                    }
                }

                // Consulta 2: Pendiente
                Card(
                    colors = CardDefaults.cardColors(containerColor = beigePendiente),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(modifier = Modifier.padding(12.dp)) {
                        Text(text = "PENDIENTE DE RESPUESTA", fontWeight = FontWeight.Bold)
                        Text(text = "Asignatura: Procedimientos Policiales II", fontSize = 13.sp)
                        Text(text = "Unidad 1 - Guía de Ejercicios N°1", fontSize = 13.sp)
                        Text(text = "\"Duda en la pregunta 3 del caso...\"", fontSize = 13.sp)
                        Text(text = "Enviado ayer a las 18:30", fontSize = 12.sp, color = Color.Gray)
                    }
                }
            }
        }
    }
}